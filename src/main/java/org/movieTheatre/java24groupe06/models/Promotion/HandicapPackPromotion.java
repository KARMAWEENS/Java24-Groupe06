package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.Ticket;
import org.movieTheatre.java24groupe06.models.tickets.TicketAdult;
import org.movieTheatre.java24groupe06.models.tickets.TicketHandicap;

import java.util.List;
import java.util.stream.Collectors;

public class HandicapPackPromotion implements IPromotion{
    final double reductionHandicapPack = 2.50;
    final int minAdultToGetPromotion = 1;
    final int minHandicapToGetPromotion = 1;

    @Override
    public double calculateDiscount(List<Ticket> tickets) {
        // Filter out tickets that have already been used for any promotion
        List<Ticket> eligibleTickets = getEligibleTickets(tickets);

        int nbAdultTickets = getnbAdultTickets(eligibleTickets);
        int nbHandicapTickets = (int) eligibleTickets.stream().filter(ticket -> ticket instanceof TicketHandicap).count();

        int nbAdultDiscountGroups = nbAdultTickets / minAdultToGetPromotion;
        int nbHandicapDiscountGroups = nbHandicapTickets / minHandicapToGetPromotion;

        if (nbAdultDiscountGroups >= 1 && nbHandicapDiscountGroups >= 1) {
            int numberOfHandicapPacks = Math.min(nbAdultDiscountGroups, nbHandicapDiscountGroups);
            // Mark tickets as used for this promotion
            markTicketsAsUsed(eligibleTickets, TicketAdult.class, numberOfHandicapPacks * minAdultToGetPromotion);
            markTicketsAsUsed(eligibleTickets, TicketHandicap.class, numberOfHandicapPacks * minHandicapToGetPromotion);
            System.out.println("HandicapPack :"+numberOfHandicapPacks);
            return numberOfHandicapPacks * reductionHandicapPack;
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
