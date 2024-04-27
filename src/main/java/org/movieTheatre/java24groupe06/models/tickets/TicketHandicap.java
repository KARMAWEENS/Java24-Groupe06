package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

public class TicketHandicap extends Ticket{
    public TicketHandicap(Session session) {
        super(session);
    }

    @Override
    public double getPrice() {
        return 40;
    }
}
