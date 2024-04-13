package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.controllers.MainPageController;
import org.movieTheatre.java24groupe06.models.CreateMovies;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.MovieModel;
import org.movieTheatre.java24groupe06.views.Components.MainScenePosterTemplateController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageViewController extends AbstractViewController implements Initializable {

    // c 'est useless
    private MainScenePosterTemplateController mainScenePosterTemplateController;

    @FXML
    private ScrollPane scrollPane;
    private List<Movie> moviesList;
    private Listener listener;
    private static String title = "Movie Theatre";

    private int nbRow;
    private final int nbColumn = 4;

    public static URL getViewURL() {
        return MainPageViewController.class.getResource("mainPage-View.fxml");
    }

    public void setListener(Listener listener) {
        this.listener = listener;

    }
    public void setMovieList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public static MainPageViewController showInStage(Stage mainStage) {
        try {
            return showFXMLOnStage(getViewURL(), mainStage,title);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setMovieList(retrieveMovieFromDB());
            show();
        } catch (SQLException | ParseException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    //logic to retrieve movie from db + more modularity
    private List<Movie> retrieveMovieFromDB() throws SQLException, ParseException {
        CreateMovies createMovies = new CreateMovies();
        return createMovies.getShowingMovies();
    }

    public void show() throws IOException {

      this.nbRow = (int) Math.ceil((double) moviesList.size() / nbColumn);
        GridPane gridPane = new GridPane( nbRow,nbColumn);

        gridPane.setHgap(10);
        gridPane.setVgap(10);


        scrollPane.setContent(gridPane);

        int index = 0;
        for (int row = 0; row < nbRow; row++){
        for (int column = 0; column <nbColumn;column++){
                if(index < moviesList.size()){

                    FXMLLoader loader =  MainScenePosterTemplateController.getFXMLLoader();
                    final Parent root = loader.load();
                    root.setStyle("-fx-background-color: red;");
                    gridPane.setStyle("-fx-background-color: black;");
                    final MainScenePosterTemplateController controller = loader.getController();
                    controller.setPoster(moviesList.get(index));
                    int finalIndex = index;
                    controller.setListener(() -> {

                        if (listener == null) return;
                        try {

                            listener.OnClickImage(moviesList.get(finalIndex));
                        } catch (IOException | SQLException | ParseException e) {
                            throw new RuntimeException(e);
                        }

                    });

                    // LE SET LISTENER SANS LAMBDA
/*                    controller.setListener(new MainScenePosterTemplateController.Listener() {
                        @Override
                        public void OnClickImage() {
                            if (listener == null) return;
                            try {
                                System.out.println(finalIndex + " 2" + controller.getListener());
                                listener.OnClickImage(moviesList.get(finalIndex));
                            } catch (IOException | SQLException | ParseException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(finalIndex + " 3" + controller.getListener());
                        }
                    });
                    */


                    gridPane.add(root, column, row);


                    index++;
                }else{
                    return;
                }
            }
        }
    }

    private static void SizeImage(ImageView imageView) {
        final int desiredWidth = 180;
        final int desiredHeight = 240;
        imageView.setFitWidth(desiredWidth);
        imageView.setFitHeight(desiredHeight);
    }


    public interface Listener {
        void OnClickImage(Movie movie) throws IOException, SQLException, ParseException;
    };



}




