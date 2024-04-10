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
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;

import java.io.IOException;
import java.util.List;

public class MovieApplication extends Application implements MovieDetailsViewController.Listener, MainPageViewController.Listener{

    private Stage mainStage;
    private Scene mainScene;
    private Scene movieDetailsScene;

    @Override
    public void start(Stage stage) throws IOException {

        ShowMainStage(stage);
    }

    private void ShowMainStage(Stage stage) {

            this.mainStage = stage;
            MainPageViewController mainPageViewController = MainPageViewController.showInStage(mainStage);
            mainPageViewController.setListener(this);
            // Retrieve movies from the database and display the main page
         //   List<Movie> movies = retrieveMovieFromDB();
          //  displayMainPage(mainStage, movies);

    }


    @Override
    public void OnClickImage(Movie movie) throws IOException, SQLException, ParseException {

        FXMLLoader fxmlLoader = new FXMLLoader(MovieDetailsViewController.getViewURL());

        Scene movieDetailsScene = new Scene(fxmlLoader.load());

        MovieDetailsViewController movieDetailsViewController = fxmlLoader.getController();

        movieDetailsViewController.displayMovieDetails(movie);

        movieDetailsViewController.setListener(this);
        mainStage.setTitle("Movies Details");
        mainStage.setScene(movieDetailsScene);
    }




    @Override
    public void previousBtnClicked() {
        mainStage.setScene(mainScene);

    }

    //function to handle initialization error (we can extend this to handle other errors)
    private void handleInitilizationError(Exception e){
        e.printStackTrace();
    }


    public static void main(String[] args) {
        launch(args);
    }
}