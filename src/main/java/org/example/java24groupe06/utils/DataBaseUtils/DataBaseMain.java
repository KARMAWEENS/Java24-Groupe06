package org.example.java24groupe06.utils.DataBaseUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseMain {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Appeler la méthode statique pour ouvrir la base de données
            conn = DataBaseConnection.openDatabase();

            // Faire d'autres opérations avec la connexion ici...
            try {
                DataBaseCreateTable.createTable(conn, "Movies",
                        "movieID INT PRIMARY KEY",
                        "pathImg VARCHAR(255)",
                        "title VARCHAR(255)",
                        "duration INT",
                        "synopsis TEXT",
                        "isShowing BOOLEAN",
                        "ReleaseDate DATE",
                        "Producer VARCHAR(255)"
                );
                DataBaseCreateTable.createTable(conn,"Genre","genreId INT PRIMARY KEY","genre VARCHAR(255)");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Appeler la méthode statique pour fermer la base de données
            DataBaseConnection.closeDatabase(conn);
        }
    }
}
