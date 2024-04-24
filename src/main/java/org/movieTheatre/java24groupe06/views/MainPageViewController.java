package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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

public class MainPageViewController extends AbstractViewController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ToolBar toolBar;

    private static String title = "Movie Theatre";


    private Listener listener;
    private List<Movie> moviesList;

    private GridPane gridPane;
    private int nbRow;
    private int nbColumn;
    public int widthStage;


    private Stage mainStage;
    private Stage stage;

    public static URL getViewURL() {
        return MainPageViewController.class.getResource("mainPage-View.fxml");
    }

    public Listener getListener() {
        return listener;
    }


    public MainPageViewController(Listener listener) {
        System.out.println("dnas le contructeur");
        setListener(listener);
        System.out.println("je suis dans le constructeur " + this.listener);
    }

    public MainPageViewController() {

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setStage(Stage stage) {
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

    public void setMovieList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }


    public void createPane() {
        GridPane gridPane = new GridPane(nbRow, nbColumn);
        this.gridPane = gridPane;
        gridPane.setId("myGridPane");
        setStyleStage(widthStage);
        scrollPane.setContent(gridPane);
        toolBar.prefWidthProperty().bind(scrollPane.widthProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        // A voir si cette ligne est utile quand on met en commentaire ca changr rien
        GridPane.setVgrow(gridPane, Priority.ALWAYS);

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
        if (moviesList == null) {
            AlertManager alertManager = new AlertManager();
            alertManager.SQLExceptionAlert();
        } else {

            setWidthStage(width);
            setColumn(calculatedColumn(width));
            setRow(calculatedRow());
            show();
            setStyleStage(widthStage);
        }
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


    public MainPageViewController showInStage(Stage mainStage) throws CantLoadFXMLException {
        return showFXMLOnStage(getViewURL(), mainStage, title);
    }
//    public  MainPageViewController(URL fxmlUrl, Stage stage, String title){
//          super(fxmlUrl,stage,title);
//    }

/*    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            MainPageController mainPageController = new MainPageController();
            System.out.println(mainPageController.getMovieList());
            setMovieList(mainPageController.getMovieList());
            show();
        } catch (CantLoadFXMLException e) {
            AlertManager alertManager = new AlertManager();
            alertManager.CantLoadPageAlert(e);
        }
    }*/

    //logic to retrieve movie from db + more modularity

    public void show() throws CantLoadFXMLException {
        System.out.println("dans show" + listener);
        createPane();
        int index = 0;

        for (int row = 0; row < nbRow; row++) {
            for (int column = 0; column < nbColumn; column++) {
                if (index < moviesList.size()) {

                    try {
                        FXMLLoader loader = MainScenePosterTemplateController.getFXMLLoader();
                        Parent root = loader.load();
                        final MainScenePosterTemplateController controller = loader.getController();
                        controller.setPoster(moviesList.get(index));
                        int finalIndex = index;
                        controller.setListener(() -> {
                            if (listener == null) {
                                //TODO faut faire un throw new ou quoi
                                System.out.println("y a un pb faut gerer");
                            }
                            listener.onClickImage(moviesList.get(finalIndex));
                        });
                        gridPane.add(root, column, row);
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

    public interface Listener {
        void onClickImage(Movie movie);
    }
}




