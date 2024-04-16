package org.movieTheatre.java24groupe06.models;

import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.views.AlertManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDAO { //DAO = Data Access Object (to access the data in DB)

    public List<Movie> getShowingMovies() throws SQLException {

        List<Movie> movies = new ArrayList<>();
        // Todo faire si aucun film a l'affiche
        String query = "SELECT * FROM Movies WHERE isShowing = true";
        try (
             ConnectionSingletonDB conn = ConnectionSingletonDB.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(createMovieObject(rs, conn));
            }
        }
        return movies;
    }
    private List<String> getActors(ResultSet rs,ConnectionSingletonDB conn) throws SQLException {
        List<String> actorsList = new ArrayList<>();
        int movieId = rs.getInt("movieID");
        String query =String.format("SELECT a.FullName\n" +
                "FROM Actors a\n" +
                "JOIN MoviesCasting mc ON a.actorID = mc.actorID\n" +
                "WHERE mc.movieID = %s;", movieId);
        // ! C'est un try with ressources pas un try catch
        System.out.println("avant preparedStatement");
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs2 = stmt.executeQuery()) {
            while (rs2.next()) {
                actorsList.add(rs2.getString("fullName"));

            }
        } catch (SQLException e){
            // Besoin de rien faire
            // Renverra un list vide
            // Si on fait un throw, ca remonte l erreur donc peut pas creer obj
            // Si on fait alert ca fait plein d alerte

        }
        // COMPRENDRE POURQUOI QUAND Y A CONN.CLOSEDATABASE CA MARCHE PAS
        // conn.closeDatabase();
        System.out.println(actorsList);
        return actorsList;
    }
    private  List<String> getGenres(ResultSet rs,ConnectionSingletonDB conn) throws SQLException {
        List<String> genresList = new ArrayList<>();
        int movieId = rs.getInt("movieID");
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
        } catch (SQLException e){
            // Besoin de rien faire
            // Renverra un list vide
            // Si on fait un throw, ca remonte l erreur donc peut pas creer obj
            // Si on fait alert ca fait plein d alerte
        }
        System.out.println(genresList);
        return genresList;
    }

    private Movie createMovieObject(ResultSet rs, ConnectionSingletonDB conn) throws SQLException {
        Movie movie = new Movie.MovieBuilder()
                .setTitle(rs.getString("title"))
                .setDuration(rs.getInt("duration"))
                .setSynopsis(rs.getString("synopsis"))
                .setIsShowing(rs.getBoolean("isShowing"))
                .setReleaseDate(rs.getString("ReleaseDate"))
                .setPathImg(rs.getString("pathImg"))
                .setProducer(rs.getString("Producer"))
                .setActors(getActors(rs, conn))
                .setGenre(getGenres(rs, conn))
                .build();
        return movie;
    }
}