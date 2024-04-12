package com.mycompany.tennis.core.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;

public class JoueurRepositoryImpl {
	
    private static final String URL_MYSQL 				= "jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private static final String USER_MYSQL				= "ADMIN";
    private static final String PASSWORD				= "Password";
    
    private static final String sqlRequestCreate		= "INSERT JOUEUR SET NOM=?, PRENOM=?, SEXE=?";
    private static final String sqlRequestUpdate		= "UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?";
    private static final String sqlRequestDelete		= "DELETE FROM JOUEUR WHERE ID = ?";
    private static final String sqlRequestGetJoueurs	= "SELECT * FROM JOUEUR";
    private static final String sqlRequestGetJoueursById= "SELECT * FROM JOUEUR WHERE ID=?";
    
	public void create(Joueur joueur) {
		
		// Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
        try {
        	// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
        	DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	connexion = dataSource.getConnection();				

            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
        		
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequestCreate);
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            
            if (result==0) {
                System.out.println("Aucun utilisateur n'a été ajouté !");
                
            } else {
            	// Affichage des données du joueur actualisé
                System.out.println("Success - Les données ont bien été ajoutés.");
            }
                
            // Valider la transaction si tout s'est bien passé
            connexion.commit();
                        
        // Gestion des exceptions SQL.
        } catch (SQLException e) {
        	System.out.println("Erreur lors de la création d'un joueur !");
        	handleDatabaseException(e, connexion);
        
        // Bloc finally s'exécute toujours, qu'il y ait une exception ou non. 
        // Fermeture de la connexion si elle a été établie.
        } finally {
        	closeConnection(connexion);
        }
	}
    public void update(Joueur joueur) {
    	
    	// Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
    	try {
    		
    		// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
    		DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	connexion = dataSource.getConnection();		
            
            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequestUpdate);
            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            System.out.println(result);
            
            if (result==0) {
                System.out.println("Pas d'utilisateur à cet id !");
                
            } else {
            	// Affichage des données du joueur actualisé
                System.out.println("Success - Les données ont bien été modifié.");
            }
            
		} catch (SQLException e) {
			System.out.println("Erreur lors de la modification d'un joueur !");
			handleDatabaseException(e, connexion);
			
		} finally {
			closeConnection(connexion);
		}
    }
    public void delete(long idJoueur) {
    	
    	// Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
    	try {
    		
    		// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
    		DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	connexion = dataSource.getConnection();		
            
            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequestDelete);
            preparedStatement.setLong(1, idJoueur);
            
            // On vérifie que le joueur à bien été supprimé
            int rowsAffected = preparedStatement.executeUpdate();
            
            // Si c'est le cas on affiche un message de succès
            if (rowsAffected > 0) {
                System.out.println("Success - " + rowsAffected + " ligne(s) ont été supprimée(s).");
            
            // Sinon un message pour dire qu'il ne sait rien passé
            } else {
                System.out.println("Aucune ligne supprimée pour l'ID " + idJoueur + ".");
            }
            
		} catch (SQLException e) {
			System.out.println("Erreur lors de la suppression du joueur.");
			handleDatabaseException(e, connexion);
            
		} finally {
        	closeConnection(connexion);
        }
    }
    public List<Joueur> getJoueurs() {
    	
    	//Création de la liste des joueurs
    	List<Joueur> joueurs = new ArrayList<>();
    	
    	// Variable pour gérer la connexion à la DB
        Connection connexion = null;
    	
    	try {
    		
    		// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
    		DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	connexion = dataSource.getConnection();			
            
            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequestGetJoueurs);
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            ResultSet result = preparedStatement.executeQuery();
            
            // Itération sur le ResultSet.
            boolean found = false;
            
            // Traitement des résultats => Si au moins une ligne est renvoyée...
            while(result.next()) {
            	found = true;
            	Joueur newJoueur = new Joueur();
            	
                // Récupération des données de chaque colonne en utilisant les bons types.
            	newJoueur.setId(result.getLong("ID"));
            	newJoueur.setNom(result.getString("NOM"));
            	newJoueur.setPrenom(result.getString("PRENOM"));
            	newJoueur.setSexe(result.getString("SEXE").charAt(0));
            	
            	// Ajouter le joueur trouvé à la liste
            	joueurs.add(newJoueur);                
            } 
            
            if (!found) {
                System.out.println("Pas d'utilisateur !");
                
            } else {
                System.out.println("Success - Des données ont été trouvées et retournés.");
            }
            
		} catch (SQLException e) {
			System.out.println("Erreur lors de la récupération des joueurs.");
			handleDatabaseException(e, connexion);
            
		} finally {
        	closeConnection(connexion);
        }
		
    	return joueurs;
    }
    public Joueur getJoueurById(long idJoueur) {
    	
    	// Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
        // Création de la variable de sortie
        Joueur joueur = new Joueur();
     	
    	try {
    		
    		// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
    		DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
        	connexion = dataSource.getConnection();			
            
            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequestGetJoueursById);
            preparedStatement.setLong(1, idJoueur);
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            ResultSet result = preparedStatement.executeQuery();
            
            // Itération sur le ResultSet.
            boolean found = false;
            
            // Traitement des résultats. Si au moins une ligne est renvoyée...
            while(result.next()) {
            	
            	found = true;
            	
            	// Récupération des données de chaque colonne en utilisant les bons types.
            	joueur.setId(result.getLong("ID"));
            	joueur.setNom(result.getString("NOM"));
            	joueur.setPrenom(result.getString("PRENOM"));
            	joueur.setSexe(result.getString("SEXE").charAt(0));
            } 
            
            if (!found) {
                System.out.println("Pas d'utilisateur !");
                
            } else {
                System.out.println("Success - Des données ont été trouvées et affichées.");
            }
            
		} catch (SQLException e) {
			System.out.println("Erreur lors de la lecture du joueur.");
			handleDatabaseException(e, connexion);
            
		} finally {
        	closeConnection(connexion);
        }
    	
    	return joueur;
    }

    private static void handleDatabaseException(SQLException e, Connection connexion) {
        // Ici, nous pouvons centraliser notre gestion des exceptions pour la base de données.
        System.out.println("Erreur de connexion à la base de données.");
        e.printStackTrace();
        
        // En cas d'erreur, annuler toutes les modifications effectuées au sein de cette transaction
        try {
            if (connexion != null) {
                connexion.rollback();
            }
        } catch (SQLException e1) {
            System.out.println("Erreur lors de l'annulation des modifications !");
            e1.printStackTrace();
        }
    }
    private static void closeConnection(Connection connexion) {
        try {
            if (connexion != null) {
            	// Réactiver l'auto-commit pour la prochaine transaction
                connexion.setAutoCommit(true);
                connexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
