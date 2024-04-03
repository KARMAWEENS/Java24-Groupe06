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

/**
 * The Movie class represents a movie in the cinema system.
 * It contains details about the movie such as title, duration, synopsis, release date, producer, image path and showing status.
 * The Movie class uses the Builder pattern for its creation, which allows for more readable and flexible construction of the Movie object.
 */
public class CreateMovies {

    /**
     * Retrieves a list of movies that are currently showing from the database.
     *
     * @return A list of Movie objects that are currently showing.
     * @throws SQLException If there is an error with the database connection or the SQL query.
     * @throws ParseException If there is an error parsing the release date of a movie.
     */
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

    /**
     * Creates a Movie object from a ResultSet row.
     *
     * This method retrieves the data from the ResultSet row and uses it to create a Movie object.
     * It uses the Builder pattern to construct the Movie object, which allows for more readable and flexible construction.
     *
     * @param rs The ResultSet row containing the movie data.
     * @return A Movie object constructed from the ResultSet row.
     * @throws SQLException If there is an error retrieving the data from the ResultSet.
     * @throws ParseException If there is an error parsing the release date of the movie.
     */
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

    /**
     * Retrieves the release date of a movie from a ResultSet row.
     *
     * This method retrieves the release date string from the ResultSet row and parses it into a Date object.
     * It uses the SimpleDateFormat class to parse the string into a Date object.
     * It is crucial to ensure that the date string is in the correct format expected by SimpleDateFormat.
     * If the date string is not properly parsed, a ParseException will be thrown
     *
     * @param rs The ResultSet row containing the movie data.
     * @return A Date object representing the release date of the movie.
     * @throws SQLException If there is an error retrieving the data from the ResultSet.
     * @throws ParseException If there is an error parsing the release date string into a Date object.
     */
    private static Date getReleaseDate(ResultSet rs) throws SQLException, ParseException {
        String releaseDateString = rs.getString("ReleaseDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date releaseDate = format.parse(releaseDateString);
        return releaseDate;
    }


}

