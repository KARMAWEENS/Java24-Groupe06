package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Movie;

import java.io.Serializable;
/**
 * The CreateSessionDTO class provides methods for creating a session.
 */
public class CreateSessionDTO implements Serializable {
    private int sessionID;
    private Movie movie;
    private String time;

    /**
     * The constructor for the CreateSessionDTO class.
     *
     * @param sessionID the ID of the session.
     * @param movie the movie for the session.
     * @param time the time of the session.
     */
    public CreateSessionDTO(int sessionID, Movie movie, String time) {
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
    /**
     * Retrieves the ID of the session.
     *
     * @return the ID of the session.
     */
    public int getSessionID() {
        return sessionID;
    }
    /**
     * Retrieves the movie for the session.
     *
     * @return the movie for the session.
     */
    public Movie getMovie() {
        return movie;
    }
    /**
     * Retrieves the time of the session.
     *
     * @return the time of the session.
     */
    public String getTime(){
        return time;
    }

}
