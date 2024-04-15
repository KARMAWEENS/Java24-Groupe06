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

    public List<Movie> getShowingMovies() throws SQLException {

        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movies WHERE isShowing = true";
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getInstance();
             PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(createMovieObject(rs, conn));
            }
        }
        return movies;
    }

    private List<String> getColumnValues(String columnName, String tableName, String idColumnName, int id, ConnectionSingletonDB conn) throws SQLException {
        List<String> valuesList = new ArrayList<>();
        String query = String.format("SELECT %s FROM %s WHERE %s = %s", columnName, tableName, idColumnName, id);
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                valuesList.add(rs.getString(columnName));
            }
        }
        return valuesList;
    }

    private List<String> getActors(ResultSet rs, ConnectionSingletonDB conn) throws SQLException {
        int movieId = rs.getInt("movieID");
        return getColumnValues("FullName", "Actors", "actorID", movieId, conn);
    }

    private List<String> getGenres(ResultSet rs, ConnectionSingletonDB conn) throws SQLException {
        int movieId = rs.getInt("movieID");
        return getColumnValues("genre", "Genres", "genreID", movieId, conn);
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