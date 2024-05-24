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

/**
 * The WelcomePageController class implements the Listener interface from the WelcomePageViewController class.
 * It serves as the controller for the welcome page, handling the initialization of the main stage and the display of
 * the welcome page.
 */
public class WelcomePageController implements WelcomePageViewController.Listener {

    private Listener listener;
    public List<Movie> movieList;
    public ObjectSocket objectSocket ;

    /**
     * The constructor for the WelcomePageController class.
     *
     * @param listener the listener for the welcome page.
     * @param objectSocket the object socket for the welcome page.
     */
    WelcomePageController(Listener listener,ObjectSocket objectSocket) {
        this.listener = listener;
        this.objectSocket = objectSocket;
    }

    /**
     * The setMovieList method sets the list of movies.
     *
     * @param movieList the list of movies.
     */
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    /**
     * The initializeMainStage method initializes the main stage.
     *
     * @param stage the stage to be initialized.
     * @throws CustomExceptions if an error occurs while initializing the stage.
     */

    public void initializeMainStage(Stage stage) throws CustomExceptions{
        try {
            objectSocket.write(new GetMovieEvent());
            setMovieList(objectSocket.read());
            WelcomePageViewController welcomePageViewController = new WelcomePageViewController(this, movieList, stage);
            welcomePageViewController.openOn(stage);
        } catch (IOException | ClassNotFoundException e) {
            throw new CustomExceptions("Failed to initialize main stage", e, ErrorCode.WELCOME_PAGE_ERROR);
        }
    }
    /**
     * The createDetailsMovieStage method creates a new stage for displaying movie details.
     *
     * @param movie the movie for which details are to be displayed.
     * @throws CustomExceptions if an error occurs while creating the stage.
     */
    @Override
    public void OnClickImage(Movie movie) throws CustomExceptions{
        listener.createDetailsMovieStage(movie);
    }

    /**
     * The Listener interface provides a method for creating a new stage for displaying movie details.
     */
    public interface Listener {
        void createDetailsMovieStage(Movie movie) throws CustomExceptions;
    }
}
