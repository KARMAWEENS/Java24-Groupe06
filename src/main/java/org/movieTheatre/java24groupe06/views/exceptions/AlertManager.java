package org.movieTheatre.java24groupe06.views.exceptions;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;

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

    public void CantLoadPageAlert(CantLoadFXMLException e) {
      //  e.printStackTrace();
        e.getCause().printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors du chargement de la page");
        alert.showAndWait();
    }

    public void FileNotFoundExceptionAlert(FileNotFoundException e){
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Impossible de charger l'image");
        alert.showAndWait();
    }

    public void SQLExceptionAlert(SQLException e){
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR, "Impossible de recevoir les informations des films");
        alert.showAndWait();
    }
    public void SQLExceptionAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Problème lors de la récupération des données");
        alert.showAndWait();
    }
    public void minorDbError(String string){
        Alert alert = new Alert(Alert.AlertType.ERROR, string);
        alert.showAndWait();
    }
}
