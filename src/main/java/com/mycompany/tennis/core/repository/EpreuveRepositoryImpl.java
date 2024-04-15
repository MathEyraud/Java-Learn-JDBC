package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.util.DatabaseExceptionHandler;
import com.mycompany.tennis.core.util.DatabaseManager;

public class EpreuveRepositoryImpl {
	
	private static final String sqlRequestCreate		= "INSERT epreuve SET ANNEE=?, TYPE_EPREUVE=?, ID_TOURNOI=?";
    private static final String sqlRequestUpdate		= "UPDATE epreuve SET ANNEE=?, TYPE_EPREUVE=?, ID_TOURNOI=? WHERE ID=?";
    private static final String sqlRequestDelete		= "DELETE FROM epreuve WHERE ID = ?";
    private static final String sqlRequestGetEpreuve	= "SELECT * FROM epreuve";
    private static final String sqlRequestGetEpreuveById= "SELECT * FROM epreuve WHERE ID=?";

	public void create(Epreuve epreuve) {
		
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
            preparedStatement.setShort(1, epreuve.getAnnee());
            preparedStatement.setString(2, epreuve.getTypeEpreuve().toString());
            preparedStatement.setLong(3, epreuve.getTournoi().getId());
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            
            if (result==0) {
                System.out.println("Aucune épreuve n'a été ajouté !");
                
            } else {
            	
            	// Récupérer l'ID du nouveau score
            	// En récupérant les valeurs générés
            	ResultSet newInformations = preparedStatement.getGeneratedKeys();
            	if(newInformations.next()) {
            		epreuve.setId(newInformations.getLong(1));
            	}
            	
            	// Affichage d'un message de reussite
                System.out.println("Success - Les données ont bien été ajoutés.");
            }
                
            // Valider la transaction si tout s'est bien passé
            connexion.commit();
                        
        // Gestion des exceptions SQL.
        } catch (SQLException e) {
        	System.out.println("Erreur lors de la création d'une épreuve !");
        	DatabaseExceptionHandler.handleException(e, connexion);
        
        // Bloc finally s'exécute toujours, qu'il y ait une exception ou non. 
        // Fermeture de la connexion si elle a été établie.
        } finally {
        	DatabaseManager.closeConnection(connexion);
        }
	}
    public Epreuve getEpreuveById(long idEpreuve) {
    	
    	// Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
        // Création de la variable de sortie
        Epreuve epreuve = new Epreuve();
     	
    	try {
    		
    		// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
    		connexion = DatabaseManager.getConnection();		
            
            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequestGetEpreuveById);
            preparedStatement.setLong(1, idEpreuve);
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            ResultSet result = preparedStatement.executeQuery();
            
            // Itération sur le ResultSet.
            boolean found = false;
            
            // Traitement des résultats. Si au moins une ligne est renvoyée...
            while(result.next()) {
            	
            	found = true;
            	
            	// Récupération des données de chaque colonne en utilisant les bons types.
            	epreuve.setId(result.getLong("ID"));
            	epreuve.setAnnee(result.getShort("ANNEE"));
            	epreuve.setTypeEpreuve(result.getString("TYPE_EPREUVE").charAt(0));
            	epreuve.setIdTournoi(result.getLong("ID_TOURNOI"));
            	//TODO: Transformer id tounoi en l'objet tournoi
            } 
            
            if (!found) {
                System.out.println("Pas d'épreuve !");
                
            } else {
                System.out.println("Success - Des données ont été trouvées et affichées.");
            }
            
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture de l'épreuve.");
			DatabaseExceptionHandler.handleException(e, connexion);
            
		} finally {
			DatabaseManager.closeConnection(connexion);
        }
    	
    	return epreuve;
    }

}
