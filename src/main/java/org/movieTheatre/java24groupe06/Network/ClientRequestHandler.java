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
import java.util.List;

public class ClientRequestHandler implements Runnable, UpdateSeatsHandler.Listener, SessionInitializer.Listener {
    ObjectSocket objectSocket;
    private List<SessionInitializer> createSessionHandlerList;

    public ClientRequestHandler(ObjectSocket objectSocket,List<SessionInitializer> createSessionHandlerList) {
        this.objectSocket = objectSocket;
        this.createSessionHandlerList = createSessionHandlerList;
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

                    SessionInitializer createSessionHandler = new SessionInitializer(objectSocket, session,this);
                    Thread createSessionHandlerThread = new Thread(createSessionHandler);
                    createSessionHandlerList.add(createSessionHandler);
                    createSessionHandlerThread.start();
                } else if (object instanceof UpdateSessionEvent) {
                    UpdateSessionEvent updateSessionEvent = (UpdateSessionEvent) object;
                    Thread updateSessionSeatsHandlerThread = new Thread(new UpdateSeatsHandler(objectSocket, updateSessionEvent.getDtoBuy(), this));
                    updateSessionSeatsHandlerThread.start();
                }

            }
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println(createSessionHandlerList.size());
        for (SessionInitializer createSessionHandlerThread : createSessionHandlerList) {
            if (createSessionHandlerThread.getTicketHandler().getSession().getSessionID() == session.getSessionID()) {
                System.out.println("faut changer ui ");
                createSessionHandlerThread.getTicketHandler().updateUI(session);
            } else {
                System.out.println("faut pas changer ui ");
            }
        }
    }
}
