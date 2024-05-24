package org.movieTheatre.java24groupe06.Network.Event;

import org.movieTheatre.java24groupe06.models.Movie;

import java.io.Serializable;
/**
 * The GetDTOSessionListEvent class provides methods for managing the DTO session list event.
 */
public class GetDTOSessionListEvent implements Serializable {
    Movie movie;
    public GetDTOSessionListEvent(Movie movie){
       this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
