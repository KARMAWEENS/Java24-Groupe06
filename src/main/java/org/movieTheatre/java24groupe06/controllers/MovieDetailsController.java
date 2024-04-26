package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;
import java.io.IOException;
public class MovieDetailsController implements MovieDetailsViewController.Listener{

    private Listener listener;
    public MovieDetailsController(Listener listener){
        this.listener = listener;
    }

    public void initializeMovieDetailsPage(Movie movie) {
            MovieDetailsViewController movieDetailsViewController = new MovieDetailsViewController(this,movie);
        try {
            movieDetailsViewController.openOnNewStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        movieDetailsViewController.displayMovieDetails(movie);
    }

    @Override
    public void previousBtnClicked(Stage stage) {
    listener.closeMovieDetailsStage(stage);
    }

    @Override
    public void sessionBtnClicked(Session session) {
        listener.createTicketStage(session);
    }

    public interface Listener {
         void closeMovieDetailsStage(Stage stage);

        void createTicketStage(Session session);
    }
}