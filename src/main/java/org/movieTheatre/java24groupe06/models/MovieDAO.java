package org.movieTheatre.java24groupe06.models;

import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDAO { //DAO = Data Access Object (to access the data in DB)

    public List<Movie> getShowingMovies() throws SQLException, ParseException {

        ConnectionSingletonDB conn =  ConnectionSingletonDB.getInstance();
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movies WHERE isShowing = true";
        // ! C'est un try with ressources pas un try catch
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {


                movies.add(createMovieObject(rs));

            }
        }
        conn.closeDatabase();
        return movies;
    }

    private static Movie createMovieObject(ResultSet rs) throws SQLException, ParseException {
        Movie movie = new Movie.MovieBuilder()
                .setTitle(rs.getString("title"))
                .setDuration(rs.getInt("duration"))
                .setSynopsis(rs.getString("synopsis"))
                .setIsShowing(rs.getBoolean("isShowing"))
                .setReleaseDate(rs.getString("ReleaseDate"))
                .setPathImg(rs.getString("pathImg"))
                .setProducer(rs.getString("Producer"))
                .setActors(getActors(rs))
                .setGenre(getGenres(rs))
                .build();

        return movie;
    }
    private static List<String> getActors(ResultSet rs) throws SQLException {
        List<String> actorsList = new ArrayList<>();
        int movieId = rs.getInt("movieID");
        ConnectionSingletonDB conn =  ConnectionSingletonDB.getInstance();
        String query =String.format("SELECT a.FullName\n" +
                "FROM Actors a\n" +
                "JOIN MoviesCasting mc ON a.actorID = mc.actorID\n" +
                "WHERE mc.movieID = %s;", movieId);
        // ! C'est un try with ressources pas un try catch

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs2 = stmt.executeQuery()) {
            while (rs2.next()) {

                actorsList.add(rs2.getString("fullName"));

            }
        }
        // COMPRENDRE POURQUOI QUAND Y A CONN.CLOSEDATABASE CA MARCHE PAS
       // conn.closeDatabase();

        return actorsList;
    }
//    private static List<String> getActors(ResultSet rsActors) throws SQLException {
//        List<String> actors = new ArrayList<>();
//        while (rsActors.next()) {
//            actors.add(rsActors.getString("fullName"));
//        }
//        return actors;
//    }
//    private static List<String> getGenres(ResultSet rsGenres) throws SQLException {
//        List<String> genres = new ArrayList<>();
//        while (rsGenres.next()) {
//            genres.add(rsGenres.getString("genre"));
//        }
//        return genres;
//    }

    private static List<String> getGenres(ResultSet rs) throws SQLException {
        List<String> genresList = new ArrayList<>();
        int movieId = rs.getInt("movieID");
        ConnectionSingletonDB conn =  ConnectionSingletonDB.getInstance();
        String query =String.format("SELECT g.genre\n" +
                "FROM Genres g\n" +
                "JOIN MoviesGenres mg ON g.genreID = mg.genreID\n" +
                "WHERE mg.movieID = %s;", movieId);
        // ! C'est un try with ressources pas un try catch

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs2 = stmt.executeQuery()) {
            while (rs2.next()) {

                genresList.add(rs2.getString("genre"));

            }
        }
        // COMPRENDRE POURQUOI QUAND Y A CONN.CLOSEDATABASE CA MARCHE PAS
        // conn.closeDatabase();

        return genresList;
    }

}