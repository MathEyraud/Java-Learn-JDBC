package com.mycompany.tennis.core.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    
    // Les informations de connexion à la base de données
    private static final String URL_MYSQL	= "jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
    private static final String USER_MYSQL	= "ADMIN";
    private static final String PASSWORD	= "Password";
    
    // DataSource pour gérer le pool de connexions
    private static BasicDataSource dataSource;

    // Initialisation du pool de connexions
    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(URL_MYSQL);
        dataSource.setUsername(USER_MYSQL);
        dataSource.setPassword(PASSWORD);
        
        // Configurations supplémentaires du pool de connexions
        // (facultatif, mais peut être utile en fonction de vos besoins)
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
    }
    
    // Méthode pour obtenir une connexion depuis le pool de connexions
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    // Méthode pour fermer une connexion
    public static void closeConnection(Connection connection) {
    	
        try {
            if (connection != null && !connection.isClosed()) {
            	// Réactiver l'auto-commit pour la prochaine transaction
                connection.setAutoCommit(true); 
                connection.close();
            }
        } catch (SQLException e) {
        	System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
