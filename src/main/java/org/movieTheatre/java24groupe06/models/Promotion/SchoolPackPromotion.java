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
     * Retrieves the number of  tickets.
     *
     * @param tickets the list of tickets.
     * @return the number of adult tickets.
     */
    @Override
    public double calculateDiscount(List<Ticket> tickets) {
        // Filter out tickets that have already been used for any promotion
      //  List<Ticket> eligibleTickets = getEligibleTickets(tickets);

        List<Ticket> eligibleTickets = tickets.stream()
                .filter(ticket -> !ticket.isPromotionApplied())
                .filter(ticket -> ticket instanceof TicketChildren)
                .collect(Collectors.toList());

        int nbChildrenTickets = eligibleTickets.size();
        int nbChildrenDiscountGroups = nbChildrenTickets / minChildrenToGetPromotion;

        if (nbChildrenDiscountGroups >= 1) {
            // Calculate total discount
            double totalDiscount = nbChildrenDiscountGroups * reductionSchoolPack;

            // Mark the required number of tickets as used
            markTicketsAsUsed(eligibleTickets, TicketChildren.class, nbChildrenDiscountGroups * minChildrenToGetPromotion);
            System.out.println("SchoolPackPromotion: "+ nbChildrenDiscountGroups);
            return totalDiscount;
        }
        return 0;
    }

    private void markTicketsAsUsed(List<Ticket> tickets, Class<? extends Ticket> ticketType, int count) {
        tickets.stream()
                .filter(ticketType::isInstance)
                .filter(ticket -> !ticket.isPromotionApplied())
                .limit(count)
                .forEach(ticket -> ticket.setPromotionApplied(true));
    }
}
