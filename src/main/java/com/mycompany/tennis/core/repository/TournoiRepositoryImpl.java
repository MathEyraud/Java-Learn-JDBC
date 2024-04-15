package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.util.DatabaseExceptionHandler;
import com.mycompany.tennis.core.util.DatabaseManager;

public class TournoiRepositoryImpl {
	
	private static final String sqlRequestCreate		= "INSERT tournoi SET NOM=?, CODE=?";
    private static final String sqlRequestUpdate		= "UPDATE tournoi SET NOM=?, CODE=? WHERE ID=?";
    private static final String sqlRequestDelete		= "DELETE FROM tournoi WHERE ID = ?";
    private static final String sqlRequestGetMatch		= "SELECT * FROM tournoi";
    private static final String sqlRequestGetMatchById	= "SELECT * FROM tournoi WHERE ID=?";

	public void create(Tournoi tournoi) {
		
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
            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            
            if (result==0) {
                System.out.println("Aucun score n'a été ajouté !");
                
            } else {
            	
            	// Récupérer l'ID du nouveau tournoi
            	// En récupérant les valeurs générés
            	ResultSet newInformations = preparedStatement.getGeneratedKeys();
            	if(newInformations.next()) {
            		tournoi.setId(newInformations.getLong(1));
            	}
            	
            	// Affichage d'un message de reussite
                System.out.println("Success - Les données ont bien été ajoutés.");
            }
                
            // Valider la transaction si tout s'est bien passé
            connexion.commit();
                        
        // Gestion des exceptions SQL.
        } catch (SQLException e) {
        	System.out.println("Erreur lors de la création du tournoi !");
        	DatabaseExceptionHandler.handleException(e, connexion);
        
        // Bloc finally s'exécute toujours, qu'il y ait une exception ou non. 
        // Fermeture de la connexion si elle a été établie.
        } finally {
        	DatabaseManager.closeConnection(connexion);
        }
	}

}
