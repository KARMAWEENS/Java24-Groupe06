package org.example.java24groupe06.utils.DataBase.CRUD;

import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.utils.DataBase.Utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateMoviesFromDB implements MovieRepository {

    @Override
    public List<Movie> getShowingMovies(Connection conn) throws SQLException, ParseException {

        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movies WHERE isShowing = true";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                movies.add(createMovieObject(rs));
            }
        }
        return movies;
    }
    private static Movie createMovieObject(ResultSet rs) throws SQLException, ParseException {
        Movie movie = new Movie.MovieBuilder()
                .setPathImg(rs.getString("pathImg"))
                .setTitle(rs.getString("title"))
                .setDuration(rs.getInt("duration"))
                .setSynopsis(rs.getString("synopsis"))
                .setIsShowing(rs.getBoolean("isShowing"))
                .setReleaseDate(getReleaseDate(rs))
                .setProducer(rs.getString("Producer"))
                .build();
        return movie;
    }
    private static Date getReleaseDate(ResultSet rs) throws SQLException, ParseException {
        String releaseDateString = rs.getString("ReleaseDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate = format.parse(releaseDateString);
        return releaseDate;
    }


}

