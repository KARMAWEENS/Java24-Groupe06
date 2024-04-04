//package org.example.java24groupe06.controllers;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import org.example.java24groupe06.models.CreateMovies;
//import org.example.java24groupe06.models.Movie;
//import org.example.java24groupe06.views.MovieDetailsViewController;
//
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.List;
//
//public class tempHelloApp extends Application {
//    public void start(Stage stage) throws IOException, SQLException, ParseException {
//            FXMLLoader fxmlLoader = new FXMLLoader(MovieDetailsViewController.getViewURL());
//        Scene scene = new Scene(fxmlLoader.load());
//          MovieDetailsViewController movieDetailsViewController = fxmlLoader.getController();
//        CreateMovies createMovies = new CreateMovies();
//        List<Movie> movies = createMovies.getShowingMovies();
//            stage.setTitle("Hello!");
//            stage.setScene(scene);
//            stage.show();
//        movieDetailsViewController.buttonOnclick(movies.get(1));
//
//
//    }


//    public static void main(String[] args) {
//        Application.launch(tempHelloApp.class,args);
//
//    }
//
//}
