package org.movieTheatre.java24groupe06.utils.DataBase.CRUD;
import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;

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
        this.connectionSingletonDB = ConnectionSingletonDB.getInstance();
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

    public void insertIntoTable (String tableName, String... values) throws SQLException {
        Connection conn = connectionSingletonDB.getConnection();
        // Construire la partie de la requête pour les valeurs
        String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
        // Construire la requête SQL avec des paramètres de requête préparée
        String query = String.format("INSERT INTO %s VALUES (%s)", tableName, placeholders);

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

    /**
     * Creates a new table in the database if it does not already exist.
     *
     * This method constructs a SQL CREATE TABLE statement using the provided table name and attributes,
     * and executes the statement using the provided Connection object.
     *
     * @param conn The Connection object to use for executing the SQL statement.
     * @param tableName The name of the table to create.
     * @param attributes The attributes of the table to create, specified as a varargs parameter.
     * @throws SQLException If there is an error executing the SQL statement.
     */


    //!!!! Y A MOYEN QU ON DOIVE CHANGER CONNECTION CONN EN CONNECTIONSINGLETON A VOIR PAS SUR

    /**
     * Inserts data into a table in the database.
     *
     * This method constructs a SQL INSERT INTO statement using the provided table name and attributes,
     * and executes the statement using the provided Connection object.
     *
     * @param conn The Connection object to use for executing the SQL statement.
     * @param tableName The name of the table to insert data into.

     * @throws SQLException If there is an error executing the SQL statement.
     */






}

