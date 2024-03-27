package org.example.java24groupe06.utils.DataBaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static final String DB_URL = "jdbc:sqlite:./src/main/resources/DataBase/DataBaseCinema.db";

    public static Connection openDatabase() throws SQLException, ClassNotFoundException {
        // Charger le pilote JDBC SQLite
        Class.forName("org.sqlite.JDBC");

        // Établir une connexion à la base de données
        Connection conn = DriverManager.getConnection(DB_URL);

        System.out.println("Connexion à la base de données établie avec succès.");

        return conn;
    }

    public static void closeDatabase(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connexion à la base de données fermée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
