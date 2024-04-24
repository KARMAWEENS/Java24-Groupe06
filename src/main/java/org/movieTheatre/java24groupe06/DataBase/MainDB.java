package org.movieTheatre.java24groupe06.DataBase;

import org.movieTheatre.java24groupe06.DataBase.CRUD.CreateDB;
import org.movieTheatre.java24groupe06.DataBase.CRUD.DeleteDB;
import org.movieTheatre.java24groupe06.DataBase.CRUD.UpdateDB;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies2;
import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.SessionDAO;


import java.sql.Connection;
import java.sql.SQLException;


// PAS UTILISER DONC PAS DE JAVADOC MTN
public class MainDB implements SessionDAO.SessionDAOInterface {
    CreateDB createDB = CreateDB.getInstance();
    DeleteDB deleteDB = DeleteDB.getInstance();
    UpdateDB updateDB = UpdateDB.getInstance();
    public static void main(String[] args) throws SQLException {
        MainDB app = new MainDB();
        app.run();
    }

    public void run() throws SQLException {
        ConnectionSingletonDB connSingleton = ConnectionSingletonDB.getInstance();
        Connection conn = connSingleton.getConnection();

        CreateMovies2 createMovies2 = new CreateMovies2();
        createMovies2.buildMoviesList();

        for (Movie.MovieBuilder movieBuilder : createMovies2.getMoviesBuilderList()){


        }



//            for (Movie movie : createMovies2.getMovieList()){
//                ActorsDAO.getActors(movie.getID());
//            }


        /*CreateMovies createMovies = new CreateMovies();
        createMovies.createHalfFilm();*/
/*        try {
            createAndInsertTables(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        /*
        String movieID ="9";
        String pathImg = "src/main/java/org/example/java24groupe06/views/img/Sleeping Dogs.png";
        String title = "Sleeping Dogs";
        String duration = "110";
        String releaseDate = "2024-03-27";
        String synopsis = "L'ancien inspecteur de la criminelle, Roy Freeman, suit un traitement contre la maladie d'Alzheimer. En effet, il est chargé de réexaminer une affaire de meurtre. Un condamné à mort que Roy a arrêté dix ans auparavant qui clame aujourd'hui son innocence.";
        String isShowing = "1";
        String producer = "Adam Cooper";



        CreateDB.insertIntoTable(conn.getConnection(), "Movies", movieID,pathImg, title, duration, synopsis, isShowing, releaseDate, producer);
        updateDB.updateTable("Movies", new String[]{"pathImg"}, new String[]{"src/main/resources/MoviesPosters/Sleeping Dogs.png"}, "movieID = 9");
        */


          //  getSessio();
            /*SessionDAO sessionDAO = new SessionDAO();
            sessionDAO.getSession();*/
           // System.out.println(sessionDAO.getHoursSession(2));

            //for (int i = 1; i<10; i++){
              //  addMovieSessions(i,i);
            //}

             /* insertMovie("1","src/main/resources/MoviesPosters/LaMalediction.jpg","La Malédiction", "120", "Une jeune Américaine est envoyée à Rome pour commencer une vie au service de l'Église. Elle se retrouve confrontée aux ténèbres qui l'amènent à remettre en question sa foi et à découvrir une terrifiante conspiration qui espère donner naissance au mal incarné.", "1", "2024-04-10", "Arkasha Stevenson");
             insertActor(25, "Nell Tiger Free");
              insertActor(26, "Tawfeek Barhom");
              insertActor(27,"Sônia Braga");
             createDB.insertIntoTable("MoviesGenres", "1","9 ");
            createDB.insertIntoTable("MoviesCasting", "1","25");
            createDB.insertIntoTable("MoviesCasting", "1","26");*/
         //  createDB.insertIntoTable("MoviesCasting", "1","27");
       // createDB.addColumnsToSessionTable();
        //createDB.fillColumnsInSessionTable();



    }


    public void insertMovie(String movieID, String pathImg, String title, String duration, String synopsis, String isShowing, String releaseDate, String producer) throws SQLException {
        String[] values = {movieID, pathImg, title, duration, synopsis, isShowing, releaseDate, producer};
        createDB.insertIntoTable("Movies", values);
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

    private void createSessionTable() throws SQLException {
        createTable("Sessions", "RoomID INT", "MovieID INT", "Hours String");
    }

    public void insertActor(int actorID, String fullName) throws SQLException {
        String[] values = {Integer.toString(actorID), fullName};
        createDB.insertIntoTable("Actors", values);
    }

    public void addMovieSessions(int movieID, int roomID) throws SQLException {
        String[] times = {"12:00", "15:00", "18:00"};
        for (String time : times) {
            createDB.insertIntoTable("Sessions", Integer.toString(roomID), Integer.toString(movieID), time);
        }
    }




}

