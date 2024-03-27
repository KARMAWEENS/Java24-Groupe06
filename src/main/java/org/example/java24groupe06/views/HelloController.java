package org.example.java24groupe06.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;

public class HelloController {
    @FXML
    private Label welcomeText;

    public static URL getViewURL(){ return HelloController.class.getResource("hello-view.fxml"); }

    @FXML
    protected void onConnectionButtonClick() {
        welcomeText.setText("Bientôt une page de connexion!");
    }


}