package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import java.io.IOException;
import java.net.Socket;

/**
 * The ReadTicketThread class extends the Thread class and implements the Listener interface from the ReadTicketThread class.
 * It serves as the controller for the reading of tickets, handling the reading of tickets from the server and updating the UI.
 */
public class ReadTicketThread extends Thread {

    private Session session;
    private Listener listener;
   private ObjectSocket objectSocket;

    /**
     * The constructor for the ReadTicketThread class.
     *
     * @param session the session for which tickets are to be read.
     * @param listener the listener for the ticket reading thread.
     * @param objectSocket the object socket for the ticket reading thread.
     */
    public ReadTicketThread(Session session, Listener listener,ObjectSocket objectSocket) {
        this.session = session;
        this.listener = listener;
        this.objectSocket=objectSocket;
    }

    /**
     * The run method reads tickets from the server and updates the UI.
     */
    @Override
    public void run() {
        try {
            while (true) {
                Object object = objectSocket.read();
                if(object instanceof SeatsRoomLeft seatsRoomLeft){
                    System.out.println(seatsRoomLeft.getNbRegularSeats());
                    session.setSeatsRoomLeft(seatsRoomLeft);
                    Platform.runLater(() -> listener.updateUITicketBought(session));
                }
            }
        } catch (IOException | ClassNotFoundException e){
            AlertManager.showErrorAlert("Erreur lors de la connexion au serveur ", e);
            throw new ReadTicketException("Error while reading from server", e);
        }
    }

    /**
     * The closeSocket method closes the socket.
     */
    public void closeSocket() {

        try {
            this.objectSocket.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de la fermeture du socket : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The Listener interface provides a method for updating the UI when a ticket is bought.
     */
    public interface Listener{
        void updateUITicketBought(Session session);
    }
}
