package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;

import java.sql.*;
import java.util.List;

public class SessionDAO extends AbstractDAO{
    public List<Session> getSession(Movie movie) throws SQLException {
        String query = String.format("SELECT * FROM Sessions WHERE movieID = %s",movie.getID());
        return getListResult(query, rs ->
                new Session(movie, new SeatsRoomLeft(rs.getInt("regularSeatsLeft"),
                                    rs.getInt("HandicapSeatsLeft"),
                                    rs.getInt("VIPSeatsLeft"),
                                    rs.getInt("RoomID")), rs.getString("Hours")));
    }

    public void update(Session session, int nbSelectedAdultSeats, int nbSelectedChildrenSeats, int nbSelectedVIPSeats, int nbSelectedHandicapSeats) {
        String query = "UPDATE Sessions SET regularSeatsLeft = regularSeatsLeft - ?, HandicapSeatsLeft = HandicapSeatsLeft - ?, VIPSeatsLeft = VIPSeatsLeft - ? WHERE RoomID = ? AND Hours = ?";
        try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nbSelectedAdultSeats + nbSelectedChildrenSeats);
            stmt.setInt(2, nbSelectedHandicapSeats);
            stmt.setInt(3, nbSelectedVIPSeats);
            stmt.setInt(4, session.getRoomID());
            stmt.setString(5, session.getHours());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}