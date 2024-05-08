package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.server.ObjectSocket;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;
import java.io.IOException;
import java.net.Socket;
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
    public void sessionBtnClicked(int sessionID, Movie movie) {
        listener.createTicketStage(sessionID, movie);
    }
    @Override
    public List<Session> getSession(Movie movie){
        try {
           Socket socketSession = new Socket("localhost",8081);
            ObjectSocket objectSocketSession = new ObjectSocket(socketSession);
            objectSocketSession.write(movie);
            return objectSocketSession.read();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public interface Listener {
         void closeMovieDetailsStage(Stage stage);

        void createTicketStage(int sessionID, Movie movie);
    }
}