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


public class MovieApplication extends Application implements MainPageController.Listener, MovieDetailsController.Listener, TicketController.Listener,ReadTicketThread.Listener{
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
// TODO peut etre se passe le DTO depuis le debut

        try {
            // C est liee a CreateSessionHandlerThread
            Socket socket = new Socket("localhost", 8083);
            ObjectSocket objectSocket = new ObjectSocket(socket);
            // On cree le DTO avec les infos du button clicked
            DTOCreateSession dtoCreateSession = new DTOCreateSession(sessionID,movie);
            // On envoie l'objet DTO
            objectSocket.write(dtoCreateSession);
            // On attend de recevoir la session
            Session session = objectSocket.read();

            ticketController = new TicketController(this, session);
            ticketController.initializeTicket();
            // On lance un truc qui attend que qq un de la meme session achete
            Thread thread = new Thread(new ReadTicketThread(objectSocket, session, this));
            thread.start();
        } catch (CantLoadFXMLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void updateUITicketBought() {
        ticketController.ticketsBoughtUpdateUI();
    }
}