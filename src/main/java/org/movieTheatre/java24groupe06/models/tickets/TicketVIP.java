package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

public class TicketVIP extends Ticket{
    public TicketVIP(Session session) {
        super(session);
    }

    @Override
    public double getPrice() {
        return 30;
    }
}
