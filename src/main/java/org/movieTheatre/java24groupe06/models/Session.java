package org.movieTheatre.java24groupe06.models;

public class Session {
    private Movie movie;
    private SeatsRoomLeft seatsRoomLeft;
    private String hour;

    public Movie getMovie() {
        return movie;
    }

    public SeatsRoomLeft getRoom() {
        return seatsRoomLeft;
    }

    public String getHours() {
        return hour;
    }
    public int getRoomID(){
        return seatsRoomLeft.getId();
    }
    public Session(Movie movie, SeatsRoomLeft seatsRoomLeft, String hours){
        this.hour=hours;
        this.seatsRoomLeft = seatsRoomLeft;
        this.movie=movie;
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
