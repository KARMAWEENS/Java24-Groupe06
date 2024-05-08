package org.movieTheatre.java24groupe06.models;

import java.io.Serializable;

public class Session implements Serializable {
    private int SessionID;
    private Movie movie;
    private SeatsRoomLeft seatsRoomLeft;
    private String Time;


    public Session(int SessionID, Movie movie, SeatsRoomLeft seatsRoomLeft, String hours){
        this.Time =hours;
        this.seatsRoomLeft = seatsRoomLeft;
        this.movie=movie;
        this.SessionID = SessionID;
    }

    public int getSessionID() {
        return SessionID;
    }

    public Movie getMovie() {
        return movie;
    }
    public SeatsRoomLeft getRoom() {
        return seatsRoomLeft;
    }

    public String getTime() {
        return Time;
    }

    public int getNbHandicapsSeats(){
        return seatsRoomLeft.getNbHandicapsSeats();
    }
    public int getNbRegularSeats(){
        return seatsRoomLeft.getNbRegularSeats();
    }
   public int getNbVIPSeats(){
        return seatsRoomLeft.getNbVIPSeats();
   }

    public void setSeatsRoomLeft(SeatsRoomLeft seatsRoomLeft) {
        this.seatsRoomLeft = seatsRoomLeft;
    }
}
