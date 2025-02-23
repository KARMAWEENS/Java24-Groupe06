package org.movieTheatre.java24groupe06.models.exceptions;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 * The SetImageWithException interface provides a method for setting an image with an exception.
 */
public interface SetImageWithException {

     default void setImage(ImageView imageView, String imagePath) {
        try {
            Image image = new Image(new FileInputStream(imagePath));
            imageView.setImage(image);
        } catch (FileNotFoundException e) {
            try {
                Image image = new Image(new FileInputStream("src/main/resources/DataBase/ErrorImg/1000_F_299617487_fPJ8v9Onthhzwnp4ftILrtSGKs1JCrbh.jpg"));
                imageView.setImage(image);
            } catch (FileNotFoundException exception) {
                AlertManager.showErrorAlert("Erreur lors du chargement de l'image", exception);
            }
        }
    }
}
