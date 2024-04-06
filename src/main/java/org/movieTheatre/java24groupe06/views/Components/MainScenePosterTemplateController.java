package org.movieTheatre.java24groupe06.views.Components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.movieTheatre.java24groupe06.controllers.MovieApplication;
import org.movieTheatre.java24groupe06.models.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainScenePosterTemplateController {

    @FXML
    private ImageView imageView;
    @FXML
    private Label titleLabel;

    public void setListener(Listener listener) {
          this.listener = listener;
    }

    private Listener listener;

    public static FXMLLoader getFXMLLoader() throws IOException {

        FXMLLoader loader = new FXMLLoader(MainScenePosterTemplateController.class.getResource("mainScenePosterTemplate.fxml"));
        return loader;
    }
    public void setPoster(Movie movie) throws FileNotFoundException {

        Image image = new Image(new FileInputStream(movie.getPathImg()));
        imageView.setImage(image);
        titleLabel.setText(movie.getTitle());

    }

    public void onPosterClicked(MouseEvent mouseEvent) {
   listener.OnClickImage();

    }
    public interface Listener {
        void OnClickImage();
    }



    /*        for(int row = 0; row< nbRow;row++){
            Movie movie = moviesList.get(row);
            Image image = new Image(new FileInputStream(movie.getPathImg()));
            ImageView imageView = new ImageView(image);
            SizeImage(imageView);
            imageView.setOnMouseClicked(event -> {

                try {
                    this.listener.OnClickImage(movie);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            });
            gridPane.add(imageView,row, 0);
        }*/
}


