package org.movieTheatre.java24groupe06.views.Components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.SetImageWithException;

public class MainScenePosterTemplateController implements SetImageWithException {

    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;
    public static final int widthImage = 180;

    public void setListener(Listener listener) {
          this.listener = listener;
    }

    public Listener getListener() {
        return listener;
    }

    private Listener listener;

    public static FXMLLoader getFXMLLoader()   {
        return new FXMLLoader(MainScenePosterTemplateController.class.getResource("mainScenePosterTemplate.fxml"));
    }
    public void setPoster(Movie movie) {

        titleLabel.setText(movie.getTitle());
        setImageWithException(imageView, movie.getPathImg());


    }

    public void onPosterClicked(MouseEvent mouseEvent) {
        System.out.println("j'ai cliqué ahri");
    listener.OnClickImage();

    }
    public interface Listener {
        void OnClickImage();
    }

}


