package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.movieTheatre.java24groupe06.models.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class MovieDetailsViewController {
    public interface Listener {
        void previousBtnClicked();
    }
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }
public void BtnClicked(){
        this.listener.previousBtnClicked();
}
    public static URL getViewURL() {
        return MovieDetailsViewController.class.getResource("MovieDetails-view.fxml");
    }
    @FXML
    private Label title;
    @FXML
    private ImageView image;
    @FXML
    private Label actors;
    @FXML
    private Label duration;
    @FXML
    private Label synopsis;
    @FXML
    private Label genre;
    @FXML
    private Label producer;
    @FXML
    private Label date;

    @FXML
    private Button previousSceneBtn;

    public void displayMovieDetails(Movie movie) throws FileNotFoundException {
   title.setText(movie.getTitle());
   synopsis.setText(movie.getSynopsis());

   duration.setText(String.valueOf(movie.getDuration()));
  genre.setText(movie.getStringListGenre());
  actors.setText(movie.getStringListActors());
  producer.setText(movie.getProducer());
  date.setText(movie.getReleaseDate().toString());
   Image image1 = new Image(new FileInputStream(movie.getPathImg()));
   image.setImage(image1) ;
    }

}
