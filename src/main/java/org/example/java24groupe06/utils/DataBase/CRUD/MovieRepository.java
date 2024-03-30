package org.example.java24groupe06.utils.DataBase.CRUD;

import org.example.java24groupe06.models.Movie;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface MovieRepository {

    List<Movie> getShowingMovies(Connection conn) throws SQLException, ParseException;

}
