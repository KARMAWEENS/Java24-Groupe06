package org.movieTheatre.java24groupe06.models;

import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

    public List<Session> getSession(Movie movie) throws SQLException {
        List<Session> sessionList = new ArrayList<>();
        String query = String.format("SELECT * FROM Sessions WHERE MovieID = %d",1);


        System.out.println("esr");
        try (
                ConnectionSingletonDB conn = ConnectionSingletonDB.getInstance();
                PreparedStatement pstmt = conn.prepareStatement(query);
           //  pstmt.setInt(1, movie.getID());
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("sd");
            while (rs.next()) {
            String hours= rs.getString("Hours");
            int roomID = rs.getInt("RoomID");
            //TODO interface
                RoomDAO roomDAO = new RoomDAO();
             Room room=  roomDAO.getSession(roomID);
            Session session = new Session(movie,room,hours);
            sessionList.add(session);
            }
        } catch (SQLException e) {
            System.out.println("Error getting session hours: " + e.getMessage());
            throw e;
        }
        System.out.println(sessionList);
        return sessionList;
    }

    public interface SessionDAOInterface{
        default void getSessio(Movie movie) throws SQLException {
            System.out.println("es");
            SessionDAO sessionDAO = new SessionDAO();
         sessionDAO.getSession(movie);
            System.out.println("dfs");
        }
    }
}