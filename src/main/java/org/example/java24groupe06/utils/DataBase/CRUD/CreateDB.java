package org.example.java24groupe06.utils.DataBase.CRUD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The CreateDB class provides methods for creating tables and inserting data into tables in a database.
 * It uses the JDBC API for interacting with the database.
 */
public class CreateDB {

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
    public static void createTable(Connection conn, String tableName, String... attributes) throws SQLException {
        String query = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, String.join(", ", attributes));

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Table " + tableName + " created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Inserts data into a table in the database.
     *
     * This method constructs a SQL INSERT INTO statement using the provided table name and attributes,
     * and executes the statement using the provided Connection object.
     *
     * @param conn The Connection object to use for executing the SQL statement.
     * @param tableName The name of the table to insert data into.
     * @param attributes The data to insert into the table, specified as a varargs parameter.
     * @throws SQLException If there is an error executing the SQL statement.
     */
    public static void insertIntoTable(Connection conn, String tableName, String... attributes) throws SQLException {
        String query = String.format("INSERT INTO %s (%s)", tableName, String.join(", ", attributes));

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Data inserted into table " + tableName + " successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting data into table: " + e.getMessage());
            throw e;
        }
    }



}

