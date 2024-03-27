package org.example.java24groupe06.utils.DataBaseUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseCreateTable {
    public static void createTable(Connection conn, String tableName, String... attributes) throws SQLException {
        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        query.append(tableName).append(" (");

        // Ajoute les attributs à la requête
        for (int i = 0; i < attributes.length; i++) {
            query.append(attributes[i]);
            if (i < attributes.length - 1) {
                query.append(", ");
            }
        }

        query.append(")");

        // Exécute la requête pour créer la table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query.toString());
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }
    public static void InsertTable(Connection conn, String tableName, String... attributes) throws SQLException {
        StringBuilder query = new StringBuilder("INSERT INTO");
        query.append(tableName).append(" (");

        // Ajoute les attributs à la requête
        for (int i = 0; i < attributes.length; i++) {
            query.append(attributes[i]);
            if (i < attributes.length - 1) {
                query.append(", ");
            }
        }

        query.append(")");

        // Exécute la requête pour créer la table
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query.toString());
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }
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
