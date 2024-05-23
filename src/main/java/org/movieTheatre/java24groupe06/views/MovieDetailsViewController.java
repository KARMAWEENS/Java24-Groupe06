package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.movieTheatre.java24groupe06.models.DAO.CreateSessionDTO;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;
import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.SetImageWithException;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import java.sql.SQLException;
import java.util.List;

public class MovieDetailsViewController extends AbstractViewController<MovieDetailsViewController.Listener> implements SetImageWithException {

    private Movie movie;
    private List<CreateSessionDTO> DTOSessionsList;
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


    @Override
    protected String getTitle() {
        return "Movies Details";
    }

    public MovieDetailsViewController(Listener listener, Movie movie) {
        super(listener);
        this.movie = movie;
    }
    private AlertManager alertManager = new AlertManager();
    public void displayMovieDetails() throws CustomExceptions{
        try {
            setTextMovie(movie);
            setImage(imageView, movie.getPathImg());
            setImageViewProprety();
            createSessionButton();
        } catch (SQLException e) {
            throw new CustomExceptions("Failed to create session button", e, ErrorCode.SESSION_CREATION_ERROR);
        }
        sessionButtonHBox.spacingProperty().bind(borderPane.widthProperty().divide(6));
    }

    private void setImageViewProprety() {
        //todo pas de chiffre magique a changer
        imageView.fitWidthProperty().bind(borderPane.widthProperty().divide(3));
        imageView.fitHeightProperty().bind(borderPane.heightProperty());
        imageView.minWidth(235);
        imageView.minHeight(332);
    }

    private void setTextMovie(Movie movie) {
        title.setText(movie.getTitle());
        synopsis.setText(movie.getSynopsis());
        duration.setText(movie.getDuration() + " minutes");
        genre.setText((checkList(movie.getGenres(), "genre")));
        actors.setText((checkList(movie.getActors(), "acteur")));
        producer.setText(movie.getProducer());
        date.setText(movie.getReleaseDate());
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
        this.listener.previousBtnClicked();
    }

    public void createSessionButton() throws SQLException, CustomExceptions {
        DTOSessionsList = listener.getDTOSessionList(movie);
        for (CreateSessionDTO createSessionDTO : DTOSessionsList) {
            Button sessionButton = new Button(createSessionDTO.getTime());
            sessionButton.setOnAction(event -> {
                try {
                    listener.sessionBtnClicked(createSessionDTO);
                } catch (CustomExceptions e) {
                    AlertManager.showErrorAlert("La creation d'un bouton de session a échouée", e);
                }
            });
            sessionButtonHBox.getChildren().add(sessionButton);
        }
    }

    @Override
    public String getFXMLPath() {
        return "movieDetails-view.fxml";
    }

    public interface Listener {
        void previousBtnClicked();

        void sessionBtnClicked(CreateSessionDTO createSessionDTO) throws CustomExceptions;

        List<CreateSessionDTO> getDTOSessionList(Movie movie) throws SQLException, CustomExceptions;
    }
}
