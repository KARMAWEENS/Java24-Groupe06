package org.example.java24groupe06.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.models.CreateMovies;

import org.example.java24groupe06.views.HelloController;
import org.example.java24groupe06.views.MovieController;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.getViewURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

/*        GetDbinfo DB
        CreateMovies Controller
        ShowMovies View*/

        try {

            CreateMovies movieRepository = new CreateMovies();
            List<Movie> movies = movieRepository.getShowingMovies();

            MovieController movieController = new MovieController();

            movieController.showMovies(movies);


        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();

    }
}