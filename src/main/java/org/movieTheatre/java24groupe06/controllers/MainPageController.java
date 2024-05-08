package org.movieTheatre.java24groupe06.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.server.ObjectSocket;
import org.movieTheatre.java24groupe06.views.Components.MainScenePosterTemplateController;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;
import org.movieTheatre.java24groupe06.views.MainPageViewController;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements MainPageViewController.Listener {

    private Listener listener;
    public List<Movie> movieList;

    MainPageController(Listener listener) {
        this.listener = listener;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void initializeMainStage(Stage stage) {
        try {
            // On se connecte
            Socket socketMovieList = new Socket("localhost",8080);
            ObjectSocket objectSocketMovieList = new ObjectSocket(socketMovieList);
            // J attends les films de MovieListHandlerThread
            setMovieList(objectSocketMovieList.read());

            MainPageViewController mainPageViewController = new MainPageViewController(this, movieList, stage);
            mainPageViewController.openOn(stage);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void OnClickImage(Movie movie) {
        listener.createDetailsMovieStage(movie);
    }

    public interface Listener {
        void createDetailsMovieStage(Movie movie);
    }
}
