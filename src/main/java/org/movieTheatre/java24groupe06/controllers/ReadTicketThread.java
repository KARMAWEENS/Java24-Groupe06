package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Platform;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.server.ObjectSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ReadTicketThread implements Runnable {
    ObjectSocket objectSocket;
    Session session;
    Listener listener;

    public ReadTicketThread(ObjectSocket objectSocket,Session session, Listener listener) {
        this.objectSocket = objectSocket;
        this.session = session;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            while (true) {
                SeatsRoomLeft seatsRoomLeft = objectSocket.read();
                System.out.println("j'ai racu");
                session.setSeatsRoomLeft(seatsRoomLeft);
                Platform.runLater(() -> listener.updateUITicketBought());
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public interface Listener{
        void updateUITicketBought();
    }
}
