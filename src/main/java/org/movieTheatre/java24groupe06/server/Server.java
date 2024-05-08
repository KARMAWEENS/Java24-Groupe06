package org.movieTheatre.java24groupe06.server;

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

    private void go() throws IOException {
        ServerSocket serverSocketMovieList = new ServerSocket(8080);
        Thread movieListHandlerThread = new Thread(new MovieListHandlerThread(serverSocketMovieList,this));
        movieListHandlerThread.start();
        ServerSocket serverSocketSession = new ServerSocket(8081);
        Thread sessionHandlerThread = new Thread(new SessionHandlerThread(serverSocketSession,this));
        sessionHandlerThread.start();
        ServerSocket serverSocketUpdateSessionSeats = new ServerSocket(8082);
        Thread updateSessionSeatsHandlerThread = new Thread(new UpdateSessionSeatsHandlerThread(serverSocketUpdateSessionSeats,this));
        updateSessionSeatsHandlerThread.start();
        ServerSocket serverSocketCreateSession = new ServerSocket(8083);
        Thread createSessionHandlerThread = new Thread(new CreateSessionHandlerThread(serverSocketCreateSession,this));
        createSessionHandlerThread.start();
    }


    @Override
    public void onMovieReceived() {
        System.out.println("Movie received");
    }
}
