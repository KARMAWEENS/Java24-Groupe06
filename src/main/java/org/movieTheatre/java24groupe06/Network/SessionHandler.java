package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class SessionHandler  {

    private Session session;
    private int id;

    ObjectSocket objectSocket;

    public SessionHandler(Session session,ObjectSocket objectSocket,int id) {
        this.session = session;
        this.objectSocket = objectSocket;
        this.id =id;
    }
    public int getId(){
        return this.id;
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