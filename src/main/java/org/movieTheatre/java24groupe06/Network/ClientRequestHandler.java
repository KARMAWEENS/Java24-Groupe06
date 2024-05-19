package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.Network.Event.GetDTOSessionListEvent;
import org.movieTheatre.java24groupe06.Network.Event.GetMovieEvent;
import org.movieTheatre.java24groupe06.Network.Event.RequestSessionEvent;
import org.movieTheatre.java24groupe06.Network.Event.UpdateSessionEvent;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRequestHandler extends Thread implements UpdateSeatsHandler.Listener {
    ObjectSocket objectSocket;
    public static List<SessionHandler> currentTicketPageList = new ArrayList<>();

    public ClientRequestHandler(ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object object = objectSocket.read();
                if (object instanceof GetMovieEvent) {
                    sendMovieList();
                } else if (object instanceof GetDTOSessionListEvent getDTOSessionListEvent) {
                    Movie movie = getDTOSessionListEvent.getMovie();
                    sendDTOSessionList(movie);
                } else if (object instanceof RequestSessionEvent requestSessionEvent) {
                    Session session = getSession(requestSessionEvent.getDtoCreateSession());
                    sendSession(session);
                    initializeSessionHandlerThread(session);
                } else if (object instanceof UpdateSessionEvent updateSessionEvent) {
                    UpdateSeatsHandler updateSeatsHandler = new UpdateSeatsHandler(objectSocket, updateSessionEvent.getDtoBuy(),this);
                    updateSeatsHandler.start();
                }
            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeSessionHandlerThread(Session session) {
        SessionHandler sessionHandler = new SessionHandler(session);
        Thread TicketPageThread = new Thread(sessionHandler);
        TicketPageThread.start();
    }

    public void sendMovieList() throws IOException {
        CreateMovies createMovies = new CreateMovies();
        objectSocket.write(createMovies.buildMoviesList());
    }

    public void sendDTOSessionList(Movie movie) throws SQLException, IOException {
        SessionDAO sessionDAO = new SessionDAO();
        objectSocket.write(sessionDAO.getDTOSessionList(movie));
    }

    public Session getSession(DTOCreateSession dtoCreateSession) throws IOException, SQLException {
        SessionDAO sessionDAO = new SessionDAO();
        return sessionDAO.getSessionBySessionId(dtoCreateSession.getSessionID(), dtoCreateSession.getMovie());
    }
    public void sendSession(Session session) throws IOException {
        objectSocket.write(session);
    }

    @Override
    public void onSeatsUpdated(Session session) {
        broadcast(session);
    }

    public void broadcast(Session session) {
        for (SessionHandler sessionHandler : currentTicketPageList) {
            if (sessionHandler.getSession().equals(session)) {
                sessionHandler.updateUI(session);
            }
        }
    }
}
