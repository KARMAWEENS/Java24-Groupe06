package org.movieTheatre.java24groupe06.Network;


import org.movieTheatre.java24groupe06.Network.exceptions.HandleExceptions;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.sql.SQLException;

public class SessionHandlerThread extends Thread {

    private Session session;

    ObjectSocket objectSocket;
    Listener listener;
    HandleExceptions exceptionHandler = new HandleExceptions();

    public SessionHandlerThread(Session session, ObjectSocket objectSocket, Listener listener) {
        this.session = session;
        this.objectSocket = objectSocket;
        this.listener=listener;
    }

    @Override
    public void run() {
        try {
            objectSocket.read();
        } catch (IOException e) {
            this.listener.onConnectionLost(this);
        } catch (ClassNotFoundException e) {
            exceptionHandler.handleException("La classe n'a pas été trouvé", e);
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
        } catch (SQLException e) {
            exceptionHandler.handleException("une erreur de Database a eu lieu", e);
        } catch (IOException e) {
            exceptionHandler.handleException("une erreur IO a eu lieu", e);
        }
    }
    public interface Listener{
        void onConnectionLost(SessionHandlerThread sessionHandlerThread);
    }
}