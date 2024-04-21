package org.movieTheatre.java24groupe06.models;

public class Room {
    private int place;
    private int placeHandi;
    private int placeVIP;
    private int id;
    private Movie movie;

    public Room(int place, int placeHandi, int placeVip, Movie movie) {
        this.place = place;
        this.placeHandi = placeHandi;
        this.placeVIP = placeVip;
        this.movie = movie;
    }
}
