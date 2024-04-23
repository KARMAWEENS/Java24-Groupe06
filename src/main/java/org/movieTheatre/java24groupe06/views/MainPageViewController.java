package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.CreateMovies;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.Components.MainScenePosterTemplateController;

import java.io.FileNotFoundException;
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
    private Stage mainStage;
    @FXML
    private ToolBar toolBar;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Stage stage;

    private void setWidthStage(int width) {
        this.widthStage = width;
    }


    public int widthStage;



    public void createPane(){
        GridPane gridPane = new GridPane(nbRow,nbColumn);
        this.gridPane = gridPane;
        gridPane.setId("myGridPane");
        setStyleStage(widthStage);
        scrollPane.setContent(gridPane);
        toolBar.prefWidthProperty().bind(scrollPane.widthProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        GridPane.setVgrow(gridPane, Priority.ALWAYS);
        System.out.println();
//        gridPane.setMinHeight();
    }
    public void onWidthChanged(int width) throws CantLoadFXMLException {
        setWidthStage(width);
        setStyleStage(width);
        if (calculatedColumn(width) != nbColumn) {
            onLoad(width);
        }
    }

    public void onLoad(int width) throws CantLoadFXMLException {
        // Quand probleme chargement db alors moviesList est null
        // Pierre veux qu on voit une page blanche
        // Moi je veux que l'appli s arrete
        if(moviesList == null){
            AlertManager alertManager = new AlertManager();
            alertManager.SQLExceptionAlert();
        }else {

            setWidthStage(width);
            setColumn(calculatedColumn(width));
            setRow(calculatedRow());
            show();
            setStyleStage(widthStage);
        }
    }

    private void setRow(int width) {
        this.nbRow = width;
    }

    private void setColumn(int width) {
        this.nbColumn = width;
    }



    private void setStyleStage(double width) {
        scrollPane.setPrefWidth(width);
        gridPane.setPrefWidth(width);
//        gridPane.setStyle("-fx-background-color: #000000");
        gridPane.setPadding(new Insets(0, 0, 0, (width - nbColumn * MainScenePosterTemplateController.widthImage) / 2));
    }
    private static int getWidthImage() {
        return MainScenePosterTemplateController.widthImage;
    }

    private int calculatedColumn(int width) {
        return (int) Math.max((double) (width / getWidthImage()), 1);
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

    public MainPageViewController showInStage(Stage mainStage) throws CantLoadFXMLException {
        return showFXMLOnStage(getViewURL(),mainStage,title);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setMovieList(retrieveMovieFromDB());
            show();
        } catch (SQLException e) {
            AlertManager alertManager = new AlertManager();
            alertManager.SQLExceptionAlert(e);
            //  this.listener.closeApp();
        } catch (CantLoadFXMLException e) {
            AlertManager alertManager = new AlertManager();
            alertManager.CantLoadPageAlert(e);
        }
    }

    //logic to retrieve movie from db + more modularity
    private List<Movie> retrieveMovieFromDB() throws SQLException{
        CreateMovies createMovies = new CreateMovies();
        return createMovies.getShowingMovies();
    }

    public void show() throws CantLoadFXMLException {
        createPane();
        int index = 0;

        for (int row = 0; row < nbRow; row++){
            for (int column = 0; column <nbColumn;column++){
                if(index < moviesList.size()){
                    FXMLLoader loader =  MainScenePosterTemplateController.getFXMLLoader();
                    final Parent root;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new CantLoadFXMLException(e);
                    }
                    final MainScenePosterTemplateController controller = loader.getController();

                    controller.setPoster(moviesList.get(index));

                    int finalIndex = index;
                    controller.setListener(() -> {
                        if (listener == null) return;

                        listener.onClickImage(moviesList.get(finalIndex));

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
        void onClickImage(Movie movie);

    }
}




