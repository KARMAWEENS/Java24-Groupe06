package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

/**
 * The TicketChildren class provides methods for managing children tickets.
 */
public class TicketChildren extends Ticket {
    private double price;
    private Session session;

    /**
     * Initializes a new instance of the TicketChildren class.
     *
     * @param session the session.
     */
    public TicketChildren(Session session) {
        super(session);
    }

    /**
     * Retrieves the price of the ticket.
     *
     * @return the price of the ticket.
     */
    @Override
    public double getPrice() {
        return 10;
    }
}



