package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class SessionHandler implements Runnable {

    private Session session;


    ObjectSocket objectSocket;

    public SessionHandler(Session session) {
        this.session = session;
    }

    @Override
    public void run() {
        try {
            Socket socket = Server.ticketServerSocket.accept();
            ClientRequestHandler.currentTicketPageList.add(this);
            objectSocket = new ObjectSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Session getSession() {
        return this.session;
    }

    public void updateUI(Session session) {
        try {
            SessionDAO sessionDAO = new SessionDAO();
            SeatsRoomLeft seatsRoomLeft = sessionDAO.getSeatsRoomLeftBySessionId(session);
            objectSocket.write(seatsRoomLeft);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}