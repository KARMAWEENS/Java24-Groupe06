package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Platform;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;

import java.io.IOException;
import java.net.Socket;

public class ReadTicketThread extends Thread {

    private Session session;
    private Listener listener;
   private ObjectSocket objectSocket;

    public ReadTicketThread(Session session, Listener listener,ObjectSocket objectSocket) {
        this.session = session;
        this.listener = listener;
        this.objectSocket=objectSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object seatsRoomLeft = objectSocket.read();
                if(seatsRoomLeft instanceof SeatsRoomLeft){
                    SeatsRoomLeft seatsRoomLeft1 = (SeatsRoomLeft) seatsRoomLeft;
                    System.out.println(seatsRoomLeft1.getNbRegularSeats());
                    session.setSeatsRoomLeft(seatsRoomLeft1);
                    Platform.runLater(() -> listener.updateUITicketBought(session));
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeSocket() {
        this.objectSocket.close();
    }

    public interface Listener{
        void updateUITicketBought(Session session);
    }
}
