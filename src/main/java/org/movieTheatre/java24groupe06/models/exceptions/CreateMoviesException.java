package org.movieTheatre.java24groupe06.models.exceptions;
/**
 * The CreateMoviesException class provides methods for handling exceptions when creating a list of movies.
 */
public class CreateMoviesException extends Exception {
    public CreateMoviesException(Throwable cause) {
        super("Erreur lors de la création de la liste de films", cause);
    }
}