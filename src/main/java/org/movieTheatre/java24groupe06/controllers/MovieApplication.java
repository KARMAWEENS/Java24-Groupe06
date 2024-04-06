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

        try {
            this.mainStage = stage;

            // Retrieve movies from the database and display the main page
            List<Movie> movies = retrieveMovieFromDB();
            displayMainPage(mainStage, movies);
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
        mainScene = new Scene(fxmlLoader.load());
        MainPageViewController mainPageViewController = fxmlLoader.getController();
        mainPageViewController.setListener(this);
        mainPageViewController.setMovieList(movies);
        mainPageViewController.show();
        stage.setTitle("Movie Theatre");
        stage.setScene(mainScene);
        stage.show();
    }

    @Override
    public void OnClickImage(Movie movie) throws IOException, SQLException, ParseException {

        FXMLLoader fxmlLoader = new FXMLLoader(MovieDetailsViewController.getViewURL());

        Scene movieDetailsScene = new Scene(fxmlLoader.load());

        MovieDetailsViewController movieDetailsViewController = fxmlLoader.getController();

        movieDetailsViewController.displayMovieDetails(movie);

        movieDetailsViewController.setListener(this);
        mainStage.setTitle("Hello!");
        mainStage.setScene(movieDetailsScene);
    }

    public static void test(Movie movie) {
        System.out.println(movie);
    }


    @Override
    public void previousBtnClicked() {
        mainStage.setScene(mainScene);
        System.out.println(mainScene);
    }

    //function to handle initialization error (we can extend this to handle other errors)
    private void handleInitilizationError(Exception e){
        e.printStackTrace();
    }


    public static void main(String[] args) {
        launch(args);
    }
}