package com.mycompany.tennis.core.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseExceptionHandler {

    /**
     * Méthode pour gérer les exceptions liées à la base de données.
     * Cette méthode affiche l'erreur et annule toutes les modifications effectuées dans la transaction en cas d'erreur.
     *
     * @param e          L'exception SQL à gérer.
     * @param connection La connexion à la base de données.
     */
    public static void handleException(SQLException e, Connection connection) {
    	
        System.out.println("Erreur de connexion à la base de données.");
        e.printStackTrace();

        // Annuler toutes les modifications effectuées au sein de cette transaction en cas d'erreur.
        try {
            if (connection != null) {
                connection.rollback();
            }
            
        } catch (SQLException rollbackException) {
            System.out.println("Erreur lors de l'annulation des modifications !");
            rollbackException.printStackTrace();
        }
    }
}
