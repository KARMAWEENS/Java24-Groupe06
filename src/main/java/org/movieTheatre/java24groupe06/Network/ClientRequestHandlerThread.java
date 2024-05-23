package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.Network.Event.GetDTOSessionListEvent;
import org.movieTheatre.java24groupe06.Network.Event.GetMovieEvent;
import org.movieTheatre.java24groupe06.Network.Event.RequestSessionEvent;
import org.movieTheatre.java24groupe06.Network.exceptions.ClassNotFoundExceptionHandler;
import org.movieTheatre.java24groupe06.Network.exceptions.HandleExceptions;
import org.movieTheatre.java24groupe06.Network.exceptions.SQLExceptionHandler;
import org.movieTheatre.java24groupe06.Network.exceptions.IOExceptionHandler;
import org.movieTheatre.java24groupe06.Network.Event.UpdateSessionSeatsEvent;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.DAO.PurchaseDTO;
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
    ClassNotFoundExceptionHandler classNotFoundExceptionHandler = new ClassNotFoundExceptionHandler();
    SQLExceptionHandler sqlExceptionHandler = new SQLExceptionHandler();
    IOExceptionHandler ioExceptionHandler = new IOExceptionHandler();

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
        } catch (SQLException e) {
            exceptionHandler.handleException("une erreur de Database a eu lieu", e);
        } catch (IOException e) {
            exceptionHandler.handleException("une erreur IO a eu lieu", e);
        } catch (ClassNotFoundException e) {
            exceptionHandler.handleException("La classe n'a pas été trouvé", e);
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
