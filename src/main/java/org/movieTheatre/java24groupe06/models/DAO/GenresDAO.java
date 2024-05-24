package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The GenresDAO class provides methods for accessing the Genres table in the database.
 */
public class GenresDAO extends AbstractDAO{
    /**
     * Retrieves a list of genres for a given movie ID.
     *
     * @param movieID the ID of the movie to retrieve genres for.
     * @return a list of genres for the given movie ID.
     * @throws DataAccessException if an error occurs while executing the query.
     */
    public List<String> getGenresByMovieId(int movieID) throws  DataAccessException {
        String query = String.format("SELECT g.genre\n" +
                "FROM Genres g\n" +
                "JOIN MoviesGenres mg ON g.genreID = mg.genreID\n" +
                "WHERE mg.movieID = ?;", movieID);
        List<String> genres = getListResult(query, rs -> rs.getString("genre"), movieID);
        if (genres.isEmpty()) {
            return new ArrayList<>();
        }

        return genres;
    }
}
