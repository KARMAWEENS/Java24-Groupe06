package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.Components.MainScenePosterTemplateController;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * The WelcomePageViewController class provides methods for managing the welcome page view controller.
 */
public class WelcomePageViewController extends AbstractViewController<WelcomePageViewController.Listener> implements Initializable, MainScenePosterTemplateController.Listener  {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ToolBar toolBar;
    private List<Movie> moviesList;
    private GridPane gridPane;
    private int nbRow;
    private int nbColumn;
    public int widthStage;

    /**
     * Title of the page
     * @return
     */
    @Override
    protected String getTitle() {
        return "Movie Theatre";
    }

    /**
     * getFXMLPath method returns the FXML path.
     * @return
     */
    @Override
    public String getFXMLPath() {
        return "welcomePage-View.fxml";
    }
    /**
     * The WelcomePageViewController method is the constructor of the class.
     * @param listener the listener.
     * @param moviesList the movies list.
     * @param stage the stage.
     */
    public WelcomePageViewController(Listener listener, List<Movie> moviesList, Stage stage) {
        super(listener);
        this.moviesList = moviesList;
        this.stage = stage;
    }
    /**
     * The setWidthStage method sets the width of the stage.
     * @param width the width.
     */
    private void setWidthStage(int width) {
        this.widthStage = width;
    }
    /**
     * The setRow method sets the row.
     * @param width the width.
     */
    private void setRow(int width) {
        this.nbRow = width;
    }
    /**
     * The setColumn method sets the column.
     * @param width the width.
     */
    private void setColumn(int width) {
        this.nbColumn = width;
    }
    /**
     * The initialize method initializes the controller.
     * @param url the URL.
     * @param resourceBundle the resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setWidthListener(this, stage);
            displayGridMovies();
        } catch (CantLoadFXMLException e) {
            AlertManager.showErrorAlert("Erreur lors du chargement de la page", e);
        }
    }
    /**
     * The setWidthListener method sets the width listener.
     * @param welcomePageViewController the welcome page view controller.
     * @param stage the stage.
     */
    private void setWidthListener(WelcomePageViewController welcomePageViewController, Stage stage) {
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            try {
                welcomePageViewController.onWidthChanged(newVal.intValue());
            } catch (CantLoadFXMLException e) {
                // quasiment tt le temps unreachable sauf si le fichier devient inaccessible pdt le run de l app
                AlertManager.showErrorAlert("Erreur lors du chargement de la page", e);
            }
        });
    }
    /**
     * The getWidthImage method returns the width of the image.
     * @return the width of the image.
     */
    private static int getWidthImage() {
        return MainScenePosterTemplateController.widthImage;
    }
    /**
     * The calculatedColumn method calculates the column.
     * @param width the width.
     * @return the column.
     */
    private int calculatedColumn(int width) {
        return (int) Math.max((double) (width / getWidthImage()), 1);
    }

    private int calculatedRow() {
        return (int) Math.ceil((double) moviesList.size() / nbColumn);
    }
    /**
     * The createPane method creates the pane.
     */
    public void createPane() {
        GridPane gridPane = new GridPane(nbRow, nbColumn);
        this.gridPane = gridPane;
        gridPane.setId("myGridPane");
        scrollPane.setContent(gridPane);
        toolBar.prefWidthProperty().bind(scrollPane.widthProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        GridPane.setVgrow(gridPane, Priority.ALWAYS);
    }
    /**
     * The onWidthChanged method is called when the width is changed.
     * @param width the width.
     * @throws CantLoadFXMLException if an I/O error occurs.
     */
    public void onWidthChanged(int width) throws CantLoadFXMLException {
        setWidthStage(width);
        if (calculatedColumn(width) != nbColumn) {
            setWidthStage(widthStage);
            setColumn(calculatedColumn(widthStage));
            setRow(calculatedRow());
            displayGridMovies();
        }    setStyleStage();
    }
    /**
     * The setStyleStage method sets the style of the stage.
     */
    private void setStyleStage() {
        scrollPane.setPrefWidth(widthStage);
        gridPane.setPrefWidth(widthStage);
        gridPane.setAlignment(Pos.CENTER);
    }
    /**
     * The displayGridMovies method displays the grid of movies.
     * @throws CantLoadFXMLException if an I/O error occurs.
     */
    public void displayGridMovies() throws CantLoadFXMLException {
        createPane();
        int index = 0;
        for (int row = 0; row < nbRow; row++) {
            for (int column = 0; column < nbColumn; column++) {
                if (index < moviesList.size()) {
                    try {
                        MainScenePosterTemplateController mainScenePosterTemplateController = new MainScenePosterTemplateController(this, moviesList.get(index));
                        gridPane.add(mainScenePosterTemplateController.getRoot(), column, row);
                        mainScenePosterTemplateController.setPoster();
                        index++;
                    } catch (IOException e) {
                        throw new CantLoadFXMLException(e);
                    }
                } else {
                    return;
                }
            }
        }
    }

    /**
     * onClickImage method is called when the image is clicked.
     */
    @Override
    public void OnClickImage(Movie movie) throws CustomExceptions{
        listener.OnClickImage(movie);
    }


    public interface Listener{
        void OnClickImage(Movie movie) throws CustomExceptions;
    }
}




