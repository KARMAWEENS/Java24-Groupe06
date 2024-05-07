package org.movieTheatre.java24groupe06.models;

public class Session {
    private Movie movie;
    private SeatsRoomLeft seatsRoomLeft;
    private String Time;


    public Session(Movie movie, SeatsRoomLeft seatsRoomLeft, String hours){
        this.Time =hours;
        this.seatsRoomLeft = seatsRoomLeft;
        this.movie=movie;
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
    public int getRoomID(){
        return seatsRoomLeft.getId();
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

}
