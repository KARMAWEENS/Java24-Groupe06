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


public class MovieApplication extends Application implements WelcomePageController.Listener, MovieDetailsController.Listener, TicketController.Listener{
    MovieDetailsController movieDetailsController;
    WelcomePageController welcomePageController;
    TicketController ticketController;
    ObjectSocket objectSocket;
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

    @Override
    public void createDetailsMovieStage(Movie movie) throws CustomExceptions{
        movieDetailsController = new MovieDetailsController(this,objectSocket);
        movieDetailsController.initializeMovieDetailsPage(movie);
    }

    @Override
    public void closeMovieDetails(){
        movieDetailsController.close();
    }

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

    @Override
    public void closeTicketView(){
        ticketController.close();
}

    public static void main(String[] args) {
        launch(args);
    }
    }
