package org.movieTheatre.java24groupe06.models;


import org.movieTheatre.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Movie class represents a movie in the cinema system.
 * It contains details about the movie such as title, duration, synopsis, release date, producer, image path and showing status.
 * The Movie class uses the Builder pattern for its creation, which allows for more readable and flexible construction of the Movie object.
 */
public class CreateMovies implements MovieModel {

    MovieDAO movieDAO;

    public CreateMovies() {
        movieDAO = new MovieDAO();
    }

    @Override
    public List<Movie> getShowingMovies() throws SQLException {
        return movieDAO.getShowingMovies();
    }

}

