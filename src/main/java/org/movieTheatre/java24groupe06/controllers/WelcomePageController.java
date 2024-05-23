package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.Network.Event.GetMovieEvent;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;
import org.movieTheatre.java24groupe06.views.WelcomePageViewController;
import static org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;

import java.io.IOException;
import java.util.List;

public class WelcomePageController implements WelcomePageViewController.Listener {

    private Listener listener;
    public List<Movie> movieList;
    public ObjectSocket objectSocket ;

    WelcomePageController(Listener listener,ObjectSocket objectSocket) {
        this.listener = listener;
        this.objectSocket = objectSocket;
    }
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void initializeMainStage(Stage stage) throws CustomExceptions{
        try {
            GetMovieEvent getMovieEvent = new GetMovieEvent();
            objectSocket.write(getMovieEvent);
            setMovieList(objectSocket.read());
            WelcomePageViewController welcomePageViewController = new WelcomePageViewController(this, movieList, stage);
            welcomePageViewController.openOn(stage);
        } catch (IOException | ClassNotFoundException e) {
            throw new CustomExceptions("Failed to initialize main stage", e, ErrorCode.WELCOME_PAGE_ERROR);
        }
    }
    @Override
    public void OnClickImage(Movie movie) throws CustomExceptions{
        listener.createDetailsMovieStage(movie);
    }

    public interface Listener {
        void createDetailsMovieStage(Movie movie) throws CustomExceptions;
    }
}
