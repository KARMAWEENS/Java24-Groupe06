package org.movieTheatre.java24groupe06.Network.exceptions;
/**
 * The HandleExceptions class provides methods for managing the exceptions.
 */
public class HandleExceptions {
    public void handleException(String message, Exception e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
        e.getCause().printStackTrace();
    }
}