package org.movieTheatre.java24groupe06.Network.Event;

import org.movieTheatre.java24groupe06.models.Movie;

import java.io.Serializable;

public class NetworkGetDTOSessionList implements Serializable {
    Movie movie;
    public NetworkGetDTOSessionList(Movie movie){
       this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
