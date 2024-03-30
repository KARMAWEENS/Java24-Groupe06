package org.example.java24groupe06.models;


import org.example.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Movie class represents a movie in the cinema system.
 * It contains details about the movie such as title, duration, synopsis, release date, producer, image path and showing status.
 * The Movie class uses the Builder pattern for its creation, which allows for more readable and flexible construction of the Movie object.
 */
public class CreateMovies {


    public List<Movie> getShowingMovies() throws SQLException, ParseException {

        ConnectionSingletonDB conn =  ConnectionSingletonDB.getInstance();
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movies WHERE isShowing = true";
        // ! C'est un try with ressources pas un try catch
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println();
                movies.add(createMovieObject(rs));
            }
        }
        conn.closeDatabase();
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

