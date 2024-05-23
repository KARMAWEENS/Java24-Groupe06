package org.movieTheatre.java24groupe06.views.Components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.SetImageWithException;
import org.movieTheatre.java24groupe06.views.AbstractViewController;

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
    setImage(imageView, movie.getPathImg());
}
    public void onPosterClicked() throws CustomExceptions{
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
        void OnClickImage(Movie movie) throws CustomExceptions;
    }

}


