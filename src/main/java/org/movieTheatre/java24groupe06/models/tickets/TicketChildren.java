package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

public class TicketChildren extends Ticket {
    private double price;
    private Session session;

    public TicketChildren(Session session) {
        super(session);
    }

    @Override
    public double getPrice() {
        return 10;
    }
}



