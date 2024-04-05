package org.example.java24groupe06.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.CreateMovies;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.views.MainPageView;
import org.movieTheatre.java24groupe06.views.MainPageViewController;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;
import org.movieTheatre.java24groupe06.views.MainPageViewController;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application implements MovieDetailsViewController.Listener, MainPageViewController.Listener {

    private Stage stage;
    private Scene scene1;

    @Override
    public void start(Stage stage) throws IOException {

        try {

            this.stage = stage;
            // On recupere la db et on crée une list d'objet Movie
            CreateMovies createMovies = new CreateMovies();
            List<Movie> movies = createMovies.getShowingMovies();

            // FXML shits
            FXMLLoader fxmlLoader = new FXMLLoader(MainPageViewController.getViewURL());
           scene1 = new Scene(fxmlLoader.load());
            MainPageViewController mainPageViewController = fxmlLoader.getController();
            mainPageViewController.setListener(this);

            mainPageViewController.setMovieList(movies);
            mainPageViewController.show();

            stage.setTitle("Hello!");
            stage.setScene(scene1);
            stage.show();


        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);

    }

    @Override
    public void OnClickImage(Movie movie) throws IOException, SQLException, ParseException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieDetailsViewController.getViewURL());
        Scene scene2 = new Scene(fxmlLoader.load());
        MovieDetailsViewController movieDetailsViewController = fxmlLoader.getController();
        movieDetailsViewController.displayMovieDetails(movie);
        movieDetailsViewController.setListener(this);
        stage.setTitle("Hello!");
        stage.setScene(scene2);
    }

    @Override
    public void previousBtnClicked() {
        stage.setScene(scene1);
        System.out.println(scene1);
    }
}