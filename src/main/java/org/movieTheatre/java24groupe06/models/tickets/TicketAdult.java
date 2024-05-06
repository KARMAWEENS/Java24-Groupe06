package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

public class TicketAdult extends Ticket {
    public TicketAdult(Session session) {
        super(session);
    }

    @Override
    public double getPrice() {
        return 20;
    }
}
