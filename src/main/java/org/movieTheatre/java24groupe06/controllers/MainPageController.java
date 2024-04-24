package org.movieTheatre.java24groupe06.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.DAO.CreateMovies;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;
import org.movieTheatre.java24groupe06.views.MainPageViewController;

import java.util.List;

public class MainPageController implements MainPageViewController.Listener {

    private Listener listener;
    private Scene mainScene;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public List<Movie> movieList;
    private Stage mainStage;
    private String title;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void initializeMainStage(Stage stage) {
        try {
            setMainStage(stage);
            createAndSetMovieList();

            //TODO en faite la on cree un mainPageViewController avec new puis on en recre un avec .showInStage y a un truc a changer
            MainPageViewController mainPageViewController = new MainPageViewController().showInStage(stage);
            mainPageViewController.setListener(this);

            mainPageViewController.setMovieList(movieList);
            mainPageViewController.show();

            mainPageViewController.onLoad((int) mainStage.getWidth());
            setWidthListener(mainPageViewController);

        } catch (CantLoadFXMLException e) {
            AlertManager alertManager = new AlertManager();
            alertManager.CantLoadPageAlert(e);
            // potentielement faire un truc du genre
            // start (mainStage)
        }
    }

    private void createAndSetMovieList() {
        CreateMovies createMovies = new CreateMovies();
        setMovieList(createMovies.buildMoviesList());
    }

    private void setWidthListener(MainPageViewController mainPageViewController) {
        mainStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Width: " + newVal);
            try {
                mainPageViewController.onWidthChanged(newVal.intValue());
            } catch (CantLoadFXMLException e) {
                // quasiment tt le temps unreachable sauf si le fichier devient inaccessible pdt le run de l app

                AlertManager alertManager = new AlertManager();
                alertManager.CantLoadPageAlert(e);
            }
        });
    }

    @Override
    public void onClickImage(Movie movie) {
        listener.createDetailsMovieStage(movie);
    }

    public interface Listener {
        void createDetailsMovieStage(Movie movie);
    }


}
