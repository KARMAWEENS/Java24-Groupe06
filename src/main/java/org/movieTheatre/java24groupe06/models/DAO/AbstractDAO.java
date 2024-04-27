package org.movieTheatre.java24groupe06.models.DAO;


import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO {

    // Utilisation de ConnectionSingletonDB pour obtenir la connexion
    protected Connection getConnection() throws SQLException {
        return ConnectionSingletonDB.getCurrent().getConnection();
    }

    protected void closeResources(ResultSet rs, PreparedStatement stmt) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode générique pour obtenir un simple élément
    protected <T> T getSingleResult(String query, RowMapper<T> mapper, Object... params) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = prepareStatement(conn, query, params);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return mapper.mapRow(rs);
            }
            return null;
        }
    }

    // Méthode générique pour obtenir une liste d'éléments
    protected <T> List<T> getListResult(String query, RowMapper<T> mapper, Object... params) throws SQLException {
        List<T> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = prepareStatement(conn, query, params);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(conn);
                results.add(mapper.mapRow(rs));
            }
        }
        return results;
    }


    private PreparedStatement prepareStatement(Connection conn, String query, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt;
    }

    public interface RowMapper<T> {
        T mapRow(ResultSet rs) throws SQLException;
    }
}

/*List<String> actorsList = new ArrayList<>();
String query =String.format("SELECT a.FullName\n" +
        "FROM Actors a\n" +
        "JOIN MoviesCasting mc ON a.actorID = mc.actorID\n" +
        "WHERE mc.movieID = %s;", movieID);
// ! C'est un try with ressources pas un try catch
        System.out.println("avant preparedStatement");
        try (
ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
PreparedStatement stmt = conn.prepareStatement(query);
ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
        actorsList.add(rs.getString("fullName"));

        }
        } catch (
SQLException e){
        // Besoin de rien faire
        // Renverra un list vide
        // Si on fait un throw, ca remonte l erreur donc peut pas creer obj
        // Si on fait alert ca fait plein d alerte

        }
        // COMPRENDRE POURQUOI QUAND Y A CONN.CLOSEDATABASE CA MARCHE PAS
        // conn.closeDatabase();
        System.out.println(actorsList);
        return actorsList;
    }*/


/*public   List<String> getDB(int movieID) throws SQLException {
    List<String> genresList = new ArrayList<>();

    String query =String.format("SELECT g.genre\n" +
            "FROM Genres g\n" +
            "JOIN MoviesGenres mg ON g.genreID = mg.genreID\n" +
            "WHERE mg.movieID = %s;", movieID);
    // ! C'est un try with ressources pas un try catch
    try (
            ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {

            genresList.add(rs.getString("genre"));

        }
    } catch (SQLException e){
        // Besoin de rien faire
        // Renverra un list vide
        // Si on fait un throw, ca remonte l erreur donc peut pas creer obj
        // Si on fait alert ca fait plein d alerte
    }
    System.out.println(genresList);
    return genresList;
}*/

/*public List<Movie.MovieBuilder> getDB() throws SQLException {
    ResultSet result;
    List<Movie.MovieBuilder> movieList = new ArrayList<>();
    String query = "SELECT * FROM Movies WHERE isShowing = true";
    try (ConnectionSingletonDB conn = ConnectionSingletonDB.getCurrent()) {
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        result = rs;
        while (result.next()) {
            movieList.add(initializeMovieBuilder(result));
        }
    }
    return movieList;
}*/

/*
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
}*/
