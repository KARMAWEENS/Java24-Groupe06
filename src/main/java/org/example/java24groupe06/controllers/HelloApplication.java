package org.example.java24groupe06.controllers;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.utils.DataBase.CRUD.CreateMoviesFromDB;
import org.example.java24groupe06.utils.DataBase.CRUD.MovieRepository;
import org.example.java24groupe06.utils.DataBase.Utils.ConnectionDB;
import org.example.java24groupe06.views.HelloController;
import org.example.java24groupe06.views.MovieController;

import java.io.IOException;

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
            Connection conn = ConnectionDB.openDatabase();
            MovieRepository movieRepository = new CreateMoviesFromDB();
            MovieController movieController = new MovieController(movieRepository);
            movieController.showMovies();
            ConnectionDB.closeDatabase(conn);
        } catch (SQLException | ClassNotFoundException | ParseException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        launch();

    }
}