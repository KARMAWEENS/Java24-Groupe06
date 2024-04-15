package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractViewController {

    private static Scene scene;

    protected static <T extends AbstractViewController> T showFXMLOnStage(URL fxmlUrl, Stage stage, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        scene = new Scene(fxmlLoader.load());
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
