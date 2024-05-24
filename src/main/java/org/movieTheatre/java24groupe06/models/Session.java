package org.movieTheatre.java24groupe06.models;

import java.io.Serializable;
/**
 * The Session class provides methods for managing sessions.
 */
public class Session implements Serializable {
    private int ID;
    private Movie movie;
    private SeatsRoomLeft seatsRoomLeft;
    private String Time;

    /**
     * Initializes a new instance of the Session class.
     *
     * @param SessionID the session ID.
     * @param movie the movie.
     * @param seatsRoomLeft the seats left in the room.
     * @param hours the hours.
     */
    public Session(int SessionID, Movie movie, SeatsRoomLeft seatsRoomLeft, String hours){
        this.Time =hours;
        this.seatsRoomLeft = seatsRoomLeft;
        this.movie=movie;
        this.ID = SessionID;
    }

    public int getSessionID() {
        return ID;
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
    public boolean equals(Session session) {
        return this.ID == session.ID;
    }
}
