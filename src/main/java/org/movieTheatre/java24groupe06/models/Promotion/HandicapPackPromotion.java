package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.Ticket;

import java.util.List;

public class HandicapPackPromotion implements IPromotion{
    final double reductionHandicapPack =2.50;
    final int minAdultToGetPromotion =1;
    final int minHandicapToGetPromotion=1;

    @Override
    public double calculateDiscount(List<Ticket> tickets) {

        int nbAdultDiscountGroups =(getnbAdultTickets(tickets)/minAdultToGetPromotion);
        int nbHandicapDiscoutGroups =(getnbHandicapTickets(tickets)/minHandicapToGetPromotion);

        if (nbAdultDiscountGroups>=1 && nbHandicapDiscoutGroups >= 1) {
            return Math.min(nbAdultDiscountGroups,nbHandicapDiscoutGroups)* reductionHandicapPack;  // 5 euros de réduction par pack famille
        }
        return 0;
    }
}
