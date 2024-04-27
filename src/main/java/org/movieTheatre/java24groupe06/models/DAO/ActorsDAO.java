package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorsDAO extends AbstractDAO{

    public List<String> getActorsByMovieId(int movieId) throws SQLException {
        String query =String.format("SELECT a.FullName\n" +
                "FROM Actors a\n" +
                "JOIN MoviesCasting mc ON a.actorID = mc.actorID\n" +
                "WHERE mc.movieID = ?", movieId);
        return getListResult(query, rs -> rs.getString("fullName"), movieId);
    }
}
