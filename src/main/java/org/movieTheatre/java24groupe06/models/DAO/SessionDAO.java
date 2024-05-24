package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBaseSingleton.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.sql.*;
import java.util.List;
/**
 * The SessionDAO class provides methods for accessing the Sessions table in the database.
 */
public class SessionDAO extends AbstractDAO {
    public List<CreateSessionDTO> getDTOSessionList(Movie movie) throws DataAccessException {
        String query = String.format("SELECT * FROM Sessions WHERE movieID = %s", movie.getID());
        return getListResult(query, rs ->
                new CreateSessionDTO(rs.getInt("SessionID"), movie, rs.getString("Time")));
    }
    /**
     * Retrieves a list of session DTOs for a given movie.
     *
     * @return a list of session DTOs.
     * @throws DataAccessException if an error occurs while executing the query.
     */

    public SeatsRoomLeft getSeatsRoomLeftBySessionId(Session session) throws  DataAccessException {
        String query = String.format("SELECT * FROM Sessions WHERE SessionID =%s",session.getSessionID());
        return getSingleResult(query, rs ->
                new SeatsRoomLeft(
                        rs.getInt("regularSeatsLeft"),
                        rs.getInt("HandicapSeatsLeft"),
                        rs.getInt("VIPSeatsLeft")
                ));
    }
    /**
     * Retrieves a session by its ID.
     *
     * @param sessionID the ID of the session to retrieve.
     * @return the session with the given ID.
     * @throws DataAccessException if an error occurs while executing the query.
     */

    public Session getSessionBySessionId(int sessionID, Movie movie) throws  DataAccessException {
        String query = String.format("SELECT * FROM Sessions WHERE SessionID =%s",sessionID);
        return  getSingleResult(query, rs ->
                new Session(sessionID, movie,
                        new SeatsRoomLeft(
                                rs.getInt("regularSeatsLeft"),
                                rs.getInt("HandicapSeatsLeft"),
                                rs.getInt("VIPSeatsLeft")),
                        rs.getString("Time")));
    }
    /**
     * Retrieves a list of sessions for a given movie.
     *
     * @return a list of sessions for the given movie.
     * @throws DataAccessException if an error occurs while executing the query.
     */

    public void updateSeats(Session session, int nbRegularSeats, int nbSelectedVIPSeats, int nbSelectedHandicapSeats) {
        String query = "UPDATE Sessions SET regularSeatsLeft = regularSeatsLeft - ?, HandicapSeatsLeft = HandicapSeatsLeft - ?, VIPSeatsLeft = VIPSeatsLeft - ? WHERE SessionID = ?";
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nbRegularSeats);
            stmt.setInt(2, nbSelectedHandicapSeats);
            stmt.setInt(3, nbSelectedVIPSeats);
            stmt.setInt(4, session.getSessionID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour des places restantes: " + e.getMessage());
            e.printStackTrace();
        }
    }

}