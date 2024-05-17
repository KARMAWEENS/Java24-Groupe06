package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.views.TicketViewController;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionHandlerThread extends Handler{
    Movie movie;
    public SessionHandlerThread(ObjectSocket objectSocket,Movie movie) {
        super(objectSocket);
        this.movie = movie;
    }

    @Override
    public void run() {

            try {
               // On attend un connexion de MovieDetailsController
               // On attend un Movie le movie passé a getSession
               // On renvoie une liste de session lie au film

                objectSocket.write(getSession(movie));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    }

    private List<Session> getSession(Movie movie) {
        SessionDAO sessionDAO = new SessionDAO();
        try {
         return sessionDAO.getSession(movie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
