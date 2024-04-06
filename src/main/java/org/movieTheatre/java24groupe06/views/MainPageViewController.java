package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.movieTheatre.java24groupe06.controllers.MainPageController;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.MovieModel;
import org.movieTheatre.java24groupe06.views.Components.MainScenePosterTemplateController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class MainPageViewController implements MainPageController{
    @FXML
    private MainScenePosterTemplateController mainScenePosterTemplateController;
    @FXML
    private ScrollPane scrollPane;
    private List<Movie> moviesList;
    private Listener listener;

    private MainPageView view;
    private MovieModel model;

    @Override
    public void initialize(MainPageView view, MovieModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void showMainPage() throws FileNotFoundException, SQLException, ParseException {
        List<Movie> moviesList = model.getShowingMovies();
        view.setMovieList(moviesList);
        view.show();
    }


    public void setMovieList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }


    public static URL getViewURL() {
        return MainPageViewController.class.getResource("mainPage-View.fxml");
    }

    private int nbRow;
    private final int nbColumn = 4;


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
                        if (listener != null) {
                            try {
                                System.out.println(listener);
                                listener.OnClickImage(moviesList.get(finalIndex));

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                    gridPane.add(root, column, row);


                    index++;
                }else{
                    return;
                }
            }
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

    private static void SizeImage(ImageView imageView) {
        final int desiredWidth = 180;
        final int desiredHeight = 240;
        imageView.setFitWidth(desiredWidth);
        imageView.setFitHeight(desiredHeight);
    }

    public void setListener(Listener listener) {
        this.listener = listener;
        System.out.println(listener);
    }

    public interface Listener {
        void OnClickImage(Movie movie) throws IOException, SQLException, ParseException;
    };



}




