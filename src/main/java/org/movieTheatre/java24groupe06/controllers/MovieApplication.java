package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Application;

import javafx.stage.Stage;


import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;


public class MovieApplication extends Application implements MainPageController.Listener,MovieDetailsController.Listener {

    @Override
    public void start(Stage stage) {
        initializeMainStage(stage);
    }

@Override
    public void createDetailsMovieStage(Movie movie)  {
        MovieDetailsController movieDetailsController = new MovieDetailsController();
        movieDetailsController.initializeMovieDetailsPage(movie);
        movieDetailsController.setListener(this);
    }

    @Override
    public void closeMovieDetailsStage(Stage movieDetailsStage) {
        movieDetailsStage.close();
    }

    @Override
    public void createTicketStage(Session session) {
        TicketController ticketController = new TicketController();
        try {
            ticketController.initializeTicket();
        } catch (CantLoadFXMLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}