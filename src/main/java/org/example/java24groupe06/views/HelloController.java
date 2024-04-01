package org.example.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.example.java24groupe06.models.Moovies;
import org.example.java24groupe06.models.Position;
import org.example.java24groupe06.models.Poster;
import org.example.java24groupe06.models.Presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

public class HelloController {
    @FXML
    private Pane paneMoovie;
    @FXML
    private ScrollPane scrollPane;
    private Presentation presentation;

    public HelloController(Presentation presentation) {
        this.presentation = presentation;
    }


    public static URL getViewURL() {
        return HelloController.class.getResource("hello-view.fxml");
    }

    public void initialize() throws FileNotFoundException {
        GridPane gridPane = new GridPane( presentation.getNbRow(),presentation.getNbColumn());
        gridPane.setHgap(10); // Espacement horizontal de 10 pixels
        gridPane.setVgap(10);
        paneMoovie.setStyle("-fx-background-color: #050505;");
        paneMoovie.getChildren().add(gridPane);
        for (Poster poster : presentation.getPosterList()) {
            Image image = new Image(new FileInputStream(poster.getPathImgPoster()));
            ImageView imageView = new ImageView(image);
            double desiredWidth = 180;
            double desiredHeight = 240;
            imageView.setFitWidth(desiredWidth);
            imageView.setFitHeight(desiredHeight);
            imageView.setOnMouseClicked(event -> {
                // Récupérer le poster associé à l'image cliquée
                Poster clickedPoster = poster;
                // Utiliser le poster comme nécessaire (par exemple, afficher des détails)
                System.out.println("Vous avez cliqué sur : " + clickedPoster.getFilm());
            });
            gridPane.add(imageView,poster.getPositionColumn(), poster.getPositionRow());
        }

    }

}




