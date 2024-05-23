package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;
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
        try {
            movieDetailsViewController = new MovieDetailsViewController(this, movie);
            movieDetailsViewController.openOnNewStage();
            movieDetailsViewController.displayMovieDetails();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void previousBtnClicked() {
        listener.closeMovieDetails();
    }


    @Override
    public void sessionBtnClicked(CreateSessionDTO createSessionDTO) {
        listener.createTicketStage(createSessionDTO);
    }

    @Override
    public List<CreateSessionDTO> getDTOSessionList(Movie movie) {
        try {
            objectSocket.write(new GetDTOSessionListEvent(movie));
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
        void createTicketStage(CreateSessionDTO createSessionDTO);
    }
}