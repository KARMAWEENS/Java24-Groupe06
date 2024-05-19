package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class DTOCreateSession implements Serializable {
    private int sessionID;
    private Movie movie;
    private String time;

    public DTOCreateSession(int sessionID, Movie movie, String time) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        if (time == null || time.isEmpty()) {
            throw new IllegalArgumentException("Time cannot be null or empty");
        }
        this.sessionID = sessionID;
        this.movie = movie;
        this.time = time;


    }

    public int getSessionID() {
        return sessionID;
    }
    public Movie getMovie() {
        return movie;
    }
    public String getTime(){
        return time;
    }

}
