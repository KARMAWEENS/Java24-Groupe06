package org.example.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.java24groupe06.models.Movie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class HelloController {
    @FXML
    private Pane paneMoovie;
    private List<Movie> moviesList;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
       void OnclickImage(Movie movie) throws IOException, SQLException, ParseException;
    };

    public void setMovieList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }


    public static URL getViewURL() {
        return HelloController.class.getResource("hello-view.fxml");
    }

    private int nbRow;
    private final int nbColumn = 4;


    public void show() throws FileNotFoundException {

        GridPane gridPane = new GridPane( nbRow,nbColumn);

        gridPane.setHgap(10);
        gridPane.setVgap(10);

        paneMoovie.setStyle("-fx-background-color: #050505;");
        paneMoovie.getChildren().add(gridPane);

      //  this.nbRow = (int) Math.ceil((double) moviesList.size() / nbColumn);
        this.nbRow = moviesList.size();
        for(int row = 0; row< nbRow;row++){
            // TODO faire double boucle
            System.out.println("FAUDRA RAJOUTER UNE DOUBLE BOUCLE SI PAS TOUS LES FILMS");
            Movie movie = moviesList.get(row);
            Image image = new Image(new FileInputStream(movie.getPathImg()));
            ImageView imageView = new ImageView(image);

            SizeImage(imageView);

            imageView.setOnMouseClicked(event -> {

                try {
                    this.listener.OnclickImage(movie);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

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




