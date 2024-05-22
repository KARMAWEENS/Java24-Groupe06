
package org.movieTheatre.java24groupe06.Network.exceptions;

import java.io.IOException;

public class IOExceptionHandler {

    public void handle(IOException e) {
        System.err.println("Une exception IOException a été levée : " + e.getMessage());
        e.printStackTrace();
    }
}
