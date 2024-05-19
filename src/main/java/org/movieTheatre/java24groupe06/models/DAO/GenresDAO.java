package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


// TODO on peut faire un abstractDAO qui contient le getDB de genre et de actor
//  c est les memes juste string qui change
public class GenresDAO extends AbstractDAO{

    public List<String> getGenresByMovieId(int movieID) throws SQLException{
        String query = String.format("SELECT g.genre\n" +
                "FROM Genres g\n" +
                "JOIN MoviesGenres mg ON g.genreID = mg.genreID\n" +
                "WHERE mg.movieID = ?;", movieID);
        List<String> genres = getListResult(query, rs -> rs.getString("genre"), movieID);
        if (genres.isEmpty()) {
            return new ArrayList<>();
        }

        return genres;
    }
}
