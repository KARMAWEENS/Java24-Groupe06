package org.movieTheatre.java24groupe06.controllers;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.Network.Event.GetDTOSessionListEvent;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import static org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;
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

    public void initializeMovieDetailsPage(Movie movie) throws CustomExceptions{
        try {
            movieDetailsViewController = new MovieDetailsViewController(this, movie);
            movieDetailsViewController.openOnNewStage();
            movieDetailsViewController.displayMovieDetails();
        } catch (IOException e) {
            AlertManager.showErrorAlert("L'ouverture de la page des détails des films a échouée", e);
            throw new CustomExceptions("Failed to open movie details page" ,e, ErrorCode.MOVIE_DETAIL_ERROR);
        }
        finally {
            movieDetailsViewController.displayMovieDetails();
        }
    }

    @Override
    public void previousBtnClicked() {
        listener.closeMovieDetails();
    }


    @Override
    public void sessionBtnClicked(CreateSessionDTO createSessionDTO) throws CustomExceptions {
        try{
            listener.createTicketStage(createSessionDTO);
        }catch (CustomExceptions  e){
            e.printStackTrace();
            AlertManager.showErrorAlert("Failed to create ticket", e);
            throw new CustomExceptions("Failed to create ticket", e, ErrorCode.TICKET_CREATION_ERROR);
        }
    }

    @Override
    public List<CreateSessionDTO> getDTOSessionList(Movie movie) throws CustomExceptions {
        try {
            objectSocket.write(new GetDTOSessionListEvent(movie));
            return objectSocket.read();
        } catch (IOException | ClassNotFoundException e) {
            AlertManager.showErrorAlert("Erreur lors de la récupéraction de la liste des sessions", e);
            throw new CustomExceptions("Failed to get session list", e, ErrorCode.SESSION_LIST_ERROR);
        }
    }

    public void close() {
        movieDetailsViewController.close();
    }

    public interface Listener {
        void closeMovieDetails();
        void createTicketStage(CreateSessionDTO createSessionDTO) throws CustomExceptions;
    }
}