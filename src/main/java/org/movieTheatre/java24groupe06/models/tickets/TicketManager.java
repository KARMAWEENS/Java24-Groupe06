package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

import java.util.ArrayList;
import java.util.List;

public class TicketManager {
    private List<Ticket> ticketsList = new ArrayList<>();
    private Session session;

    public TicketManager(Session session) {
        this.session = session;
    }

    public  <T extends Ticket> int updateCount(Class<T> ticketClass, boolean isIncrement) {
        if (isIncrement) addTicketOfType(ticketClass);
        else removeTicketOfType(ticketClass);
        int count = countTicketsOfType(ticketClass);
        return count;
    }
    public void addTicketOfType(Class<? extends Ticket> ticketClass) {
        try {
            Ticket ticket = ticketClass.getConstructor(Session.class).newInstance(session);
            ticketsList.add(ticket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public <T extends Ticket> void removeTicketOfType(Class<T> ticketClass) {
        ticketsList.stream()
                .filter(ticketClass::isInstance)
                .findFirst()
                .ifPresent(ticketsList::remove);
    }
    public <T extends Ticket> int countTicketsOfType(Class<T> ticketClass) {
        return (int) ticketsList.stream().filter(ticketClass::isInstance).count();
    }
    public List<Ticket> getTicketsList() {
        return ticketsList;
    }
}
