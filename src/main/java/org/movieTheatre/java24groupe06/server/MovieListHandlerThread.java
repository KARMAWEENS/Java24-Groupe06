package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.Movie;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class MovieListHandlerThread extends Handler {

    public MovieListHandlerThread(ServerSocket serverSocket,Listener listener) {
        super(serverSocket, listener);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectSocket objectSocket = new ObjectSocket(socket);
                objectSocket.write(createMovieList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Movie> createMovieList() {
        CreateMovies createMovies = new CreateMovies();
      return  createMovies.buildMoviesList();
    }
    public interface Listener {

    }
}
