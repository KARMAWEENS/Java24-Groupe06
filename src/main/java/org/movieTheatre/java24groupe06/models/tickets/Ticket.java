package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Room;
import org.movieTheatre.java24groupe06.models.Session;

public  abstract class Ticket {
    private double price;
    private Session session;
    public Ticket(Session session) {
        this.session=session;
    }

    public abstract double getPrice();
}
