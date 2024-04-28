package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.*;

import java.util.List;
import java.util.stream.Collectors;

public class GroupPackPromotion implements IPromotion {
    int nbPeopleNeededToGetPromotion = 5;

    @Override
    public double calculateDiscount(List<Ticket> tickets) {
        // Filter out tickets that have already been used for any promotion
        List<Ticket> eligibleTickets = getEligibleTickets(tickets);
        double discount = 0;
        for (Class<? extends Ticket> ticketClass : List.of(TicketVIP.class, TicketHandicap.class, TicketAdult.class, TicketChildren.class)) {
            long count = eligibleTickets.stream().filter(ticketClass::isInstance).count();
            int groupPack = (int) (count/nbPeopleNeededToGetPromotion);
           for(int i=0; i<groupPack;i++) {
               discount += eligibleTickets.stream()
                       .filter(ticketClass::isInstance)
                       .findFirst()
                       .get()
                       .getPrice(); // Return the price a single ticket of the class
               markTicketsAsUsed(eligibleTickets, ticketClass, nbPeopleNeededToGetPromotion);
               System.out.println("Reduction de groupe pour " + ticketClass.getSimpleName()+" :" +groupPack);
           }

        }

        return discount;
    }

    private void markTicketsAsUsed(List<Ticket> tickets, Class<? extends Ticket> ticketType, int count) {
        tickets.stream()
                .filter(ticketType::isInstance)
                .filter(ticket -> !ticket.isPromotionApplied())
                .limit(count)
                .forEach(ticket -> ticket.setPromotionApplied(true));
    }
}
