package org.example.java24groupe06.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.models.CreateMovies;

import org.example.java24groupe06.models.Moovies;
import org.example.java24groupe06.models.Presentation;
import org.example.java24groupe06.views.HelloController;
import org.example.java24groupe06.views.MovieController;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        List<Moovies> moovies = new ArrayList<>();
        Moovies moovies1 = new Moovies("src/main/java/org/example/java24groupe06/views/img/téléchargement(2).png");
        Moovies moovies2 = new Moovies("src/main/java/org/example/java24groupe06/views/img/téléchargement.png");
        Moovies moovies4 = new Moovies("src/main/java/org/example/java24groupe06/views/img/téléchargement.png");
        Moovies moovies5 = new Moovies("src/main/java/org/example/java24groupe06/views/img/téléchargement.png");

        Moovies moovies3 = new Moovies("src/main/java/org/example/java24groupe06/views/img/téléchargement.png");
        moovies.add(moovies1);
        moovies.add(moovies2);
        moovies.add(moovies3);
        moovies.add(moovies5);
        moovies.add(moovies4);
        MoovieController moovieController = new MoovieController(moovies);
        Presentation presentation = new Presentation(moovieController.getPosterList());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.getViewURL());
        fxmlLoader.setControllerFactory(controller -> new HelloController(presentation));
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