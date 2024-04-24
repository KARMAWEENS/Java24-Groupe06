package org.movieTheatre.java24groupe06.models;


import org.movieTheatre.java24groupe06.models.DAO.MovieDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * The Movie class represents a movie in the cinema system.
 * It contains details about the movie such as title, duration, synopsis, release date, producer, image path and showing status.
 * The Movie class uses the Builder pattern for its creation, which allows for more readable and flexible construction of the Movie object.
 */
public class CreateMovies implements MovieModel {

    MovieDAOOOO movieDAO;

    public CreateMovies() {
        movieDAO = new MovieDAOOOO();
    }

    // En soit inutile pour l'instant faut voir ce que Louis a derriere sa pti caboche
    @Override
    public List<Movie> getShowingMovies() throws SQLException {
        return movieDAO.getShowingMovies();
    }

}
