package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.DataBase.Utils.ConnectionSingletonDB;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements InterfaceDAO {

    @Override
    public List<Movie.MovieBuilder> getDB() throws SQLException {
        ResultSet result;
        List<Movie.MovieBuilder> movieList = new ArrayList<>();
        String query = "SELECT * FROM Movies WHERE isShowing = true";
        try (
                ConnectionSingletonDB conn = ConnectionSingletonDB.getInstance()){
            PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
            result = rs;
            while (result.next()){
             movieList.add(createMovieObject2(result));
            }
        }
        return movieList;
    }
    private Movie.MovieBuilder createMovieObject2(ResultSet rs) throws SQLException {
        Movie.MovieBuilder movieBuilder = new Movie.MovieBuilder()
                .setTitle(rs.getString("title"))
                .setDuration(rs.getInt("duration"))
                .setSynopsis(rs.getString("synopsis"))
                .setIsShowing(rs.getBoolean("isShowing"))
                .setReleaseDate(rs.getString("ReleaseDate"))
                .setPathImg(rs.getString("pathImg"))
                .setProducer(rs.getString("Producer"))
                .setID(rs.getInt("movieID"));
        return movieBuilder;
    }
}
