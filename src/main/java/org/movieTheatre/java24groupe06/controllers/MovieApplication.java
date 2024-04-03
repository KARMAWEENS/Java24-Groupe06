package org.movieTheatre.java24groupe06.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.CreateMovies;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.views.MainPageViewController;

import java.io.IOException;
import java.util.List;

public class MovieApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        try {

            // On recupere la db et on crée une list d'objet Movie
            CreateMovies createMovies = new CreateMovies();
            List<Movie> movies = createMovies.getShowingMovies();

            // FXML shits
            FXMLLoader fxmlLoader = new FXMLLoader(MainPageViewController.getViewURL());
            Scene scene = new Scene(fxmlLoader.load());
            MainPageViewController mainPageViewController = fxmlLoader.getController();


            mainPageViewController.setMovieList(movies);
            mainPageViewController.show();

            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();



        } catch (SQLException |  ParseException e) {
            e.printStackTrace();
        }



    }


    public static void main(String[] args) {
        Application.launch(MovieApplication.class,args);

    }
}