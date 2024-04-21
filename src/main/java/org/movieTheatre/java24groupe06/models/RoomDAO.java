package org.movieTheatre.java24groupe06.models;

import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    private ConnectionSingletonDB connectionSingletonDB;
    public RoomDAO() {
        this.connectionSingletonDB = ConnectionSingletonDB.getInstance();
    }
    public Room getSession(int RoomID) throws SQLException {
        Room room = null;
        String query = "SELECT * FROM Rooms WHERE  roomID = ?";
        Connection conn = connectionSingletonDB.getConnection();

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, RoomID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
              int nbRegularSeats = rs.getInt("nbRegularSeats");
              int nbHandicapSeats = rs.getInt("nbHandicapSeats");
              int nbVIPSeats = rs.getInt("nbVIPSeats");
              room = new Room(nbRegularSeats, nbHandicapSeats, nbVIPSeats, RoomID);
            }
        } catch (SQLException e) {
            System.out.println("Error getting session hours: " + e.getMessage());
            throw e;
        }
        return room;
    }

}
