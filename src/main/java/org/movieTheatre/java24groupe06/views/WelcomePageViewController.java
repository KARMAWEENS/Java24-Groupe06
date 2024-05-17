package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.Components.MainScenePosterTemplateController;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

    @Override
    protected String getTitle() {
        return "Movie Theatre";
    }

    @Override
    public String getFXMLPath() {
        return "welcomePage-View.fxml";
    }

    public WelcomePageViewController(Listener listener, List<Movie> moviesList, Stage stage) {
        super(listener);
        this.moviesList = moviesList;
        this.stage = stage;
    }

    private void setWidthStage(int width) {
        this.widthStage = width;
    }

    private void setRow(int width) {
        this.nbRow = width;
    }

    private void setColumn(int width) {
        this.nbColumn = width;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setWidthListener(this, stage);
            show();
        } catch (CantLoadFXMLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setWidthListener(WelcomePageViewController welcomePageViewController, Stage stage) {
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            try {
                welcomePageViewController.onWidthChanged(newVal.intValue());
            } catch (CantLoadFXMLException e) {
                // quasiment tt le temps unreachable sauf si le fichier devient inaccessible pdt le run de l app
                AlertManager alertManager = new AlertManager();
                alertManager.CantLoadPageAlert(e);
            }
        });
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

    public void onWidthChanged(int width) throws CantLoadFXMLException {
        setWidthStage(width);
        if (calculatedColumn(width) != nbColumn) {
            setWidthStage(widthStage);
            setColumn(calculatedColumn(widthStage));
            setRow(calculatedRow());
            show();
        }    setStyleStage();

    }

    private void setStyleStage() {
        scrollPane.setPrefWidth(widthStage);
        gridPane.setPrefWidth(widthStage);
        gridPane.setAlignment(Pos.CENTER);
    }

    public void show() throws CantLoadFXMLException {
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

    @Override
    public void OnClickImage(Movie movie) {
        listener.OnClickImage(movie);
    }


    public interface Listener{
        void OnClickImage(Movie movie);
    }
}




