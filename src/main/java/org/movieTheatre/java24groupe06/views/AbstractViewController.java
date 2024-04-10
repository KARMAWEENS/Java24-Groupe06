package org.movieTheatre.java24groupe06.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractViewController {


    protected static <T extends AbstractViewController> T showFXMLOnStage(URL fxmlUrl, Stage stage, String title) throws IOException {
        // création de la vue
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        Scene scene = new Scene(fxmlLoader.load());

        T viewController = fxmlLoader.getController();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
        return viewController;
    }
}
