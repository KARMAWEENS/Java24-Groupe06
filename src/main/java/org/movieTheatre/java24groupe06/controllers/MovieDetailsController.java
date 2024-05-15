package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.server.NetworkGetSession;
import org.movieTheatre.java24groupe06.server.ObjectSocket;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class MovieDetailsController implements MovieDetailsViewController.Listener{
    private ObjectSocket objectSocket;
    private Listener listener;
    public MovieDetailsController(Listener listener,ObjectSocket objectSocket){
        this.listener = listener;
        this.objectSocket = objectSocket;
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
            // On se connection a SessionHandlerThread
            // On donne le film pour recevoir les sessions
            NetworkGetSession networkGetSession = new NetworkGetSession(movie);
            objectSocket.write(networkGetSession);
            // On return quand on a recu les sessions du film en parametres
            return objectSocket.read();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public interface Listener {
         void closeMovieDetailsStage(Stage stage);

        void createTicketStage(int sessionID, Movie movie);
    }
}