package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.Socket;

public class SessionInitializer implements Runnable {

    private Session session;

    private TicketSessionHandler ticketSessionHandler;
    ObjectSocket objectSocket;

    public TicketSessionHandler getTicketHandler() {
        return ticketSessionHandler;
    }

    public SessionInitializer(ObjectSocket objectSocket, Session session) {
        this.objectSocket = objectSocket;
        this.session = session;
    }

    @Override
    public void run() {
        try {
            Socket socket = Server.ticketServerSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(socket);
            ticketSessionHandler = new TicketSessionHandler(objectSocket, session);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}