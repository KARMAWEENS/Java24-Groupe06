package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Platform;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;

import java.io.IOException;
import java.net.Socket;

public class ReadTicketThread implements Runnable {

    Session session;
    Listener listener;

    public ReadTicketThread(Session session, Listener listener) {
        this.session = session;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 8000);
            ObjectSocket objectSocket = new ObjectSocket(socket);
            while (true) {
                Object seatsRoomLeft = objectSocket.read();
                if(seatsRoomLeft instanceof SeatsRoomLeft){
                    SeatsRoomLeft seatsRoomLeft1 = (SeatsRoomLeft) seatsRoomLeft;
                    System.out.println(seatsRoomLeft1.getNbRegularSeats());
                    session.setSeatsRoomLeft(seatsRoomLeft1);
                    Platform.runLater(() -> listener.updateUITicketBought(session));
                    System.out.println("je m'update");
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("je suis dans le catch");
            throw new RuntimeException(e);
        }
    }

    public interface Listener{
        void updateUITicketBought(Session session);
    }
}
