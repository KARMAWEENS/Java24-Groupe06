package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;


import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.views.MainPageViewController;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;

import java.io.IOException;

public class MovieApplication extends Application implements MovieDetailsViewController.Listener, MainPageViewController.Listener{

    private Stage mainStage;

    private Scene mainScene;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {this.mainScene = mainScene;
    }

    @Override
    public void start(Stage stage)  {
        initializeMainStage(stage);
    }

    private void initializeMainStage(Stage stage)  {
            setMainStage(stage);
            MainPageViewController mainPageViewController = MainPageViewController.showInStage(mainStage);
            mainPageViewController.onWidthChanged(mainStage.getWidth());
            mainStage.widthProperty().addListener((obs, oldVal, newVal) -> {
                System.out.println("Width: " + newVal);
                mainPageViewController.onWidthChanged(newVal.doubleValue());
            });
            mainPageViewController.setListener(this);
            setMainScene(mainPageViewController.getScene());
    }

    @Override
    public void onClickImage(Movie movie) throws IOException {
        MovieDetailsViewController movieDetailsViewController = MovieDetailsViewController.showInStage(mainStage);
        movieDetailsViewController.setListener(this);
        movieDetailsViewController.displayMovieDetails(movie);
    }

    @Override
    public void previousBtnClicked() {
        mainStage.setScene(mainScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}