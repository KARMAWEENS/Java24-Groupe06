package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
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
                System.out.println("je reas");
                Object object = objectSocket.read();
                System.out.println(object.getClass());
                if (object instanceof NetworkGetFIlm) {
                    Thread movieListHandlerThread = new Thread(new MovieListHandlerThread(objectSocket));
                    movieListHandlerThread.start();
                } else if (object instanceof NetworkGetDTOSessionList) {

                    NetworkGetDTOSessionList networkGetDTOSessionList = (NetworkGetDTOSessionList) object;
                    Thread sessionHandlerThread = new Thread(new SessionHandlerThread(objectSocket, networkGetDTOSessionList.getMovie()));
                    sessionHandlerThread.start();
                } else if (object instanceof NetworkTicketGetSessionAndThread) {
                    NetworkTicketGetSessionAndThread networkTicketGetSessionAndThread = (NetworkTicketGetSessionAndThread) object;
                   CreateSessionHandlerThread createSessionHandler = new CreateSessionHandlerThread(objectSocket, networkTicketGetSessionAndThread.getDtoCreateSession(), serverSocket2, this);
                    Thread createSessionHandlerThread = new Thread(createSessionHandler);
                    System.out.println("je suis dans le create session handler thread");
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
        }
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
