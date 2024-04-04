package org.example.java24groupe06.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.java24groupe06.views.MovieDetailsViewController;


import java.io.IOException;

public class tempHelloApp extends Application {
    public void start(Stage stage) throws IOException
    {
            FXMLLoader fxmlLoader = new FXMLLoader(MovieDetailsViewController.getViewURL());
            Scene scene = new Scene(fxmlLoader.load());
            MovieDetailsViewController movieDetailsViewController = fxmlLoader.getController();
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

    }


    public static void main(String[] args) {
        Application.launch(tempHelloApp.class,args);

    }

}
