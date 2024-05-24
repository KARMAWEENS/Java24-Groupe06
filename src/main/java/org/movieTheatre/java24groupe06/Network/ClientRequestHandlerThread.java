package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.Network.Event.GetDTOSessionListEvent;
import org.movieTheatre.java24groupe06.Network.Event.GetMovieEvent;
import org.movieTheatre.java24groupe06.Network.Event.RequestSessionEvent;

import org.movieTheatre.java24groupe06.Network.exceptions.HandleExceptions;


import org.movieTheatre.java24groupe06.Network.Event.UpdateSessionSeatsEvent;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.DAO.PurchaseDTO;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CreateMoviesException;
import org.movieTheatre.java24groupe06.models.exceptions.DataAccessException;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The ClientRequestHandlerThread class provides methods for managing the client request handler thread.
 */
public class ClientRequestHandlerThread extends Thread implements SessionHandlerThread.Listener {
    ObjectSocket objectSocket;
    public static List<SessionHandlerThread> currentTicketPageList = new ArrayList<>();



    /**
     * The constructor initializes the object socket.
     * @param objectSocket
     */
    public ClientRequestHandlerThread(ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
    }
    /**
     * The run method manages the client request handler thread.
     */
    @Override
    public void run()  {
        HandleExceptions exceptionHandler = new HandleExceptions();

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
                } else if (object instanceof UpdateSessionSeatsEvent updateSessionSeatsEvent) {
                    updateSessionSeatsAndBroadcast(updateSessionSeatsEvent.getDtoBuy());
                }
            }
        } catch (IOException e) {
            exceptionHandler.handleException("une erreur IO a eu lieu", e);
        } catch (ClassNotFoundException e) {
            exceptionHandler.handleException("La classe n'a pas été trouvé", e);
        } catch (CreateMoviesException e) {
            exceptionHandler.handleException("une erreur de création de film a eu lieu", e);
        } catch (DataAccessException e) {
            exceptionHandler.handleException("une erreur d'accès aux données a eu lieu", e);
        }
    }

    /**
     * The initializeSessionHandlerThread method initializes the session handler thread.
     * @param session the session.
     */
    private void initializeSessionHandlerThread(Session session) {
        HandleExceptions exceptionHandler = new HandleExceptions();
        try {
          Socket socket = Server.ticketServerSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(socket);
            SessionHandlerThread sessionHandlerThread = new SessionHandlerThread(session, objectSocket,this);
            sessionHandlerThread.start();
            currentTicketPageList.add(sessionHandlerThread);
        } catch (IOException e) {
            exceptionHandler.handleException("une erreur IO a eu lieu", e);
        }
    }
    /**
     * The sendMovieList method sends the movie list.
     */
    public void sendMovieList() throws IOException, CreateMoviesException {
        CreateMovies createMovies = new CreateMovies();
        objectSocket.write(createMovies.buildMoviesList());
    }
    /**
     * The sendDTOSessionList method sends the DTO session list.
     * @param movie the movie.
     */
    public void sendDTOSessionList(Movie movie) throws DataAccessException, IOException {
        SessionDAO sessionDAO = new SessionDAO();
        objectSocket.write(sessionDAO.getDTOSessionList(movie));
    }
    /**
     * The getSession method gets the session.
     * @param createSessionDTO the create session DTO.
     * @return The session.
     */
    public Session getSession(CreateSessionDTO createSessionDTO) throws  DataAccessException {
        SessionDAO sessionDAO = new SessionDAO();
        return sessionDAO.getSessionBySessionId(createSessionDTO.getSessionID(), createSessionDTO.getMovie());
    }
    /**
     * The sendSession method sends the session.
     * @param session the session.
     */
    public void sendSession(Session session) throws IOException {
        objectSocket.write(session);
    }
    /**
     * The updateSessionSeatsAndBroadcast method updates the session seats and broadcasts.
     * @param purchaseDto the purchase DTO.
     */
    public void updateSessionSeatsAndBroadcast(PurchaseDTO purchaseDto){
        SessionDAO sessionDAO = new SessionDAO();
        Session session = purchaseDto.getSession();
        int nbRegularSeatsBuy = purchaseDto.getNbRegularSeatsBuy();
        int nbVIPSeatsBuy = purchaseDto.getNbVIPSeatsBuy();
        int nbHandicapsSeatsBuy = purchaseDto.getNbHandicapsSeatsBuy();
        sessionDAO.updateSeats(session,nbRegularSeatsBuy,nbVIPSeatsBuy,nbHandicapsSeatsBuy);
        broadcast(session);
    }
    /**
     * The broadcast method broadcasts the session.
     * @param session the session.
     */
    public void broadcast(Session session) {
            for (SessionHandlerThread sessionHandlerThread : currentTicketPageList) {
                if (sessionHandlerThread.getSession().equals(session)) {
                    sessionHandlerThread.updateUI(session);
                }
            }
    }
    /**
     * The onConnectionLost method manages the connection lost.
     * @param sessionHandlerThread the session handler thread.
     */
    @Override
    public void onConnectionLost(SessionHandlerThread sessionHandlerThread) {
        currentTicketPageList.remove(sessionHandlerThread);
    }
}
