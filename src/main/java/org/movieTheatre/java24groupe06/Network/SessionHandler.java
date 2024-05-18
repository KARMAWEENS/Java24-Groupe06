package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class SessionHandler implements Runnable {

    private Session session;
    private Listener listener;

    ObjectSocket objectSocket;

    public SessionHandler(Session session, Listener listener) {
        this.session = session;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            Socket socket = Server.ticketServerSocket.accept();
            ClientRequestHandler.currentTicketPageList.add(this);
            System.out.println(ClientRequestHandler.currentTicketPageList.size());
            objectSocket = new ObjectSocket(socket);
        } catch (IOException e) {
            System.out.println("je suis dans SessionHandler");
            if(!Thread.currentThread().isInterrupted())
                listener.onConnectionLost(this);
        }
    }
    public Session getSession() {
        return this.session;
    }

    public void updateUI(Session session) {
        try {
            System.out.println("je suis dans update UI et je sout la list");
            SessionDAO sessionDAO = new SessionDAO();
            SeatsRoomLeft seatsRoomLeft = sessionDAO.getSeatsRoomLeftBySessionId(session);
            objectSocket.write(seatsRoomLeft);
        } catch (SQLException | IOException e) {

            throw new RuntimeException(e);
        }
    }
    public interface Listener{
        void onConnectionLost(SessionHandler sessionHandler);
    }
}