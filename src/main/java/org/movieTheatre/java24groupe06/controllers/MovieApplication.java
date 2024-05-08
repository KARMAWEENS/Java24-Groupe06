package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.server.ObjectSocket;

import java.io.IOException;
import java.net.Socket;


public class MovieApplication extends Application implements MainPageController.Listener, MovieDetailsController.Listener, TicketController.Listener {
    MovieDetailsController movieDetailsController;
    MainPageController mainPageController;
    TicketController ticketController;

    @Override
    public void start(Stage stage) {
        mainPageController = new MainPageController(this);
        mainPageController.initializeMainStage(stage);
    }

    @Override
    public void createDetailsMovieStage(Movie movie) {
        movieDetailsController = new MovieDetailsController(this);
        movieDetailsController.initializeMovieDetailsPage(movie);
    }

    @Override
    public void closeMovieDetailsStage(Stage movieDetailsStage) {
        movieDetailsStage.close();
    }

    @Override
    public void createTicketStage(int sessionID, Movie movie) {


        try {
            Socket socket = new Socket("localhost", 8083);
            ObjectSocket objectSocket = new ObjectSocket(socket);
            DTOCreateSession dtoCreateSession = new DTOCreateSession(sessionID,movie);
            objectSocket.write(dtoCreateSession);
            Session session = objectSocket.read();
            ticketController = new TicketController(this, session);
            ticketController.initializeTicket();
        } catch (CantLoadFXMLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}