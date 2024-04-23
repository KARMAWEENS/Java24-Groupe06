package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Movie;
import org.movieTheatre.java24groupe06.models.Room;
import org.movieTheatre.java24groupe06.models.tickets.Ticket;

public class TicketAdult extends Ticket {
    public TicketAdult(Movie movie, double price, Room room) {
        super(movie, price,room);
    }

}
