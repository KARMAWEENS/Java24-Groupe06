package org.movieTheatre.java24groupe06.models.DAO;


import org.movieTheatre.java24groupe06.DataBaseSingleton.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The Listener interface for the TicketController class. It provides methods for closing the ticket view.
 */

public abstract class AbstractDAO {


    /**
     * Retrieves a list of results from a query.
     *
     * @param query the SQL query to execute.
     * @param mapper the RowMapper to use for mapping the ResultSet to objects.
     * @param params the parameters to set in the PreparedStatement.
     * @return a list of objects mapped from the ResultSet.
     * @throws DataAccessException if an error occurs while executing the query.
     */
    protected <T> List<T> getListResult(String query, RowMapper<T> mapper, Object... params) throws  DataAccessException {
        List<T> results = new ArrayList<>();
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
             PreparedStatement stmt = conn.prepareStatement(query, params);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                results.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error executing query: " + query, e);
        }
        return results;
    }

    /**
     * Retrieves a single result from a query.
     *
     * @param query the SQL query to execute.
     * @param mapper the RowMapper to use for mapping the ResultSet to an object.
     * @param params the parameters to set in the PreparedStatement.
     * @return the object mapped from the ResultSet.
     * @throws DataAccessException if an error occurs while executing the query.
     */
    protected <T> T getSingleResult(String query, RowMapper<T> mapper, Object... params) throws DataAccessException {
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
             PreparedStatement stmt = conn.prepareStatement(query, params);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error executing query: " + query, e);
        }
        return null;
    }

    /**
     * The RowMapper interface provides a method for mapping a ResultSet to an object.
     *
     * @param <T> the type of object to map to.
     */
    @FunctionalInterface
    public interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }
}

