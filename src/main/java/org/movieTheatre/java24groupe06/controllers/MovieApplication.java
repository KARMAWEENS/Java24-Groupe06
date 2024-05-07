package org.movieTheatre.java24groupe06.controllers;
import javafx.application.Application;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;


public class MovieApplication extends Application implements MainPageController.Listener, MovieDetailsController.Listener, TicketController.Listener{
    MovieDetailsController movieDetailsController;
    MainPageController mainPageController;
    TicketController ticketController;
    @Override
    public void start(Stage stage) {
        mainPageController = new MainPageController(this);
        mainPageController.initializeMainStage(stage);
    }

    @Override
    public void createDetailsMovieStage(Movie movie)  {
        movieDetailsController = new MovieDetailsController(this);
        movieDetailsController.initializeMovieDetailsPage(movie);
    }

    @Override
    public void closeMovieDetailsStage(Stage movieDetailsStage) {
        movieDetailsStage.close();
    }

    @Override
    public void createTicketStage(Session session) {
        System.out.println(session.getSessionID());
       ticketController = new TicketController(this, session);
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