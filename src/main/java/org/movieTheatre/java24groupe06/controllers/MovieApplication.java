package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.PortConfig;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.Network.Event.RequestSessionEvent;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import static org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;

import java.io.IOException;
import java.net.Socket;
/**
 * The MovieApplication class extends the Application class and implements the Listener interface from
 * WelcomePageController, MovieDetailsController, and TicketController. It serves as the main controller
 * for the application, handling the creation and closing of different stages and views.
 */


public class MovieApplication extends Application implements WelcomePageController.Listener, MovieDetailsController.Listener, TicketController.Listener{
    MovieDetailsController movieDetailsController;
    WelcomePageController welcomePageController;
    TicketController ticketController;
    ObjectSocket objectSocket;
    /**
     * The start method is the main entry point for any JavaFX application. It is called after the init method
     * has returned, and after the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * @throws CustomExceptions if an error occurs while initializing the stage.
     */
    @Override
    public void start(Stage stage) throws CustomExceptions{
        try {
            PortConfig portConfig = new PortConfig();
            portConfig.loadConfig();
            Socket socket = new Socket(PortConfig.host, PortConfig.mainPort);
            objectSocket = new ObjectSocket(socket);
        } catch (IOException e) {
            System.err.println("Error while connecting to server : " + e.getMessage());
        }
        welcomePageController = new WelcomePageController(this,objectSocket);
        welcomePageController.initializeMainStage(stage);
    }
    /**
     * The createDetailsMovieStage method creates a new stage for displaying movie details.
     *
     * @param movie the movie for which details are to be displayed.
     * @throws CustomExceptions if an error occurs while creating the stage.
     */

    @Override
    public void createDetailsMovieStage(Movie movie) throws CustomExceptions{
        movieDetailsController = new MovieDetailsController(this,objectSocket);
        movieDetailsController.initializeMovieDetailsPage(movie);
    }
    /**
     * The closeMovieDetails method closes the movie details stage.
     */

    @Override
    public void closeMovieDetails(){
        movieDetailsController.close();
    }

    /**
     * The createTicketStage method creates a new stage for displaying the ticket.
     *
     * @param createSessionDTO the session data transfer object used to create the ticket.
     * @throws CustomExceptions if an error occurs while creating the stage.
     */
    @Override
    public void createTicketStage(CreateSessionDTO createSessionDTO) throws CustomExceptions {
        try {
            objectSocket.write(new RequestSessionEvent(createSessionDTO));
            Session session = objectSocket.read();
            ticketController = new TicketController(this, session,objectSocket);
            ticketController.initializeTicket();
        } catch (CantLoadFXMLException e){
            AlertManager.showErrorAlert("Erreur lors du chargement de la page", e);
            throw new CustomExceptions("Error while creating ticket", e, ErrorCode.TICKET_CREATION_ERROR);
        }catch (IOException e){
            AlertManager.showErrorAlert("Erreur lors de la connexion au serveur", e);
            throw new CustomExceptions("Error while creating ticket", e, ErrorCode.TICKET_CREATION_ERROR);
        }catch (ClassNotFoundException e){
            AlertManager.showErrorAlert("Erreur lors de la création du ticket", e);
            throw new CustomExceptions("Error while creating ticket", e, ErrorCode.TICKET_CREATION_ERROR);
        }
    }
    /**
     * The closeTicketView method closes the ticket stage.
     */

    @Override
    public void closeTicketView(){
        ticketController.close();
}

    /**
     * The main method is the entry point for the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
    }
