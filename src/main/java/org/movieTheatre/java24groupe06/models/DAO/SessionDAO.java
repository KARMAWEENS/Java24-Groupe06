package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;

import java.sql.*;
import java.util.List;

public class SessionDAO extends AbstractDAO {
    public List<DTOCreateSession> getDTOSessionList(Movie movie) throws SQLException {
        String query = String.format("SELECT * FROM Sessions WHERE movieID = %s", movie.getID());
        return getListResult(query, rs ->
                new DTOCreateSession(rs.getInt("SessionID"), movie, rs.getString("Time")));
    }

    public SeatsRoomLeft getSeatsRoomLeftBySessionId(Session session) throws SQLException {
        String query = String.format("SELECT * FROM Sessions WHERE SessionID =%s",session.getSessionID());
        return getSingleResult(query, rs ->
                new SeatsRoomLeft(
                        rs.getInt("regularSeatsLeft"),
                        rs.getInt("HandicapSeatsLeft"),
                        rs.getInt("VIPSeatsLeft")
                ));
    }

    public Session getSessionBySessionId(int sessionID, Movie movie) throws SQLException {
        String query = String.format("SELECT * FROM Sessions WHERE SessionID =%s",sessionID);
        return  getSingleResult(query, rs ->
                new Session(sessionID, movie,
                        new SeatsRoomLeft(
                                rs.getInt("regularSeatsLeft"),
                                rs.getInt("HandicapSeatsLeft"),
                                rs.getInt("VIPSeatsLeft")),
                        rs.getString("Time")));
    }

    public void update(Session session, int nbRegularSeats, int nbSelectedVIPSeats, int nbSelectedHandicapSeats) {
        String query = "UPDATE Sessions SET regularSeatsLeft = regularSeatsLeft - ?, HandicapSeatsLeft = HandicapSeatsLeft - ?, VIPSeatsLeft = VIPSeatsLeft - ? WHERE SessionID = ?";
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nbRegularSeats);
            stmt.setInt(2, nbSelectedHandicapSeats);
            stmt.setInt(3, nbSelectedVIPSeats);
            stmt.setInt(4, session.getSessionID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}