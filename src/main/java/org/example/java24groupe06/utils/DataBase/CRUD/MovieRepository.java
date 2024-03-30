package org.example.java24groupe06.utils.DataBase.CRUD;

import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.utils.DataBase.Utils.ConnectionSingletonDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface MovieRepository {

    List<Movie> getShowingMovies(ConnectionSingletonDB conn) throws SQLException, ParseException;

}
