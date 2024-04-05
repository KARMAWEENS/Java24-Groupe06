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
            // Retrieve movies from the database and display the main page
            List<Movie> movies = retrieveMovieFromDB();
            displayMainPage(stage, movies);
        } catch (SQLException |  ParseException | IOException e) {
            handleInitilizationError(e);
        }
    }


    //logic to retrieve movie from db + more modularity
    private List<Movie> retrieveMovieFromDB() throws SQLException, ParseException {
        CreateMovies createMovies = new CreateMovies();
        return createMovies.getShowingMovies();
    }


    //logic to display main page
    private void displayMainPage(Stage stage, List<Movie> movies) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainPageViewController.getViewURL());
        Scene scene = new Scene(fxmlLoader.load());
        MainPageViewController mainPageViewController = fxmlLoader.getController();
        mainPageViewController.setMovieList(movies);
        mainPageViewController.show();
        stage.setTitle("Movie Theatre");
        stage.setScene(scene);
        stage.show();
    }


    //function to handle initialization error (we can extend this to handle other errors)
    private void handleInitilizationError(Exception e){
        e.printStackTrace();
    }


    public static void main(String[] args) {
        launch(args);
    }
}