package org.example.java24groupe06.utils.DataBase;

import org.example.java24groupe06.utils.DataBase.CRUD.CreateDB;
import org.example.java24groupe06.utils.DataBase.CRUD.UpdateDB;
import org.example.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;


import java.sql.Connection;
import java.sql.SQLException;


// PAS UTILISER DONC PAS DE JAVADOC MTN
public class MainDB {

    public static void main(String[] args) {

    ConnectionSingletonDB conn = ConnectionSingletonDB.getInstance();
        try {
            UpdateDB.updateTable(conn.getConnection(), "Movies", new String[]{"pathImg"}, new String[]{"src/main/resources/MoviesPosters/téléchargement(2).png"}, "movieID = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
   private static void createAndInsertTables(Connection conn) throws SQLException {
        createTable(conn, "Movies", "movieID INT PRIMARY KEY", "pathImg VARCHAR(255)", "title VARCHAR(255)", "duration INT", "synopsis TEXT", "isShowing BOOLEAN", "ReleaseDate DATE", "Producer VARCHAR(255)");
        createTable(conn, "Genres", "genreId INT PRIMARY KEY", "genre VARCHAR(255)");
        createTable(conn, "MoviesGenres", "movieID INT", "genreID INT", "FOREIGN KEY (movieID) REFERENCES Movies(movieID)", "FOREIGN KEY (genreID) REFERENCES Genres(genreID)", "PRIMARY KEY (movieID, genreID)");
        createTable(conn, "Actors", "actorId INT PRIMARY KEY", "fullName VARCHAR(255)");
        createTable(conn, "MoviesCasting", "movieID INT", "actorID INT", "FOREIGN KEY (movieID) REFERENCES Movies(movieID)", "FOREIGN KEY (actorID) REFERENCES Actors(actorID)", "PRIMARY KEY (movieID, actorID)");

        insertIntoTable(conn, "Genres", "genreID, genre) VALUES (5, 'action'");
        insertIntoTable(conn, "MoviesGenres", "movieID, genreID) VALUES (5,2)");
    }

    private static void createTable(Connection conn, String tableName, String... columns) throws SQLException {
        CreateDB.createTable(conn, tableName, columns);
    }
    private static void insertIntoTable(Connection conn, String tableName, String values) throws SQLException {
        CreateDB.insertIntoTable(conn, tableName, values);
    }

    }
