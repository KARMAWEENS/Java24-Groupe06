package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.CreateMovies;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.views.Components.MainScenePosterTemplateController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class MainPageViewController extends AbstractViewController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    private List<Movie> moviesList;
    private Listener listener;
    private static String title = "Movie Theatre";
    private int nbRow;
    private  int nbColumn ;
    private GridPane gridPane;
    public int widthStage;

    public void createPane(){
        GridPane gridPane = new GridPane(nbRow,nbColumn);
        this.gridPane = gridPane;
        setStyleStage(widthStage);
        scrollPane.setContent(gridPane);
    }
     public void onWidthChanged(double width){
        this.widthStage = (int) width;
         setStyleStage(width);
         if(calculatedColumn((int) width)!=nbColumn) {
            this.nbColumn = calculatedColumn((int) width);
            this.nbRow = calculatedRow();
            try {
                System.out.println("showing");
                show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setStyleStage(double width) {
        scrollPane.setPrefWidth(width);
        gridPane.setPrefWidth(width);
        gridPane.setStyle("-fx-background-color: #000000");
        gridPane.setPadding(new Insets(0,0,0,(width -nbColumn*180)/2));
    }

    private int calculatedColumn(int width) {
        return (int) Math.max((double) (width / MainScenePosterTemplateController.widthImage), 1);
    }
    private int calculatedRow() {
        return (int) Math.ceil((double) moviesList.size() / nbColumn);
    }


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
            e.printStackTrace();
        }
    }

    //logic to retrieve movie from db + more modularity
    private List<Movie> retrieveMovieFromDB() throws SQLException, ParseException {
        CreateMovies createMovies = new CreateMovies();
        return createMovies.getShowingMovies();
    }

    public void show() throws IOException {
        createPane();
        int index = 0;
        System.out.println(nbRow);
        System.out.println(nbColumn);
        for (int row = 0; row < nbRow; row++){
        for (int column = 0; column <nbColumn;column++){
                if(index < moviesList.size()){
                    FXMLLoader loader =  MainScenePosterTemplateController.getFXMLLoader();
                    final Parent root = loader.load();
                    final MainScenePosterTemplateController controller = loader.getController();
                    controller.setPoster(moviesList.get(index));
                    int finalIndex = index;
                    controller.setListener(() -> {
                        if (listener == null) return;
                        try {
                            listener.onClickImage(moviesList.get(finalIndex));
                        } catch (IOException | SQLException | ParseException e) {
                            throw new RuntimeException(e);
                        }
                    });
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
        void onClickImage(Movie movie) throws IOException, SQLException, ParseException;
    }
}




