package org.example.java24groupe06.utils.DataBase.CRUD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {

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

