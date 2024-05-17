package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.Network.Event.NetworkGetDTOSessionList;
import org.movieTheatre.java24groupe06.Network.Event.NetworkGetFIlm;
import org.movieTheatre.java24groupe06.Network.Event.NetworkTicketGetSessionAndThread;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.List;

public class ReadObjectThread implements Runnable, UpdateSessionSeatsHandlerThread.Listener, CreateSessionHandlerThread.Listener {
    ObjectSocket objectSocket;
    CreateSessionHandlerThread createSessionHandler;
    ServerSocket serverSocket2;
    private List<CreateSessionHandlerThread> createSessionHandlerThreads;

    public ReadObjectThread(ObjectSocket objectSocket, ServerSocket serverSocket2, List<CreateSessionHandlerThread> createSessionHandlerThreads) {
        this.objectSocket = objectSocket;
        this.serverSocket2 = serverSocket2;
        this.createSessionHandlerThreads = createSessionHandlerThreads;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object object = objectSocket.read();
                if (object instanceof NetworkGetFIlm) {
                    sendMovieList();
                } else if (object instanceof NetworkGetDTOSessionList) {
                    //LIIRRRRE
                    // C'est trop style on peu replace networkGetDTOSessionList avec pattern variable go non ?
                    NetworkGetDTOSessionList networkGetDTOSessionList = (NetworkGetDTOSessionList) object;
                    sendDTOSessionList(networkGetDTOSessionList.getMovie());
                } else if (object instanceof NetworkTicketGetSessionAndThread) {
                    NetworkTicketGetSessionAndThread networkTicketGetSessionAndThread = (NetworkTicketGetSessionAndThread) object;
                    CreateSessionHandlerThread createSessionHandler = new CreateSessionHandlerThread(objectSocket, networkTicketGetSessionAndThread.getDtoCreateSession(), serverSocket2, this);
                    Thread createSessionHandlerThread = new Thread(createSessionHandler);
                    createSessionHandlerThreads.add(createSessionHandler);
                    createSessionHandlerThread.start();
                } else if (object instanceof NetworkUpdateSession) {
                    NetworkUpdateSession networkUpdateSession = (NetworkUpdateSession) object;
                    Thread updateSessionSeatsHandlerThread = new Thread(new UpdateSessionSeatsHandlerThread(objectSocket, networkUpdateSession.getDtoBuy(), this));
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


    @Override
    public void onSeatsUpdated(Session session) {
        broadcast(session);
    }

    public void broadcast(Session session) {
        System.out.println(createSessionHandlerThreads.size());
        for (CreateSessionHandlerThread createSessionHandlerThread : createSessionHandlerThreads) {
            if (createSessionHandlerThread.getTicketHandler().getSession().getSessionID() == session.getSessionID()) {
                System.out.println("faut changer ui ");
                createSessionHandlerThread.getTicketHandler().updateUI(session);
            } else {
                System.out.println("faut pas changer ui ");
            }
        }
    }
}
