package org.movieTheatre.java24groupe06.DataBase.CRUD;
import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

/**
 * The CreateDB class provides methods for creating tables and inserting data into tables in a database.
 * It uses the JDBC API for interacting with the database.
 */
public class CreateDB {

    private static CreateDB instance;
    private ConnectionSingletonDB connectionSingletonDB ;

    public CreateDB(){
        this.connectionSingletonDB = ConnectionSingletonDB.getCurrent();
    }
    public static CreateDB getInstance(){
        if(instance == null){
            instance = new CreateDB();
        }
        return instance;
    }

    public void createTable(String tableName, String... attributes) throws SQLException {
        Connection conn = connectionSingletonDB.getConnection();
        String query = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, String.join(", ", attributes));
        try (Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(query);
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }

    public void addColumnsToSessionTable() {
        Connection conn = connectionSingletonDB.getConnection();
        String[] sqlStatements = {
                "ALTER TABLE Sessions ADD COLUMN VIPSeatsLeft INTEGER",
                "ALTER TABLE Sessions ADD COLUMN regularSeatsLeft INTEGER",
                "ALTER TABLE Sessions ADD COLUMN HandicapSeatsLeft INTEGER"
        };

        for (String sql : sqlStatements) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void fillColumnsInSessionTable() {
        Connection conn = connectionSingletonDB.getConnection();
        String sql = "UPDATE Sessions SET VIPSeatsLeft = 15, regularSeatsLeft = 200, HandicapSeatsLeft = 5";

        try (Statement stmt = conn.createStatement()) {
            // execute the query
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertIntoTable (String tableName, String... values) throws SQLException {
        Connection conn = connectionSingletonDB.getConnection();
        // Construire la partie de la requête pour les valeurs
        String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
        // Construire la requête SQL avec des paramètres de requête préparée
        String query = String.format("INSERT INTO %s VALUES (%s)", tableName, placeholders);
        System.out.println("querryyyy" + query);
        System.out.println(placeholders);
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Remplacer les paramètres de requête préparée par les valeurs réelles
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]);
            }
            // Exécuter la requête
            pstmt.executeUpdate();
            System.out.println("Data inserted into table " + tableName + " successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting data into table: " + e.getMessage());
            throw e;
        }
    }






}

