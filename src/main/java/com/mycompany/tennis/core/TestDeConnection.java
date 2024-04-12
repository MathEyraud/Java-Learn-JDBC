package com.mycompany.tennis.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class TestDeConnection {
	
	// Données pour la connexion à la DB
	// Connexion à MySQL avec désactivation de SSL et paramétrage de la timezone (cf URL)
    private static final String URL_MYSQL 		= "jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private static final String USER_MYSQL		= "ADMIN";
    
    private static final String URL_POSTGRESQL 	= "jdbc:postgresql://localhost:5432/tennis";
    private static final String USER_POSTGRESQL	= "postgres";
    
    private static final String URL_ORACLE 		= "jdbc:oracle:thin:@localhost:1521:tennis";
    private static final String USER_ORACLE		= "oracle";
    
    private static final String PASSWORD		= "Password";

    public static void main(String... args){
        
        // Données pour certaines requetes
        long		idJoueur 	= 46L;
        String 		newNom 		= "Test";	//Wozniacki
        String 		newPrenom 	= "Test";	//Caroline
        String 		newSexe		= "H";			
        
        // Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
        try {
        	// Le code commenté ci-dessous serait nécessaire avant Java 7
            // pour charger le pilote JDBC explicitement.
            // Class.forName(DRIVER_CLASS_NAME);
        	
            // Les lignes suivantes sont des exemples de connexion à différentes bases de données.
            // Vous devez en décommenter une selon le SGBD que vous utilisez.            
            
        	// -------------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DRIVER MANAGER (SANS DE POOL DE CONNEXIONS) ----- //
        	// -------------------------------------------------------------------------------- //
        	// connexion = connectOracleWithDriverManager();		// Connexion à Oracle
            // connexion = connectPostgreSQLWithDriverManager();	// Connexion à PostgreSQL
            // connexion = connectMySQLWithDriverManager();			// Connexion à MySQL
        	
        	// ----------------------------------------------------------------------------- //
        	// ----- CONNEXION AVEC LA DB VIA DATA SOURCE (AVEC DE POOL DE CONNEXIONS) ----- //
        	// ----------------------------------------------------------------------------- //
        	// connexion = connectOracleWithDataSource();			// Connexion à Oracle
            // connexion = connectPostgreSQLWithDataSource();		// Connexion à PostgreSQL
             connexion = connectMySQLWithDataSource();				// Connexion à MySQL
            
            // ---------------------------------- //
        	// ----- EXECUTION DES REQUETES ----- //
            // ---------------------------------- //   
            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
            
            // Jouer avec les données
            readAllJoueur(connexion);
            readJoueurById(connexion,idJoueur);
            //addJoueur(connexion, newNom, newPrenom, newSexe);
            updateJoueur(connexion, idJoueur, newNom, newPrenom);
            //deleteJoueurById(connexion,idJoueur);
            
            // Valider la transaction si tout s'est bien passé
            connexion.commit();
                        
        // Gestion des exceptions SQL.
        } catch (SQLException e) {
        	handleDatabaseException(e, connexion);
        
        // Bloc finally s'exécute toujours, qu'il y ait une exception ou non. 
        // Fermeture de la connexion si elle a été établie.
        } finally {
        	closeConnection(connexion);
        }
    }
    
    private static Connection connectMySQLWithDriverManager() 		throws SQLException {
        return DriverManager.getConnection(URL_MYSQL, USER_MYSQL, PASSWORD);
    }
    private static Connection connectPostgreSQLWithDriverManager() 	throws SQLException {
        return DriverManager.getConnection(URL_POSTGRESQL, USER_POSTGRESQL, PASSWORD);
    }
    private static Connection connectOracleWithDriverManager() 		throws SQLException {
        return DriverManager.getConnection(URL_ORACLE, USER_ORACLE, PASSWORD);
    }
    //
    //
    //
    //
    //
    // Méthode pour gérer les problèmes de connexion à la DB
    
    private static Connection connectMySQLWithDataSource() 			throws SQLException {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setUrl(URL_MYSQL);
    	dataSource.setUsername(USER_MYSQL);
    	dataSource.setPassword(PASSWORD);
        return dataSource.getConnection();
    }
    private static Connection connectPostgreSQLWithDataSource() 	throws SQLException {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setUrl(URL_POSTGRESQL);
    	dataSource.setUsername(USER_POSTGRESQL);
    	dataSource.setPassword(PASSWORD);
        return dataSource.getConnection();
    }
    private static Connection connectOracleWithDataSource() 		throws SQLException {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setUrl(URL_ORACLE);
    	dataSource.setUsername(USER_ORACLE);
    	dataSource.setPassword(PASSWORD);
        return dataSource.getConnection();
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
    //
    //
    //
    //
    //
    // Méthode qui récupère tous les joueurs de la table joueur
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
    
    public static void readAllJoueur(Connection connexion) {
    	
    	String sqlRequest = "SELECT * FROM JOUEUR";
    	
    	try {
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            // Création d'une instance de Statement pour exécuter des requêtes SQL.
            // On utilise un PreparedStatment plutot qu'un Statement car 
            // Meilleur sécurité - Meilleur performance - Meilleur Maintenabilité - Meilleur fonctionnalités
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest);
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            ResultSet result = preparedStatement.executeQuery();
            
            // Itération sur le ResultSet.
            boolean found = false;
            
            // Traitement des résultats. Si au moins une ligne est renvoyée...
            while(result.next()) {
            	found = true;
            	
                // Récupération des données de chaque colonne en utilisant les bons types.
                Long    resultId      = result.getLong("ID");
                String  resultNom     = result.getString("NOM");
                String  resultPrenom  = result.getString("PRENOM");
                String  resultSexe    = result.getString("SEXE");

                // Affichage des données du joueur.
                System.out.println(resultId + " " + resultNom + " " + resultPrenom + " " + resultSexe);
                
            } 
            
            if (!found) {
                System.out.println("Pas d'utilisateur !");
                
            } else {
                System.out.println("Success - Des données ont été trouvées et affichées.");
            }
            
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture des joueurs.");
			System.out.println("(readAllJoueur)");
            e.printStackTrace();
		}
    }
    //
    //
    //
    //
    //
    // Méthode qui récupère un joueur en fonction de son id
    public static void readJoueurById(Connection connexion, long idJoueur) {
    	
    	String sqlRequest = "SELECT * FROM JOUEUR WHERE ID=?";
    	
    	try {
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            // Création d'une instance de Statement pour exécuter des requêtes SQL.
            // On utilise un PreparedStatment plutot qu'un Statement car 
            // Meilleur sécurité - Meilleur performance - Meilleur Maintenabilité - Meilleur fonctionnalités
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setLong(1, idJoueur);
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            ResultSet result = preparedStatement.executeQuery();
            
            // Itération sur le ResultSet.
            boolean found = false;
            
            // Traitement des résultats. Si au moins une ligne est renvoyée...
            while(result.next()) {
            	found = true;
            	
                // Récupération des données de chaque colonne en utilisant les bons types.
                Long    resultId      = result.getLong("ID");
                String  resultNom     = result.getString("NOM");
                String  resultPrenom  = result.getString("PRENOM");
                String  resultSexe    = result.getString("SEXE");

                // Affichage des données du joueur.
                System.out.println(resultId + " " + resultNom + " " + resultPrenom + " " + resultSexe);
                
            } 
            
            if (!found) {
                System.out.println("Pas d'utilisateur !");
                
            } else {
                System.out.println("Success - Des données ont été trouvées et affichées.");
            }
            
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture du joueur.");
			System.out.println("(readJoueurById)");
            e.printStackTrace();
		}
    }
    //
    //
    //
    //
    //
    // Méthode qui update un joueur par rapport à son id
    public static void updateJoueur(Connection connexion, long idJoueur, String newNom, String newPrenom) {
    	
    	String sqlRequest  = "UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?";
    	
    	try {
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            // Création d'une instance de Statement pour exécuter des requêtes SQL.
            // On utilise un PreparedStatment plutot qu'un Statement car 
            // Meilleur sécurité - Meilleur performance - Meilleur Maintenabilité - Meilleur fonctionnalités
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, newNom);
            preparedStatement.setString(2, newPrenom);
            preparedStatement.setLong(3, idJoueur);
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            
            if (result==0) {
                System.out.println("Pas d'utilisateur à cet id !");
                
            } else {
            	// Affichage des données du joueur actualisé
                System.out.println("Success - Les données ont bien été modifié.");
            }
            
		} catch (Exception e) {
			System.out.println("Erreur lors de la modification du joueur.");
			System.out.println("(updateJoueur)");
            e.printStackTrace();
		}
    }
    //
    //
    //
    //
    //
    // Méthode qui update un joueur par rapport à son id
    public static void addJoueur(Connection connexion, String nom, String prenom, String sexe) {
    	
    	String sqlRequest  = "INSERT JOUEUR SET NOM=?, PRENOM=?, SEXE=?";
    	
    	try {
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            // Création d'une instance de Statement pour exécuter des requêtes SQL.
            // On utilise un PreparedStatment plutot qu'un Statement car 
            // Meilleur sécurité - Meilleur performance - Meilleur Maintenabilité - Meilleur fonctionnalités
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);
                   
            // Exécution d'une requête SQL et récupération des résultats dans un ResultSet.
            int result = preparedStatement.executeUpdate();
            
            if (result==0) {
                System.out.println("Aucun utilisateur n'a été ajouté !");
                
            } else {
            	// Affichage des données du joueur actualisé
                System.out.println("Success - Les données ont bien été ajoutés.");
            }
            
		} catch (Exception e) {
			System.out.println("Erreur lors de l'ajout d'un joueur.");
			System.out.println("(addJoueur)");
            e.printStackTrace();
		}
    }
    //
    //
    //
    //
    //
    // Méthode pour supprimer un joueur par son ID
    public static void deleteJoueurById(Connection connexion, long idJoueur) {
    	
    	String sqlRequest = "DELETE FROM JOUEUR WHERE ID = ?";
    	
    	try {
    		
    		// ------------------------------------- //
        	// ----- PREPARATION DE LA REQUETE ----- //
            // ------------------------------------- //
            // Création d'une instance de Statement pour exécuter des requêtes SQL.
            // On utilise un PreparedStatment plutot qu'un Statement car 
            // Meilleur sécurité - Meilleur performance - Meilleur Maintenabilité - Meilleur fonctionnalités
            PreparedStatement preparedStatement = connexion.prepareStatement(sqlRequest);
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
            
		} catch (Exception e) {
			System.out.println("Erreur lors de la suppression du joueur.");
			System.out.println("(deleteJoueurById)");
            e.printStackTrace();
		}
    }
}
