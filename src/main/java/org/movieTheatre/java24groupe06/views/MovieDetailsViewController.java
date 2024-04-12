package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MovieDetailsViewController extends AbstractViewController {

    private Listener listener;
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

    private static String titleStage = "Movies Details";

    public static URL getViewURL() {
        return MovieDetailsViewController.class.getResource("MovieDetails-view.fxml");
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void displayMovieDetails(Movie movie) throws FileNotFoundException {
        title.setText(movie.getTitle());
        synopsis.setText(movie.getSynopsis());
        duration.setText(String.valueOf(movie.getDuration()));
        genre.setText(parseList(movie.getGenre()));
        actors.setText(parseList(movie.getActors()));
        producer.setText(movie.getProducer());
        date.setText(movie.getReleaseDate().toString());
        Image image1 = new Image(new FileInputStream(movie.getPathImg()));
        image.setImage(image1);
    }

    public static MovieDetailsViewController showInStage(Stage mainStage) throws IOException {
        return showFXMLOnStage(getViewURL(),mainStage,titleStage);

    }

    public void BtnClicked(){
        this.listener.previousBtnClicked();
    }

    public interface Listener {
        void previousBtnClicked();
    }

    public String parseList (List<String> list){

            StringBuilder genreStringList = new StringBuilder();
            for(int i = 0; i < list.size(); i++) {
                genreStringList.append(list.get(i));
                if (i != list.size() - 1) {
                    genreStringList.append(", ");
                }
            }
            return genreStringList.toString();

    }


}
