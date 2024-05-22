
package org.movieTheatre.java24groupe06.Network.exceptions;

public class ClassNotFoundExceptionHandler {

    public void handle(ClassNotFoundException e) {
        System.err.println("Une exception ClassNotFoundException a été levée : " + e.getMessage());
        e.printStackTrace();
    }
}
