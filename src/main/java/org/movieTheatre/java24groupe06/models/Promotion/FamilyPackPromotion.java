package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.Ticket;
import org.movieTheatre.java24groupe06.models.tickets.TicketAdult;
import org.movieTheatre.java24groupe06.models.tickets.TicketChildren;

import java.util.List;
import java.util.stream.Collectors;
/**
 * The FamilyPackPromotion class provides methods for calculating the discount for a family pack promotion.
 */
public class FamilyPackPromotion implements IPromotion {
    final double reductionFamilyPack =5;
    final int minAdultToGetPromotion =2;
    final int minChildrenToGetPromotion=2;

    /**
     * calculate the discount for a family pack promotion.
     *
     */
        @Override
        public double calculateDiscount(List<Ticket> tickets) {


            // Filter out tickets that have already been used for any promotion
            List<Ticket> eligibleTickets = getEligibleTickets(tickets);

            int nbAdultTickets = getnbAdultTickets(eligibleTickets);
            int nbChildrenTickets = getnbChildrentTickets(eligibleTickets);

            int nbAdultDiscountGroups = nbAdultTickets / minAdultToGetPromotion;
            int nbChildrenDiscountGroups = nbChildrenTickets / minChildrenToGetPromotion;

            if (nbAdultDiscountGroups >= 1 && nbChildrenDiscountGroups >= 1) {
                int numberOfFamilyPacks = Math.min(nbAdultDiscountGroups, nbChildrenDiscountGroups);
                // Mark tickets as used for this promotion
                markTicketsAsUsed(eligibleTickets, TicketAdult.class, numberOfFamilyPacks * minAdultToGetPromotion);
                markTicketsAsUsed(eligibleTickets, TicketChildren.class, numberOfFamilyPacks * minChildrenToGetPromotion);
                System.out.println("number of familyPack" + numberOfFamilyPacks);
                return numberOfFamilyPacks * reductionFamilyPack;
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
