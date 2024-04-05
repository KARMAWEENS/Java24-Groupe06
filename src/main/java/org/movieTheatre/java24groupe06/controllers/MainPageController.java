package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.models.MovieModel;
import org.movieTheatre.java24groupe06.views.MainPageView;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;

public interface MainPageController {

    void initialize(MainPageView view, MovieModel model);
    void showMainPage() throws SQLException, ParseException, FileNotFoundException;

}
