package org.movieTheatre.java24groupe06.views.exceptions;

import javafx.scene.control.Alert;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.exceptions.CreateMoviesException;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AlertManager {
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
