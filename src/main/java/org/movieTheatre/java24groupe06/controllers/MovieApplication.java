package org.movieTheatre.java24groupe06.controllers;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;


import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.AlertManager;
import org.movieTheatre.java24groupe06.views.MainPageViewController;
import org.movieTheatre.java24groupe06.views.MovieDetailsViewController;

import java.io.IOException;
import java.util.SplittableRandom;

import static org.movieTheatre.java24groupe06.views.MainPageViewController.showInStage;

public class MovieApplication extends Application implements MovieDetailsViewController.Listener, MainPageViewController.Listener {

    private Stage mainStage;

    private Scene mainScene;

    private Stage detailsStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }

    public void setDetailsStage(Stage detailsStage) {
        this.detailsStage = detailsStage;
    }


    @Override
    public void start(Stage stage) {
        initializeMainStage(stage);
    }

    private void initializeMainStage(Stage stage) {
        try {
            setMainStage(stage);
            MainPageViewController mainPageViewController = showInStage(mainStage);
            mainPageViewController.onLoad((int) mainStage.getWidth());
            setWidthListener(mainPageViewController);
            mainPageViewController.setListener(this);
            setMainScene(mainPageViewController.getScene());
        } catch (CantLoadFXMLException e) {
            AlertManager alertManager = new AlertManager();
            alertManager.CantLoadPageAlert(e);
            // potentielement faire un truc du genre
            // start (mainStage)
        }
    }

    private void initializeDetailsStage(Movie movie) {
        try {
            setDetailsStage(new Stage());
            MovieDetailsViewController movieDetailsViewController = MovieDetailsViewController.showInStage(detailsStage);
            movieDetailsViewController.setListener(this);
            movieDetailsViewController.displayMovieDetails(movie);
        } catch (CantLoadFXMLException e) {
            AlertManager alertManager = new AlertManager();
            alertManager.CantLoadPageAlert(e);
        }
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
    public void onClickImage(Movie movie)  {
        try {
            MovieDetailsViewController movieDetailsViewController = MovieDetailsViewController.showInStage(mainStage);
            movieDetailsViewController.setListener(this);
            movieDetailsViewController.displayMovieDetails(movie);
        } catch (CantLoadFXMLException e) {
            AlertManager alertManager = new AlertManager();
            alertManager.CantLoadPageAlert(e);
        }
    }

    @Override
    public void closeApp(){
        this.mainStage.close();
    }
    @Override
    public void previousBtnClicked() {
        mainStage.setScene(mainScene);
        mainStage.setTitle("Movie Theatre");
    }

    public static void main(String[] args) {
        launch(args);
    }
}