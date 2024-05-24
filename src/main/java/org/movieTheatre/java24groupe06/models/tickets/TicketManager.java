package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * The TicketManager class provides methods for managing tickets.
 */
public class TicketManager {
    private List<Ticket> ticketsList = new ArrayList<>();
    private Session session;

    /**
     * Initializes a new instance of the TicketManager class.
     *
     * @param session the session.
     */
    public TicketManager(Session session) {
        this.session = session;
    }

    public  <T extends Ticket> int updateCount(Class<T> ticketClass, boolean isIncrement) {
        if (isIncrement) addTicketOfType(ticketClass);
        else removeTicketOfType(ticketClass);
        int count = countTicketsOfType(ticketClass);
        return count;
    }
    /**
     * Adds a ticket of the specified type to the list of tickets.
     *
     * @param ticketClass the ticket type.
     */
    public void addTicketOfType(Class<? extends Ticket> ticketClass) {
        try {
            Ticket ticket = ticketClass.getConstructor(Session.class).newInstance(session);
            ticketsList.add(ticket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Removes a ticket of the specified type from the list of tickets.
     *
     * @param ticketClass the ticket type.
     */
    public <T extends Ticket> void removeTicketOfType(Class<T> ticketClass) {
        ticketsList.stream()
                .filter(ticketClass::isInstance)
                .findFirst()
                .ifPresent(ticketsList::remove);
    }
    /**
     * Retrieves the number of tickets of the specified type.
     *
     * @param ticketClass the ticket type.
     * @return the number of tickets of the specified type.
     */
    public <T extends Ticket> int countTicketsOfType(Class<T> ticketClass) {
        return (int) ticketsList.stream().filter(ticketClass::isInstance).count();
    }
    /**
     * Retrieves the list of tickets.
     *
     * @return the list of tickets.
     */
    public List<Ticket> getTicketsList() {
        return ticketsList;
    }
}
