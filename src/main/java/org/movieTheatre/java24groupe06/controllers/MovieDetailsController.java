package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.Network.Event.GetDTOSessionListEvent;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;

import java.io.IOException;
import java.util.List;

public class MovieDetailsController implements MovieDetailsViewController.Listener {
    private ObjectSocket objectSocket;
    private Listener listener;
    private MovieDetailsViewController movieDetailsViewController;

    public MovieDetailsController(Listener listener, ObjectSocket objectSocket) {
        this.listener = listener;
        this.objectSocket = objectSocket;
    }

    public void initializeMovieDetailsPage(Movie movie) {
    movieDetailsViewController = new MovieDetailsViewController(this, movie);
        try {
            movieDetailsViewController.openOnNewStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        movieDetailsViewController.displayMovieDetails();
    }

    @Override
    public void previousBtnClicked() {
        listener.closeMovieDetails();
    }


    @Override
    public void sessionBtnClicked(DTOCreateSession dtoCreateSession) {
        listener.createTicketStage(dtoCreateSession);
    }

    @Override
    public List<DTOCreateSession> getDTOSessionList(Movie movie) {
        try {
            GetDTOSessionListEvent getDTOSessionListEvent = new GetDTOSessionListEvent(movie);
            objectSocket.write(getDTOSessionListEvent);
            return objectSocket.read();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() {
        movieDetailsViewController.close();
    }

    public interface Listener {
        void closeMovieDetails();

        void createTicketStage(DTOCreateSession dtoCreateSession);
    }
}