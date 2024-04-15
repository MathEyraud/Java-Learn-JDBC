package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.util.DatabaseExceptionHandler;
import com.mycompany.tennis.core.util.DatabaseManager;

public class ScoreRepositoryImpl {
	
	private static final String sqlRequestCreate		= "INSERT score_vainqueur SET ID_MATCH=?, SET_1=?, SET_2=?, SET_3=?, SET_4=?, SET_5=?";
    private static final String sqlRequestUpdate		= "UPDATE score_vainqueur SET ID_MATCH=?, SET_1=?, SET_2=?, SET_3=?, SET_4=?, SET_5=? WHERE ID=?";
    private static final String sqlRequestDelete		= "DELETE FROM score_vainqueur WHERE ID = ?";
    private static final String sqlRequestGetMatch		= "SELECT * FROM score_vainqueur";
    private static final String sqlRequestGetMatchById	= "SELECT * FROM score_vainqueur WHERE ID=?";

	public void create(Score score) {
		
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
            preparedStatement.setLong(1, score.getMatch().getId());
            
            if (score.getSet1()==null) {
            	preparedStatement.setNull(2, Types.TINYINT);
			} else {
				preparedStatement.setByte(2, score.getSet1());
			}
            
            if (score.getSet2()==null) {
            	preparedStatement.setNull(3, Types.TINYINT);
			} else {
				preparedStatement.setByte(3, score.getSet2());
			}
            
            if (score.getSet3()==null) {
            	preparedStatement.setNull(4, Types.TINYINT);
			} else {
				preparedStatement.setByte(4, score.getSet3());
			}
            
            if (score.getSet4()==null) {
            	preparedStatement.setNull(5, Types.TINYINT);
			} else {
				preparedStatement.setByte(5, score.getSet4());
			}
            
            if (score.getSet5()==null) {
            	preparedStatement.setNull(6, Types.TINYINT);
			} else {
				preparedStatement.setByte(6, score.getSet5());
			}
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            
            if (result==0) {
                System.out.println("Aucun score n'a été ajouté !");
                
            } else {
            	
            	// Récupérer l'ID du nouveau score
            	// En récupérant les valeurs générés
            	ResultSet newInformations = preparedStatement.getGeneratedKeys();
            	if(newInformations.next()) {
            		score.setId(newInformations.getLong(1));
            	}
            	
            	// Affichage d'un message de reussite
                System.out.println("Success - Les données ont bien été ajoutés.");
            }
                
            // Valider la transaction si tout s'est bien passé
            connexion.commit();
                        
        // Gestion des exceptions SQL.
        } catch (SQLException e) {
        	System.out.println("Erreur lors de la création du score !");
        	DatabaseExceptionHandler.handleException(e, connexion);
        
        // Bloc finally s'exécute toujours, qu'il y ait une exception ou non. 
        // Fermeture de la connexion si elle a été établie.
        } finally {
        	DatabaseManager.closeConnection(connexion);
        }
	}

}
