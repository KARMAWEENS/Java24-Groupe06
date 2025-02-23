package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MoviesDAO class provides methods for accessing the Movies table in the database.
 */
public class MoviesDAO extends AbstractDAO{
    public List<Movie.MovieBuilder> getMoviesBuilderList() throws DataAccessException {
        String query =String.format("SELECT * FROM Movies WHERE isShowing = true");
        return getListResult(query, rs -> new Movie.MovieBuilder()
                        .setTitle(rs.getString("title"))
                        .setDuration(rs.getInt("duration"))
                        .setID(rs.getInt("movieID"))
                        .setSynopsis(rs.getString("synopsis"))
                        .setReleaseDate(rs.getString("ReleaseDate"))
                        .setProducer(rs.getString("Producer"))
                        .setPathImg(rs.getString("pathImg"))
                        .setIsShowing(rs.getBoolean("isShowing"))
            );
    }
}
