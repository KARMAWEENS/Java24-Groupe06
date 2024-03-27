package org.example.java24groupe06.controllers;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.views.HelloController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.getViewURL());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

    }
}