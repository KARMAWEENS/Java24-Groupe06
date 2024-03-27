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
                DataBaseCreateTable.createTable(conn,"Genres","genreId INT PRIMARY KEY ","genre VARCHAR(255)");
                DataBaseCreateTable.createTable(conn,"MoviesGenres",
                        "movieID INT",
                        "genreID INT",
                        "FOREIGN KEY (movieID) REFERENCES Movies(movieID)",
                        "FOREIGN KEY (genreID) REFERENCES Genres(genreID)",
                        "PRIMARY KEY (movieID, genreID)");
                DataBaseCreateTable.createTable(conn,"Actors","actorId INT PRIMARY KEY","fullName VARCHAR(255)");
                DataBaseCreateTable.createTable(conn,"MoviesCasting",
                        "movieID INT",
                        "actorID INT",
                        "FOREIGN KEY (movieID) REFERENCES Movies(movieID)",
                        "FOREIGN KEY (actorID) REFERENCES Actors(actorID)",
                        "PRIMARY KEY (movieID, actorID)");

           //     DataBaseCreateTable.InsertTable(conn," Movies","movieID, title, duration, synopsis, ReleaseDate, Producer, pathImg, isShowing) VALUES (1, 'Titre du Film', 120, 'Ceci est un synopsis du film.', '2024-03-27', 'Nom du Producteur', 'chemin/vers/image.jpg', true");
                DataBaseCreateTable.InsertTable(conn," Genres","genreID, genre) VALUES (2, 'action' ");
                DataBaseCreateTable.InsertTable(conn," MoviesGenres", "movieID, genreID) VALUES (1,2");


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
