package org.movieTheatre.java24groupe06.models;

import java.io.Serializable;
/**
 * The SeatsRoomLeft class provides methods for managing the number of seats left in a room.
 */
public class SeatsRoomLeft implements Serializable {

    public int getNbRegularSeats() {
        return nbRegularSeats;
    }

    public int getNbHandicapsSeats() {
        return nbHandicapsSeats;
    }

    public int getNbVIPSeats() {
        return nbVIPSeats;
    }
    private int nbRegularSeats;
    private int nbHandicapsSeats;
    private int nbVIPSeats;
    public SeatsRoomLeft(int nbRegularSeats, int nbHandicapsSeats, int nbVIPSeats) {
        this.nbRegularSeats = nbRegularSeats;
        this.nbHandicapsSeats = nbHandicapsSeats;
        this.nbVIPSeats =nbVIPSeats ;
    }
}
