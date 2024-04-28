package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.Ticket;
import org.movieTheatre.java24groupe06.models.tickets.TicketAdult;
import org.movieTheatre.java24groupe06.models.tickets.TicketChildren;

import java.util.List;

public class SchoolPackPromotion implements IPromotion{
    final int minChildrenToGetPromotion=5;
    final double reductionSchoolPack =15;
    @Override
    public double calculateDiscount(List<Ticket> tickets) {
        long nbChildrenDiscoutGroups = getnbChildrentTickets(tickets)/minChildrenToGetPromotion;
        if (nbChildrenDiscoutGroups>=1) {
            return nbChildrenDiscoutGroups*reductionSchoolPack;  // 15 euros de réduction pour les écoles
        }
        return 0;
    }
}
