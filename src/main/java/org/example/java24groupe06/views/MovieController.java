package org.example.java24groupe06.views;

import org.example.java24groupe06.models.Movie;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class MovieController {


    public void showMovies(List<Movie> movies) throws SQLException, ClassNotFoundException, ParseException {

        for(Movie movie : movies){
            System.out.println(movie);
            System.out.println(movie.getTitle());
            System.out.println(movie.getDuration());
        }
    }
}
