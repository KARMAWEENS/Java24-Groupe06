package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Room;
import org.movieTheatre.java24groupe06.models.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

    public List<Session> getSession(Movie movie) throws SQLException {
        List<Session> sessionList = new ArrayList<>();
        String query = String.format("SELECT * FROM Sessions WHERE movieID = %s",movie.getID());
        try {
                ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
            System.out.println(conn);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
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
        default List<Session> getSession(Movie movie) throws SQLException {
            SessionDAO sessionDAO = new SessionDAO();
            return  sessionDAO.getSession(movie);
        }
    }
}