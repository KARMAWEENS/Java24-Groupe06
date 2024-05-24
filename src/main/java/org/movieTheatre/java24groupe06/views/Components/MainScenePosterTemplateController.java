package org.movieTheatre.java24groupe06.views.Components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.SetImageWithException;
import org.movieTheatre.java24groupe06.views.AbstractViewController;
/**
 * The MainScenePosterTemplateController class provides methods for managing the main scene poster template controller.
 */
public class MainScenePosterTemplateController extends AbstractViewController<MainScenePosterTemplateController.Listener> implements SetImageWithException {

    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;
    public static final int widthImage = 180;
    Movie movie;
    /**
     * The MainScenePosterTemplateController method is the constructor of the class.
     * @param listener the listener
     * @param movie the movie
     */
    public MainScenePosterTemplateController(Listener listener, Movie movie) {
        super(listener);
        this.movie = movie;
    }
/**
     * The setPoster method sets the poster.
     */
public void setPoster() {
    titleLabel.setText(movie.getTitle());
    setImage(imageView, movie.getPathImg());
}
    public void onPosterClicked() throws CustomExceptions{
        listener.OnClickImage(movie);
    }
    /**
     * getTitle method returns the title.
     */
    @Override
    protected String getTitle() {
        return null;
    }
    /**
     * The getFXMLPath method returns the FXML path.
     * @return the FXML path.
     */
    @Override
    public String getFXMLPath() {
        return "mainScenePosterTemplate.fxml";
    }
    /**
     * The Listener interface provides methods for managing the listener.
     */
    public interface Listener {
        void OnClickImage(Movie movie) throws CustomExceptions;
    }

}


