package org.example.java24groupe06.utils.DataBase.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDB {

    public static void deleteTable(Connection conn, String tableName) {
        try {
            Statement statement = conn.createStatement();

            // Exécutez ici votre instruction SQL pour supprimer la table
            String deleteTableQuery = "DROP TABLE IF EXISTS " + tableName;
            statement.executeUpdate(deleteTableQuery);

            System.out.println("Table " + tableName + " supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
