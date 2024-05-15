package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;

public class TicketHandlerThread implements Runnable {
    ObjectSocket objectSocket;
    Session session;

    public TicketHandlerThread(ObjectSocket objectSocket, Session session) {
        this.objectSocket = objectSocket;
        this.session = session;
    }

    public Session getSession() {
        return this.session;
    }

    @Override
    public void run() {

    }

    public void updateUI(Session session) {
        try {
            System.out.println("il faut chang");
            SessionDAO sessionDAO = new SessionDAO();
           SeatsRoomLeft seatsRoomLeft = sessionDAO.getSeatsRoomLeftBySessionId(session);
            objectSocket.write(seatsRoomLeft);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
