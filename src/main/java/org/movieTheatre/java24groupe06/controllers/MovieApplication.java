package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.server.NetworkTicketGetSessionAndThread;
import org.movieTheatre.java24groupe06.server.ObjectSocket;

import java.io.IOException;
import java.net.Socket;


public class MovieApplication extends Application implements WelcomePageController.Listener, MovieDetailsController.Listener, TicketController.Listener,ReadTicketThread.Listener{
    MovieDetailsController movieDetailsController;
    WelcomePageController welcomePageController;
    TicketController ticketController;
    ObjectSocket objectSocket;

    @Override
    public void start(Stage stage) {
        try {
          Socket socket = new Socket("localhost",7999);
          objectSocket = new ObjectSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        welcomePageController = new WelcomePageController(this,objectSocket);
        welcomePageController.initializeMainStage(stage);
    }

    @Override
    public void createDetailsMovieStage(Movie movie) {
        movieDetailsController = new MovieDetailsController(this,objectSocket);
        movieDetailsController.initializeMovieDetailsPage(movie);
    }

    @Override
    public void closeMovieDetailsStage(Stage movieDetailsStage) {
        movieDetailsStage.close();
    }

    @Override
    public void createTicketStage(DTOCreateSession dtoCreateSession) {
        try {
            NetworkTicketGetSessionAndThread networkTicketGetSessionAndThread = new NetworkTicketGetSessionAndThread(dtoCreateSession);
            objectSocket.write(networkTicketGetSessionAndThread);
            Session session = objectSocket.read();
            System.out.println(session.getNbRegularSeats());
            ticketController = new TicketController(this, session,objectSocket);
            ticketController.initializeTicket();
            Thread thread = new Thread(new ReadTicketThread( session, this));
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