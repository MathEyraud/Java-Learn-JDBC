package com.mycompany.tennis.core;

import java.sql.*;

public class TestDeConnection {
	
	// Données pour la connexion à la DB
    private static final String URL 	 	= "jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private static final String USER		= "ADMIN";
    private static final String PASSWORD	= "Password";

    public static void main(String... args){
        
        // Données pour certaines requetes
        long		idJoueur 	= 1L;
        String 		newNom 		= "TestNom";	//Wozniacki
        String 		newPrenom 	= "TestPrenom";	//Caroline
        
        // Variable pour gérer la connexion à la DB
        Connection connexion = null;
        
        try {
        	// -------------------------------- //
        	// ----- CONNEXION AVEC LA DB ----- //
        	// -------------------------------- //
            // Le code commenté ci-dessous serait nécessaire avant Java 7
            // pour charger le pilote JDBC explicitement.
            // Class.forName(DRIVER_CLASS_NAME);

            // Les lignes suivantes sont des exemples de connexion à différentes bases de données.
            // Vous devez en décommenter une selon le SGBD que vous utilisez.

            // Connexion à Oracle
            // connexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis",user,password);

            // Connexion à PostgreSQL
            // connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis",user,password);

            // Connexion à MySQL avec désactivation de SSL et paramétrage de la timezone (cf URL)
            connexion = DriverManager.getConnection(URL,USER,PASSWORD);
            
            // Vérifie que la transaction c'est bien déroulé (Mode manuel)
            connexion.setAutoCommit(false);
            
            // ---------------------------------- //
        	// ----- EXECUTION DES REQUETES ----- //
            // ---------------------------------- //                     
            readAllJoueur(connexion);
            readJoueurById(connexion,idJoueur);
            updateJoueur(connexion, idJoueur, newNom, newPrenom);
            
            // Valider la transaction si tout s'est bien passé
            connexion.commit();
                        
        // Gestion des exceptions SQL.
        } catch (SQLException e) {
        	
        	// En cas d'erreur, annuler toutes les modifications effectuées au sein de cette transaction
            try {
				connexion.rollback();
			} catch (SQLException e1) {
				System.out.println("Erreur lors de l'annulation des modifications !");
				e1.printStackTrace();
			}
            
            // Puis afficher un message d'erreur
        	handleDatabaseException(e);
        
        // Bloc finally s'exécute toujours, qu'il y ait une exception ou non. 
        // Fermeture de la connexion si elle a été établie.
        } finally {
            
        	// Gestion des exceptions pouvant survenir lors de la fermeture de la connexion.
            try {           
            	
            	// Réactiver l'auto-commit pour la prochaine transaction
                connexion.setAutoCommit(true);
                
                if (connexion!=null) {
                    connexion.close();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //
    //
    //
    //
    //
    // Méthode pour gérer les problèmes de connexion à la DB
    private static void handleDatabaseException(SQLException e) {
        // Ici, nous pouvons centraliser notre gestion des exceptions pour la base de données.
        System.out.println("Erreur de connexion à la base de données.");
        e.printStackTrace();
    }
    //
    //
    //
    //
    //
    // Méthode qui récupère tous les joueurs de la table joueur
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
}
