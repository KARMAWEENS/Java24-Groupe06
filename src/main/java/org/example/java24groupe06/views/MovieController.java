package org.example.java24groupe06.views;

import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.utils.DataBase.CRUD.MovieRepository;

import org.example.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void showMovies() throws SQLException, ClassNotFoundException, ParseException {
        ConnectionSingletonDB conn = ConnectionSingletonDB.getInstance();
        List<Movie> movies = movieRepository.getShowingMovies(conn);
        // On passe les movies a la vue

        for(Movie movie : movies){
            System.out.println(movie.getTitle());
            System.out.println(movie.getDuration());
        }
    }
}
