package org.movieTheatre.java24groupe06.models;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface MovieModel {

    List<Movie> getShowingMovies() throws SQLException, ParseException;

}
