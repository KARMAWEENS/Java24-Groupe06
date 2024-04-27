package org.movieTheatre.java24groupe06.models;

import java.sql.Time;
public class Session {
    private Movie movie;
    private Room room;
    private String hour;

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public String getHour() {
        return hour;
    }


    public Session(Movie movie,Room room,String hours){
        this.hour=hours;
        this.room=room;
        this.movie=movie;
    }

}
