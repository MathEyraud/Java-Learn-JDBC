package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.util.DatabaseExceptionHandler;
import com.mycompany.tennis.core.util.DatabaseManager;

public class MatchRepositoryImpl {
	
	private static final String sqlRequestCreate		= "INSERT match_tennis SET ID_EPREUVE=?, ID_VAINQUEUR=?, ID_FINALISTE=?";
    private static final String sqlRequestUpdate		= "UPDATE match_tennis SET ID_EPREUVE=?, ID_VAINQUEUR=?, ID_FINALISTE=? WHERE ID=?";
    private static final String sqlRequestDelete		= "DELETE FROM match_tennis WHERE ID = ?";
    private static final String sqlRequestGetMatch		= "SELECT * FROM match_tennis";
    private static final String sqlRequestGetMatchById	= "SELECT * FROM match_tennis WHERE ID=?";

	public void create(Match match) {
		
		// Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
        try {
        	// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
        	connexion = DatabaseManager.getConnection();			

            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
        	
            // "Statement.RETURN_GENERATED_KEYS" : Permet de récupérer les valeurs auto-générés
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequestCreate, Statement.RETURN_GENERATED_KEYS);
            
            //TODO: Créer une erreur si pas d'id du match ???
            if (match.getEpreuve().getId()==null) {
            	preparedStatement.setNull(1, Types.BIGINT);
			} else {
				preparedStatement.setLong(1, match.getEpreuve().getId());
			}
            
            if (match.getVainqueur().getId()==null) {
            	preparedStatement.setNull(2, Types.BIGINT);
			} else {
				preparedStatement.setLong(2, match.getVainqueur().getId());
			}
            
            if (match.getFinaliste().getId()==null) {
            	preparedStatement.setNull(3, Types.BIGINT);
			} else {
				preparedStatement.setLong(3, match.getFinaliste().getId());
			}
            
            
            
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            
            if (result==0) {
                System.out.println("Aucun match n'a été ajouté !");
                
            } else {
            	
            	// Récupérer l'ID du nouveau match
            	// En récupérant les valeurs générés
            	ResultSet newInformations = preparedStatement.getGeneratedKeys();
            	if(newInformations.next()) {
            		match.setId(newInformations.getLong(1));
            	}
            	
            	// Affichage des données du match actualisé
                System.out.println("Success - Les données ont bien été ajoutés.");
            }
                
            // Valider la transaction si tout s'est bien passé
            connexion.commit();
                        
        // Gestion des exceptions SQL.
        } catch (SQLException e) {
        	System.out.println("Erreur lors de la création du match !");
        	DatabaseExceptionHandler.handleException(e, connexion);
        
        // Bloc finally s'exécute toujours, qu'il y ait une exception ou non. 
        // Fermeture de la connexion si elle a été établie.
        } finally {
        	DatabaseManager.closeConnection(connexion);
        }
	}

}
