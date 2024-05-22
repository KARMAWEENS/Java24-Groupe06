package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.Network.Event.GetDTOSessionListEvent;
import org.movieTheatre.java24groupe06.Network.Event.GetMovieEvent;
import org.movieTheatre.java24groupe06.Network.Event.RequestSessionEvent;
import org.movieTheatre.java24groupe06.Network.Event.UpdateSessionSeatsEvent;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRequestHandlerThread extends Thread implements SessionHandlerThread.Listener {
    ObjectSocket objectSocket;
    public static List<SessionHandlerThread> currentTicketPageList = new ArrayList<>();

    public ClientRequestHandlerThread(ObjectSocket objectSocket) {
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
                    initializeSessionHandler(session);
                } else if (object instanceof UpdateSessionSeatsEvent updateSessionSeatsEvent) {
                    updateSessionSeatsAndBroadcast(updateSessionSeatsEvent.getDtoBuy());
                }
            }
        } catch (ClassNotFoundException | IOException e) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void initializeSessionHandler(Session session) {
        try {
          Socket socket = Server.ticketServerSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(socket);
            SessionHandlerThread sessionHandlerThread = new SessionHandlerThread(session, objectSocket,this);
            sessionHandlerThread.start();
            currentTicketPageList.add(sessionHandlerThread);
        } catch (IOException e) {
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

    public void updateSessionSeatsAndBroadcast(DTOBuy dtoBuy){
        SessionDAO sessionDAO = new SessionDAO();
        Session session = dtoBuy.getSession();
        int nbRegularSeatsBuy = dtoBuy.getNbRegularSeatsBuy();
        int nbVIPSeatsBuy = dtoBuy.getNbVIPSeatsBuy();
        int nbHandicapsSeatsBuy = dtoBuy.getNbHandicapsSeatsBuy();
        sessionDAO.updateSeats(session,nbRegularSeatsBuy,nbVIPSeatsBuy,nbHandicapsSeatsBuy);
        broadcast(session);
    }

    public void broadcast(Session session) {
            for (SessionHandlerThread sessionHandlerThread : currentTicketPageList) {
                if (sessionHandlerThread.getSession().equals(session)) {
                    sessionHandlerThread.updateUI(session);
                }
            }
    }


    @Override
    public void onConnectionLost(SessionHandlerThread sessionHandlerThread) {
        currentTicketPageList.remove(sessionHandlerThread);
    }
}
