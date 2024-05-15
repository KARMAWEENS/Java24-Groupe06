package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.Movie;

import java.io.Serializable;

public class NetworkGetSession implements Serializable {
    Movie movie;
    public NetworkGetSession(Movie movie){
       this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
