
package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.*;

import java.util.ArrayList;
import java.util.List;
public class GroupPackPromotion implements IPromotion{
    int nbPeopleNeededToGetPromotion = 5;

    @Override
    public double calculateDiscount(List<Ticket> tickets) {
        for (Class<? extends Ticket> ticketClass : List.of(TicketVIP.class, TicketHandicap.class, TicketAdult.class, TicketChildren.class)) {
            long count = tickets.stream().filter(ticketClass::isInstance).count();
            if (count >= nbPeopleNeededToGetPromotion) {
                return tickets.stream()
                        .filter(ticketClass::isInstance)
                        .findFirst()
                        .get()
                        .getPrice(); // Return the price a single ticket of the class
            }
        }
        return 0;

    }
}