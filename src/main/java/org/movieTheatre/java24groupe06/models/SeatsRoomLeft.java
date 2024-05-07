package org.movieTheatre.java24groupe06.models;

public class SeatsRoomLeft {

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
    public SeatsRoomLeft(int nbRegularSeats, int nbHandicapsSeats, int nbVIPSeats, int id) {
        this.nbRegularSeats = nbRegularSeats;
        this.nbHandicapsSeats = nbHandicapsSeats;
        this.nbVIPSeats =nbVIPSeats ;

    }
}
