package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorsDAO extends AbstractDAO {

    public List<String> getActorsByMovieId(int movieId) throws SQLException {

        String query = "SELECT a.FullName\n" +
                "FROM Actors a\n" +
                "JOIN MoviesCasting mc ON a.actorID = mc.actorID\n" +
                "WHERE mc.movieID = ?";
        List<String> actors = getListResult(query, rs -> rs.getString("fullName"), movieId);
        if (actors.isEmpty()) {
            throw new SQLException("pas d'acteur trouvé pour l'ID: " + movieId);
        }
        return actors;
    }
}
