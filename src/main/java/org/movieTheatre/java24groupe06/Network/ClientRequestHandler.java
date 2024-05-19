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
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRequestHandler extends Thread implements UpdateSeatsHandler.Listener,Testtttt.Listener {
    ObjectSocket objectSocket;
    public static List<SessionHandler> currentTicketPageList = new ArrayList<>();

    public ClientRequestHandler(ObjectSocket objectSocket) {
        this.objectSocket = objectSocket;
    }
    int i = 0;
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

                    initializeSessionHandler(session,i);
                    i++;
                } else if (object instanceof UpdateSessionEvent updateSessionEvent) {
                    UpdateSeatsHandler updateSeatsHandler = new UpdateSeatsHandler(objectSocket, updateSessionEvent.getDtoBuy(),this);
                    updateSeatsHandler.start();
                }
            }
        } catch (ClassNotFoundException | IOException e) {

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeSessionHandler(Session session,int id) {


        try {
          Socket socket = Server.ticketServerSocket.accept();
            ObjectSocket objectSocket2 = new ObjectSocket(socket);
            SessionHandler sessionHandler = new SessionHandler(session, objectSocket2, id);
            currentTicketPageList.add(sessionHandler);
            for (SessionHandler sessionhanlder: currentTicketPageList){
                System.out.println(sessionhanlder);
                System.out.println(sessionhanlder.getId());
            }
            Testtttt testtttt = new Testtttt(sessionHandler,objectSocket2,this);
            testtttt.start();
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

    @Override
    public void planted(SessionHandler sessionHandler) {
        System.out.println(sessionHandler.getId());
        currentTicketPageList.remove(sessionHandler);
    }
}
