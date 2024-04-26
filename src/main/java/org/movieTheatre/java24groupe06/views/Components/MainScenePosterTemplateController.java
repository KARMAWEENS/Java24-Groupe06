package org.movieTheatre.java24groupe06.views.Components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.SetImageWithException;
import org.movieTheatre.java24groupe06.views.AbstractViewController;
import org.movieTheatre.java24groupe06.views.MainPageViewController;

public class MainScenePosterTemplateController extends AbstractViewController<MainScenePosterTemplateController.Listener> implements SetImageWithException {

    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;
    public static final int widthImage = 180;
    Movie movie;

    public MainScenePosterTemplateController(Listener listener, Movie movie) {
        super(listener);
        this.movie = movie;
    }

public void setPoster() {
    titleLabel.setText(movie.getTitle());
    setImageWithException(imageView, movie.getPathImg());
}
    public void onPosterClicked() {
        System.out.println("j'ai cliqué ahri");
        listener.OnClickImage(movie);
    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    public String getFXMLPath() {
        return "mainScenePosterTemplate.fxml";
    }

    public interface Listener {
        void OnClickImage(Movie movie);
    }

}


