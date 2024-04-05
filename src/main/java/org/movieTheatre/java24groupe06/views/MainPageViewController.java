package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.movieTheatre.java24groupe06.controllers.MainPageController;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.MovieModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class MainPageViewController implements MainPageController{
    @FXML
    private Pane paneMovie;
    private List<Movie> moviesList;

    private MainPageView view;
    private MovieModel model;

    @Override
    public void initialize(MainPageView view, MovieModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void showMainPage() throws FileNotFoundException, SQLException, ParseException {
        List<Movie> moviesList = model.getShowingMovies();
        view.setMovieList(moviesList);
        view.show();
    }


    public void setMovieList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }


    public static URL getViewURL() {
        return MainPageViewController.class.getResource("mainPage-View.fxml");
    }

    private int nbRow;
    private final int nbColumn = 4;


    public void show() throws FileNotFoundException {

        GridPane gridPane = new GridPane( nbRow,nbColumn);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        paneMovie.setStyle("-fx-background-color: #050505;");
        paneMovie.getChildren().add(gridPane);

        this.nbRow = moviesList.size();
        for(int row = 0; row< nbRow;row++){
            Movie movie = moviesList.get(row);
            Image image = new Image(new FileInputStream(movie.getPathImg()));
            ImageView imageView = new ImageView(image);
            SizeImage(imageView);
            imageView.setOnMouseClicked(event -> {
                System.out.println("Vous avez cliqué sur : " + movie.getTitle());
            });
            gridPane.add(imageView,row, 0);
        }
    }

    private static void SizeImage(ImageView imageView) {
        final int desiredWidth = 180;
        final int desiredHeight = 240;
        imageView.setFitWidth(desiredWidth);
        imageView.setFitHeight(desiredHeight);
    }
}




