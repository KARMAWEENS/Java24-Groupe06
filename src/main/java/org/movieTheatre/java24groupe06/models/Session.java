package org.movieTheatre.java24groupe06.models;

import java.sql.Time;
public class Session {
    private Movie movie;
    private Room room;
    private String hour;
    public Session(Movie movie,Room room,String hours){
        this.hour=hours;
        this.room=room;
        this.movie=movie;
    }
}
