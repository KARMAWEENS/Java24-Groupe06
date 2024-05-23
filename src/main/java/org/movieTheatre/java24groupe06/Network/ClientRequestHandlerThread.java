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

public class ClientRequestHandlerThread extends Thread implements SessionHandlerThread.Listener {
    ObjectSocket objectSocket;
    public static List<SessionHandlerThread> currentTicketPageList = new ArrayList<>();




    public ClientRequestHandlerThread(ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
    }

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

    public void sendMovieList() throws IOException, CreateMoviesException {
        CreateMovies createMovies = new CreateMovies();
        objectSocket.write(createMovies.buildMoviesList());
    }

    public void sendDTOSessionList(Movie movie) throws DataAccessException, IOException {
        SessionDAO sessionDAO = new SessionDAO();
        objectSocket.write(sessionDAO.getDTOSessionList(movie));
    }

    public Session getSession(CreateSessionDTO createSessionDTO) throws  DataAccessException {
        SessionDAO sessionDAO = new SessionDAO();
        return sessionDAO.getSessionBySessionId(createSessionDTO.getSessionID(), createSessionDTO.getMovie());
    }
    public void sendSession(Session session) throws IOException {
        objectSocket.write(session);
    }

    public void updateSessionSeatsAndBroadcast(PurchaseDTO purchaseDto){
        SessionDAO sessionDAO = new SessionDAO();
        Session session = purchaseDto.getSession();
        int nbRegularSeatsBuy = purchaseDto.getNbRegularSeatsBuy();
        int nbVIPSeatsBuy = purchaseDto.getNbVIPSeatsBuy();
        int nbHandicapsSeatsBuy = purchaseDto.getNbHandicapsSeatsBuy();
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
