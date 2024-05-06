package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
        movieDetailsViewController.displayMovieDetails();
    }

    @Override
    public void previousBtnClicked(Stage stage) {
    listener.closeMovieDetailsStage(stage);
    }

    @Override
    public void sessionBtnClicked(Session session) {
        listener.createTicketStage(session);
    }
    @Override
    public List<Session> getSession(Movie movie){
        SessionDAO sessionDAO = new SessionDAO();
        try {
             return sessionDAO.getSession(movie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public interface Listener {
         void closeMovieDetailsStage(Stage stage);

        void createTicketStage(Session session);
    }
}