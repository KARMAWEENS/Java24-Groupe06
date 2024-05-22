
package org.movieTheatre.java24groupe06.Network.exceptions;

import java.sql.SQLException;

public class SQLExceptionHandler {

    public void handle(SQLException e) {
        System.err.println("Une exception SQLException a été levée : " + e.getMessage());
        e.printStackTrace();
    }
}