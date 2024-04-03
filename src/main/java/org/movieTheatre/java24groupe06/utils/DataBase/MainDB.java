package org.movieTheatre.java24groupe06.utils.DataBase;

import org.movieTheatre.java24groupe06.utils.DataBase.CRUD.CreateDB;
import org.movieTheatre.java24groupe06.utils.DataBase.CRUD.DeleteDB;
import org.movieTheatre.java24groupe06.utils.DataBase.CRUD.UpdateDB;
import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;


import java.sql.Connection;
import java.sql.SQLException;


// PAS UTILISER DONC PAS DE JAVADOC MTN
public class MainDB {
    CreateDB createDB = CreateDB.getInstance();
    DeleteDB deleteDB = DeleteDB.getInstance();
    UpdateDB updateDB = UpdateDB.getInstance();
    public static void main(String[] args) {
        MainDB app = new MainDB();
        app.run();
    }

    public void run() {

        try {

            String movieID ="9";
            String pathImg = "src/main/java/org/example/java24groupe06/views/img/Sleeping Dogs.png";
            String title = "Sleeping Dogs";
            String duration = "110";
            String releaseDate = "2024-03-27";
            String synopsis = "L'ancien inspecteur de la criminelle, Roy Freeman, suit un traitement contre la maladie d'Alzheimer. En effet, il est chargé de réexaminer une affaire de meurtre. Un condamné à mort que Roy a arrêté dix ans auparavant qui clame aujourd'hui son innocence.";
            String isShowing = "1";
            String producer = "Adam Cooper";

            //CreateDB.insertIntoTable(conn.getConnection(), "Movies", movieID,pathImg, title, duration, synopsis, isShowing, releaseDate, producer);
            updateDB.updateTable("Movies", new String[]{"pathImg"}, new String[]{"src/main/resources/MoviesPosters/Sleeping Dogs.png"}, "movieID = 9");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   private void createAndInsertTables(Connection conn) throws SQLException {
        createTable( "Movies", "movieID INT PRIMARY KEY", "pathImg VARCHAR(255)", "title VARCHAR(255)", "duration INT", "synopsis TEXT", "isShowing BOOLEAN", "ReleaseDate DATE", "Producer VARCHAR(255)");
        createTable( "Genres", "genreId INT PRIMARY KEY", "genre VARCHAR(255)");
        createTable( "MoviesGenres", "movieID INT", "genreID INT", "FOREIGN KEY (movieID) REFERENCES Movies(movieID)", "FOREIGN KEY (genreID) REFERENCES Genres(genreID)", "PRIMARY KEY (movieID, genreID)");
        createTable( "Actors", "actorId INT PRIMARY KEY", "fullName VARCHAR(255)");
        createTable( "MoviesCasting", "movieID INT", "actorID INT", "FOREIGN KEY (movieID) REFERENCES Movies(movieID)", "FOREIGN KEY (actorID) REFERENCES Actors(actorID)", "PRIMARY KEY (movieID, actorID)");

        insertIntoTable( "Genres", "genreID, genre) VALUES (5, 'action'");
        insertIntoTable( "MoviesGenres", "movieID, genreID) VALUES (5,2)");
    }

    private void createTable( String tableName, String... columns) throws SQLException {
        createDB.createTable(tableName, columns);
    }
    private void insertIntoTable( String tableName, String values) throws SQLException {
        createDB.insertIntoTable( tableName, values);
    }
}
