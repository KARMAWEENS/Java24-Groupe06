package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractViewController {

    private static Scene scene;

    protected static <T extends AbstractViewController> T showFXMLOnStage(URL fxmlUrl, Stage stage, String title) throws CantLoadFXMLException {
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        try {
            scene = new Scene(fxmlLoader.load());
        // l'exception arrive quand le ficher fxml est pas trouvé ou mauvais
        // pour generer cette exception on doit ajouter de la merde dans le fichier fxml (genre dfsokdsfkosdkfo)
        } catch (IOException e) {
            throw new CantLoadFXMLException(e);
        }

        T viewController = fxmlLoader.getController();
        stage.setScene(scene);
        stage.setTitle(title);
        // Le stage.show est pas appelé quand on passe de mainStage à detailsPage a voir comment en gère ca
        stage.show();
        return viewController;
    }

    public Scene getScene() {
        return scene;
    }
}
