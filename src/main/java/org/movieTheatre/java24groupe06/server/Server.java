package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  implements MovieListHandlerThread.Listener, SessionHandlerThread.Listener, UpdateSessionSeatsHandlerThread.Listener, CreateSessionHandlerThread.Listener{
    public static void main(String[] args) {
        Server serve = new Server();
        try {
            serve.go();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    CreateSessionHandlerThread createSessionHandler;

    private void go() throws IOException {

        ServerSocket serverSocket = new ServerSocket(7999);
        Socket socket = serverSocket.accept();

        ObjectSocket objectSocket = new ObjectSocket(socket);


        try {
            while (true) {
                Object object = objectSocket.read();

                if (object instanceof NetworkGetFIlm) {
                    Thread movieListHandlerThread = new Thread(new MovieListHandlerThread(objectSocket, this));
                    movieListHandlerThread.start();
                } else if (object instanceof NetworkGetSession) {

                    NetworkGetSession networkGetSession = (NetworkGetSession) object;
                    Thread sessionHandlerThread = new Thread(new SessionHandlerThread(objectSocket, this, networkGetSession.getMovie()));
                    sessionHandlerThread.start();
                } else if (object instanceof NetworkTicketGetSessionAndThread) {
                    NetworkTicketGetSessionAndThread networkTicketGetSessionAndThread = (NetworkTicketGetSessionAndThread) object;
                    createSessionHandler = new CreateSessionHandlerThread(objectSocket, this, networkTicketGetSessionAndThread.getDtoCreateSession());
                    Thread createSessionHandlerThread = new Thread(createSessionHandler);
                    createSessionHandlerThread.start();
                } else if (object instanceof NetworkUpdateSession) {
                    NetworkUpdateSession networkUpdateSession = (NetworkUpdateSession) object;
                    Thread updateSessionSeatsHandlerThread = new Thread(new UpdateSessionSeatsHandlerThread(objectSocket, this, networkUpdateSession.getDtoBuy()));
                    updateSessionSeatsHandlerThread.start();
                }

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void onMovieReceived() {
        System.out.println("Movie received");
    }

    @Override
    public void onSeatsUpdated(Session session) {
        createSessionHandler.broadcast(session);
    }
}