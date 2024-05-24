package org.movieTheatre.java24groupe06.views.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.exceptions.CreateMoviesException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

public class AlertManager {

    public static void showErrorAlert(String message, Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(message);

        alert.showAndWait();
        exception.printStackTrace();
    }


    public static void showErrorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void TicketBoughtAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Votre achat a bien été effectué");

        alert.showAndWait();
    }
}
