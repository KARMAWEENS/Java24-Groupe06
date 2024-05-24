package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

/**
 * The TicketVIP class provides methods for managing VIP tickets.
 */
public class TicketVIP extends Ticket{
    public TicketVIP(Session session) {
        super(session);
    }

    /**
     * Retrieves the price of the ticket.
     *
     * @return the price of the ticket.
     */
    @Override
    public double getPrice() {
        return 30;
    }
}
