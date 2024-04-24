package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.SessionDAO;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.exceptions.SetImageWithException;
import org.movieTheatre.java24groupe06.views.Components.SessionButton;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

public class MovieDetailsViewController extends AbstractViewController implements SetImageWithException, SessionDAO.SessionDAOInterface {

    private Movie movie;
    private List<Session> sessionList;
    private Listener listener;
    @FXML
    private Label title;
    @FXML
    private ImageView imageView;
    @FXML
    private Label actors;
    @FXML
    private Label duration;
    @FXML
    private TextArea synopsis;
    @FXML
    private Label genre;
    @FXML
    private Label producer;
    @FXML
    private Label date;
    @FXML
    private Button SessionButton;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox sessionButtonHBox;
    @FXML
    private GridPane gridPane;

    private static String titleStage = "Movies Details";
    private static Stage stage;

    public static void setStage(Stage stage) {
        MovieDetailsViewController.stage = stage;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }


    public static URL getViewURL() {
        return MovieDetailsViewController.class.getResource("MovieDetails-view.fxml");
    }

    //todo  tout la chaine de méthode est statique donc stage est statique à changer
    public MovieDetailsViewController showInStage(Stage movieDetailsStage) throws CantLoadFXMLException {
        setStage(movieDetailsStage);
        movieDetailsStage.initModality(Modality.APPLICATION_MODAL);
        return showFXMLOnStage(getViewURL(), movieDetailsStage, titleStage);
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }


    public void displayMovieDetails(Movie movie) {
        setMovie(movie);
        try {
            createSessionButton(movie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        setImageWithException(imageView, movie.getPathImg());
        title.setText(movie.getTitle());
        synopsis.setText(movie.getSynopsis());
        duration.setText(String.valueOf(movie.getDuration()) + " minutes");

        genre.setText((checkList(movie.getGenre(), "genre")));
        actors.setText((checkList(movie.getActors(), "acteur")));

        producer.setText(movie.getProducer());
        date.setText(movie.getReleaseDate());

        imageView.fitWidthProperty().bind(borderPane.widthProperty().divide(3));
        imageView.fitHeightProperty().bind(borderPane.heightProperty());
        imageView.minWidth(235);
        imageView.minHeight(332);

//
//        gridPane.prefWidthProperty().bind(anchorPane.widthProperty());
//        gridPane.prefHeightProperty().bind(anchorPane.heightProperty());
//
        sessionButtonHBox.spacingProperty().bind(borderPane.widthProperty().divide(6));
    }

    public String checkList(List list, String listType) {
        if (list.isEmpty()) {
            String message = "Aucun " + listType + " trouvé";
            AlertManager alertManager = new AlertManager();
            alertManager.minorDbError(message);
            return message;
        } else {
            return parseList(list);
        }
    }

    public String parseList(List<String> list) {

        StringBuilder genreStringList = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            genreStringList.append(list.get(i));
            if (i != list.size() - 1) {
                genreStringList.append(", ");
            }
        }
        return genreStringList.toString();
    }

    public void btnClicked() {
        // TODO faudrait surement faire pour dire au main controller
        //  de se ferme
        this.listener.previousBtnClicked(stage);
    }


    public void createSessionButton(Movie movie) throws SQLException {
         sessionList = getSession(movie);
        for(Session session : sessionList){
            org.movieTheatre.java24groupe06.views.Components.SessionButton sessionButton = new SessionButton(session);

            sessionButton.setOnAction(event -> {

               try {

                 TicketViewController ticketViewController = new TicketViewController().showInStage(new Stage());
                 ticketViewController.setSession(session);

                } catch (CantLoadFXMLException e) {
                    throw new RuntimeException(e);
                }
            });
            sessionButtonHBox.getChildren().add(sessionButton);
        }
    }

    public interface Listener {
        void previousBtnClicked(Stage stage);
        void sessionBtnClicked(Session session);
    }

}
