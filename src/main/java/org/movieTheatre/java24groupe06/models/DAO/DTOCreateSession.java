package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;

import java.io.Serializable;

public class DTOCreateSession implements Serializable {
    private int sessionID;

    public int getSessionID() {
        return sessionID;
    }

    public Movie getMovie() {
        return movie;
    }

    private Movie movie;

    public DTOCreateSession(int sessionID, Movie movie) {
        this.sessionID = sessionID;
        this.movie = movie;
    }
}
