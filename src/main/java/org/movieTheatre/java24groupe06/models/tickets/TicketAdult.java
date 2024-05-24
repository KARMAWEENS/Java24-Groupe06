package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

/**
 * The TicketAdult class provides methods for managing adult tickets.
 */
public class TicketAdult extends Ticket {
    public TicketAdult(Session session) {
        super(session);
    }

    /**
     * Retrieves the price of the ticket.
     *
     * @return the price of the ticket.
     */
    @Override
    public double getPrice() {
        return 20;
    }
}
