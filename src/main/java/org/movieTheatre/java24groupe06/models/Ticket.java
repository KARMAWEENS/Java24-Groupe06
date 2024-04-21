package org.movieTheatre.java24groupe06.models;

public  abstract class Ticket {
    private double price;
    private Movie movie;
    private Room room;
    public Ticket(Movie movie,double price,Room room) {
        this.movie=movie;
        this.price=price;
        this.room=room;
    }

    public double getPrice(){
        return price;
    }
}
