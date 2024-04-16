package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.exceptions.SetImageWithException;

import java.net.URL;
import java.util.List;

public class MovieDetailsViewController extends AbstractViewController implements SetImageWithException {

    private Listener listener;
    @FXML
    private Label title;
    @FXML
    private ImageView imageView;
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

    private static String titleStage = "Movies Details";

    public static URL getViewURL() {
        return MovieDetailsViewController.class.getResource("MovieDetails-view.fxml");
    }

    public static MovieDetailsViewController showInStage(Stage mainStage) throws CantLoadFXMLException {
            return showFXMLOnStage(getViewURL(), mainStage,titleStage);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void displayMovieDetails(Movie movie) {

        setImageWithException(imageView, movie.getPathImg());

        title.setText(movie.getTitle());
        synopsis.setText(movie.getSynopsis());
        duration.setText(String.valueOf(movie.getDuration()));

        genre.setText((checkList(movie.getGenre(), "genre")));
        actors.setText((checkList(movie.getActors(), "acteur")));


        producer.setText(movie.getProducer());
        date.setText(movie.getReleaseDate());

    }

    public String checkList(List list, String listType){
        if (list.isEmpty()){
            String message = "Aucun " + listType + " trouvé" ;
            AlertManager alertManager = new AlertManager();
            alertManager.minorDbError(message);
            return message;
        }else{
            return parseList(list);
        }
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

    public void btnClicked(){
        this.listener.previousBtnClicked();
    }

    public interface Listener {
        void previousBtnClicked();
    }

}
