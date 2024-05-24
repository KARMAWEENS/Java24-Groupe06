package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.SQLException;
import java.util.List;

/**
 * The ActorsDAO class provides methods for accessing the Actors table in the database.
 */
public class ActorsDAO extends AbstractDAO {

    /**
     * Retrieves a list of actors for a given movie ID.
     *
     * @param movieId the ID of the movie to retrieve actors for.
     * @return a list of actors for the given movie ID.
     * @throws SQLException if an error occurs while executing the query.
     * @throws DataAccessException if an error occurs while executing the query.
     */
    public List<String> getActorsByMovieId(int movieId) throws SQLException, DataAccessException {

        String query = "SELECT a.FullName\n" +
                "FROM Actors a\n" +
                "JOIN MoviesCasting mc ON a.actorID = mc.actorID\n" +
                "WHERE mc.movieID = ?";
        List<String> actors = getListResult(query, rs -> rs.getString("fullName"), movieId);
        if (actors.isEmpty()) {
            throw new SQLException("pas d'acteur trouvé pour l'ID: " + movieId);
        }
        return actors;
    }
}
