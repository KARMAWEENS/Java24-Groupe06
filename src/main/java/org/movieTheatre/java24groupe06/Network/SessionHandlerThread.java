package org.movieTheatre.java24groupe06.Network;


import org.movieTheatre.java24groupe06.Network.exceptions.HandleExceptions;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.SeatsRoomLeft;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.io.IOException;
import java.sql.SQLException;
/**
 * The SessionHandlerThread class provides methods for managing the session handler thread.
 */
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
    /**
     * The run method manages the session handler thread.
     */
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
    /**
     * The getSession method returns the session.
     * @return the session.
     */
    public Session getSession() {
        return this.session;
    }
    /**
     * The updateUI method updates the user interface.
     * @param session the session.
     */
    public void updateUI(Session session) {
        try {
            SessionDAO sessionDAO = new SessionDAO();
            SeatsRoomLeft seatsRoomLeft = sessionDAO.getSeatsRoomLeftBySessionId(session);
            objectSocket.write(seatsRoomLeft);
        }
         catch (IOException e) {
            exceptionHandler.handleException("une erreur IO a eu lieu", e);
        } catch (DataAccessException e) {
            exceptionHandler.handleException("une erreur d'accès aux données a eu lieu", e);
        }
    }
    /**
     * The Listener interface provides methods for managing the listener.
     */
    public interface Listener{
        void onConnectionLost(SessionHandlerThread sessionHandlerThread);
    }
}