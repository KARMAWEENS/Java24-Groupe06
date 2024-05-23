package org.movieTheatre.java24groupe06.Network.exceptions;

public class HandleExceptions {
    public void handleException(String message, Exception e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
        e.getCause().printStackTrace();
    }
}