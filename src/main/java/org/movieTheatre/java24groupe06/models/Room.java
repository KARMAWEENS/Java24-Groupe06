package org.movieTheatre.java24groupe06.models;

public class Room {
    private int nbRegularSeats;
    private int nbHandicapsSeats;
    private int nbVIPSeats;
    private int id;
    public Room(int nbRegularSeats, int nbHandicapsSeats, int nbVIPSeats,int id) {
        this.nbRegularSeats = nbRegularSeats;
        this.nbHandicapsSeats = nbHandicapsSeats;
        this.nbVIPSeats =nbVIPSeats ;
        this.id =id;
    }
}
