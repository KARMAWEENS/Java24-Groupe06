package org.movieTheatre.java24groupe06.models.exceptions;
/**
 * The DataAccessException class provides methods for handling exceptions when accessing data.
 */
public class DataAccessException extends Exception {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
