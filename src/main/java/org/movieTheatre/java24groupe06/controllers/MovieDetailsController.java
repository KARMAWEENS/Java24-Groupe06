package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;

public class MovieDetailsController implements MovieDetailsViewController.Listener{

    private Listener listener;
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void initializeMovieDetailsPage(Movie movie) {

        try {
/*            MovieDetailsController movieDetailsController = new MovieDetailsController();
            movieDetailsController.
            MovieDetailsViewController movieDetailsViewController = MovieDetailsViewController.showInStage(new Stage());
            */

            // On doit faire un ligne aussi longue sinon on return dans le vent si on fait en 2 lignes
            MovieDetailsViewController movieDetailsViewController = new MovieDetailsViewController().showInStage(new Stage());

            movieDetailsViewController.displayMovieDetails(movie);
            movieDetailsViewController.setListener(this);
        } catch (CantLoadFXMLException e) {
            throw new RuntimeException(e);
        }
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