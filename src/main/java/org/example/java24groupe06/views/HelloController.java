package org.example.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.java24groupe06.models.Movie;
import org.example.java24groupe06.models.Presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

public class HelloController {
    @FXML
    private Pane paneMoovie;
    private List<Movie> moviesList;
    private Presentation presentation;

    public void setMovieList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public static URL getViewURL() {
        return HelloController.class.getResource("hello-view.fxml");
    }



    public void show() throws FileNotFoundException {

        GridPane gridPane = new GridPane( presentation.getNbRow(),presentation.getNbColumn());

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        paneMoovie.setStyle("-fx-background-color: #050505;");
        paneMoovie.getChildren().add(gridPane);


        for(int row = 0; row<= presentation.getNbRow();row++){
            // TODO faire double boucle
            System.out.println("FAUDRA RAJOUTER UNE DOUBLE BOUCLE SI PAS TOUS LES FILMS");
            Movie movie = moviesList.get(row);
            Image image = new Image(new FileInputStream(movie.getPathImg()));
            ImageView imageView = new ImageView(image);

            SizeImage(imageView);

            imageView.setOnMouseClicked(event -> {

                // Utiliser le poster comme nécessaire (par exemple, afficher des détails)
                System.out.println("Vous avez cliqué sur : " + movie.getTitle());
            });
            gridPane.add(imageView,row, 0);
        }

        /*for (Poster poster : presentation.getPosterList()) {

            Image image = new Image(new FileInputStream(poster.getPathImgPoster()));
            ImageView imageView = new ImageView(image);

            SizeImage(imageView);




            imageView.setOnMouseClicked(event -> {
                // Récupérer le poster associé à l'image cliquée
                Poster clickedPoster = poster;
                // Utiliser le poster comme nécessaire (par exemple, afficher des détails)
                System.out.println("Vous avez cliqué sur : " + clickedPoster.getFilm());
            });
            gridPane.add(imageView,0, 0);
        }
*/
    }

    private static void SizeImage(ImageView imageView) {
        final int desiredWidth = 180;
        final int desiredHeight = 240;
        imageView.setFitWidth(desiredWidth);

        imageView.setFitHeight(desiredHeight);
    }

}




