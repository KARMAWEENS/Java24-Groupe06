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

/**
 * The MovieDetailsController class implements the Listener interface from the MovieDetailsViewController class.
 * It serves as the controller for the movie details page, handling the creation of the stage and the display of
 * movie details.
 */
public class MovieDetailsController implements MovieDetailsViewController.Listener {
    private ObjectSocket objectSocket;
    private Listener listener;
    private MovieDetailsViewController movieDetailsViewController;

    /**
     * The constructor for the MovieDetailsController class.
     *
     * @param listener the listener for the movie details page.
     * @param objectSocket the object socket for the movie details page.
     */
    public MovieDetailsController(Listener listener, ObjectSocket objectSocket) {
        this.listener = listener;
        this.objectSocket = objectSocket;
    }

    /**
     * The initializeMovieDetailsPage method initializes the movie details page.
     *
     * @param movie the movie for which details are to be displayed.
     * @throws CustomExceptions if an error occurs while initializing the page.
     */
    public void initializeMovieDetailsPage(Movie movie) throws CustomExceptions{
        try {
            movieDetailsViewController = new MovieDetailsViewController(this, movie);
            movieDetailsViewController.openOnNewStage();
            movieDetailsViewController.displayMovieDetails();
        } catch (IOException e) {
            AlertManager.showErrorAlert("L'ouverture de la page des détails des films a échouée", e);
            throw new CustomExceptions("Failed to open movie details page" ,e, ErrorCode.MOVIE_DETAIL_ERROR);
        }
    }

    /**
     * The close method closes the movie details page.
     */
    @Override
    public void previousBtnClicked() {
        listener.closeMovieDetails();
    }


    /**
     * The sessionBtnClicked method creates a new ticket stage.
     *
     * @param createSessionDTO the session to be created.
     * @throws CustomExceptions if an error occurs while creating the ticket.
     */
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

    /**
     * The getDTOSessionList method retrieves the list of sessions.
     *
     * @param movie the movie for which the session list is to be retrieved.
     * @return the list of sessions.
     * @throws CustomExceptions if an error occurs while retrieving the session list.
     */
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

    /**
     * The close method closes the movie details page.
     */
    public void close() {
        movieDetailsViewController.close();
    }

    /**
     * The Listener interface is used to handle the closing of the movie details page and the creation of a ticket stage.
     */
    public interface Listener {
        void closeMovieDetails();
        void createTicketStage(CreateSessionDTO createSessionDTO) throws CustomExceptions;
    }
}