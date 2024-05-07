package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO {
    // TODO CETTE CLASS EST POURRI JCROIS ON PEUT LA DELETE
    private ConnectionSingletonDB connectionSingletonDB;
    public RoomDAO() {
        this.connectionSingletonDB = ConnectionSingletonDB.getCurrent();
    }
    public SeatsRoomLeft getRoom(int RoomID) throws SQLException {
        SeatsRoomLeft seatsRoomLeft = null;
        String query = "SELECT * FROM Rooms WHERE  roomID = ?";
        Connection conn = connectionSingletonDB.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, RoomID);
            ResultSet rs = pstmt.executeQuery();
            // TODO CETTE CLASS EST POURRI JCROIS ON PEUT LA DELETE
            while (rs.next()) {
              int nbRegularSeats = rs.getInt("nbRegularSeats");
              int nbHandicapSeats = rs.getInt("nbHandicapSeats");
              int nbVIPSeats = rs.getInt("nbVIPSeats");
              seatsRoomLeft = new SeatsRoomLeft(nbRegularSeats, nbHandicapSeats, nbVIPSeats, RoomID);
            }
        } catch (SQLException e) {
            System.out.println("Error getting session hours: " + e.getMessage());
            throw e;
        }
        return seatsRoomLeft;
    }
    // TODO CETTE CLASS EST POURRI JCROIS ON PEUT LA DELETE
}
