package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class CreateSessionHandlerThread extends Handler {

    private Session session;
    ServerSocket serverSocket;
    Listener listener;
    private TicketHandler ticketHandler;

    public TicketHandler getTicketHandler() {
        return ticketHandler;
    }

    public CreateSessionHandlerThread(ObjectSocket objectSocket, Session session, ServerSocket serverSocket, Listener listener) {
        super(objectSocket);
        this.session = session;
        this.serverSocket = serverSocket;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {

            Socket socket = serverSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(socket);
            ticketHandler = new TicketHandler(objectSocket, session);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public interface Listener {
        void onSeatsUpdated(Session session);
    }
}