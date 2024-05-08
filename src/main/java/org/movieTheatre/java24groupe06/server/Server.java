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
        // C est lie a mainPageController
        ServerSocket serverSocketMovieList = new ServerSocket(8080);
        Thread movieListHandlerThread = new Thread(new MovieListHandlerThread(serverSocketMovieList,this));
        movieListHandlerThread.start();

        // C est lie a MovieDetailsController
        ServerSocket serverSocketSession = new ServerSocket(8081);
        Thread sessionHandlerThread = new Thread(new SessionHandlerThread(serverSocketSession,this));
        sessionHandlerThread.start();


        ServerSocket serverSocketUpdateSessionSeats = new ServerSocket(8082);
        Thread updateSessionSeatsHandlerThread = new Thread(new UpdateSessionSeatsHandlerThread(serverSocketUpdateSessionSeats,this));
        updateSessionSeatsHandlerThread.start();

        ServerSocket serverSocketCreateSession = new ServerSocket(8083);
         createSessionHandler = new CreateSessionHandlerThread(serverSocketCreateSession,this);
        Thread createSessionHandlerThread = new Thread(createSessionHandler);
        createSessionHandlerThread.start();
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
