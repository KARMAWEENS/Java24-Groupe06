package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Application;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.Network.Event.RequestSessionEvent;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class MovieApplication extends Application implements WelcomePageController.Listener, MovieDetailsController.Listener, TicketController.Listener,ReadTicketThread.Listener{
    MovieDetailsController movieDetailsController;
    WelcomePageController welcomePageController;
    TicketController ticketController;
    List<TicketController> ticketControllerList = new ArrayList<>();
    ObjectSocket objectSocket;

    ReadTicketThread readTicketThread;

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
            RequestSessionEvent requestSessionEvent = new RequestSessionEvent(dtoCreateSession);
            objectSocket.write(requestSessionEvent);
            Session session = objectSocket.read();
            ticketController = new TicketController(this, session,objectSocket);
            ticketControllerList.add(ticketController);
            ticketController.initializeTicket();
            Socket socket = new Socket("localhost", 8000);
            ObjectSocket objectSocket2 = new ObjectSocket(socket);
             readTicketThread = new ReadTicketThread(session,this,objectSocket2);
            readTicketThread.start();
        } catch (CantLoadFXMLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
@Override
public void onCloseTicketView(TicketController ticketController){
        ticketControllerList.remove(ticketController);
        readTicketThread.objectSocket.close();
}
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void updateUITicketBought(Session session) {
        for(TicketController ticketController :ticketControllerList ) {
            if (session.equals(ticketController.getSession())){
            ticketController.ticketsBoughtUpdateUI();
            }
        }
    }
}