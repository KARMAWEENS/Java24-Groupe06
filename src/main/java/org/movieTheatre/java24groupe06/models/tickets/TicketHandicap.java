package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

/**
 * The TicketHandicap class provides methods for managing handicap tickets.
 */
public class TicketHandicap extends Ticket{
    public TicketHandicap(Session session) {
        super(session);
    }

    /**
     * Retrieves the price of the ticket.
     *
     * @return the price of the ticket.
     */
    @Override
    public double getPrice() {
        return 40;
    }
}
