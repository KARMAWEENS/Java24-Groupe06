package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.Ticket;
import org.movieTheatre.java24groupe06.models.tickets.TicketChildren;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The SchoolPackPromotion class provides methods for calculating the discount for a school pack promotion.
 */
public class SchoolPackPromotion implements IPromotion {
    final int minChildrenToGetPromotion = 15;
    final double reductionSchoolPack = 30;

    /**
     * calculate the discount for a school pack promotion.
     *
     */
    @Override
    public double calculateDiscount(List<Ticket> tickets) {


        List<Ticket> eligibleTickets = tickets.stream()
                .filter(ticket -> !ticket.isPromotionApplied())
                .filter(ticket -> ticket instanceof TicketChildren)
                .collect(Collectors.toList());

        int nbChildrenTickets = eligibleTickets.size();
        int nbChildrenDiscountGroups = nbChildrenTickets / minChildrenToGetPromotion;

        if (nbChildrenDiscountGroups >= 1) {

            double totalDiscount = nbChildrenDiscountGroups * reductionSchoolPack;


            markTicketsAsUsed(eligibleTickets, TicketChildren.class, nbChildrenDiscountGroups * minChildrenToGetPromotion);
            System.out.println("SchoolPackPromotion: "+ nbChildrenDiscountGroups);
            return totalDiscount;
        }
        return 0;
    }
    /**
     * mark tickets as used for promotion
     *
     * @param tickets the list of tickets.
     * @return the number of adult tickets.
     */
    private void markTicketsAsUsed(List<Ticket> tickets, Class<? extends Ticket> ticketType, int count) {
        tickets.stream()
                .filter(ticketType::isInstance)
                .filter(ticket -> !ticket.isPromotionApplied())
                .limit(count)
                .forEach(ticket -> ticket.setPromotionApplied(true));
    }
}
