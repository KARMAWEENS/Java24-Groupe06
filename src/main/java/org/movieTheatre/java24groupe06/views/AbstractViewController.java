package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;

import java.io.IOException;
import java.net.URL;
/**
 * The AbstractViewController class provides methods for managing the abstract view controller.
 * @param <T> the type of the listener.
 */

public abstract class AbstractViewController<T> {

    private Parent root;

    protected Stage stage;
    /**
     * The getTitle method returns the title.
     * @return the title.
     */
    protected String getTitle() {
        return null;
    }

    protected T listener;
    /**
     * The AbstractViewController method is the constructor of the class.
     * @param listener the listener.
     */
    public AbstractViewController(T listener) {
        this.listener = listener;
    }
    /**
     * The getFXMLPath method returns the FXML path.
     * @return the FXML path.
     */
    public abstract String getFXMLPath();
    public Parent getRoot() throws IOException {
        if (this.root == null) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(getFXMLPath()));
            loader.setController(this);
            this.root = loader.load();

        }
        return this.root;
    }
    /**
     * The openOn method opens the view on the stage.
     * @param stage the stage.
     * @throws IOException if an I/O error occurs.
     */
    public void openOn(Stage stage) throws IOException {
        Scene scene = new Scene(getRoot());
        stage.setScene(scene);
        stage.setTitle(getTitle());
        this.stage = stage;
        stage.setScene(scene);
        stage.show();
    }
    /**
     * The openOnNewStage method opens the view on a new stage.
     * @throws IOException if an I/O error occurs.
     */
    public void openOnNewStage() throws IOException {
        Stage newStage = new Stage();
        this.stage = newStage;
        this.stage.initModality(Modality.APPLICATION_MODAL); // Ajoutez cette ligne pour rendre la fenêtre modale
        openOn(newStage);
    }
    /**
     * The close method closes the view.
     * @throws IllegalStateException if the view was never opened.
     */
    public void close() throws IllegalStateException {
        if (this.stage != null) {
            this.stage.close();
        } else {
            throw new IllegalStateException("Cannot close a view that was never opened");
        }
    }
}
