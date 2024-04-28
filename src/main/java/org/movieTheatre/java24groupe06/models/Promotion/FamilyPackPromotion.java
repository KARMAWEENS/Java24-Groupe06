package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.Ticket;
import org.movieTheatre.java24groupe06.models.tickets.TicketAdult;
import org.movieTheatre.java24groupe06.models.tickets.TicketChildren;

import java.util.List;

public class FamilyPackPromotion implements IPromotion {
    final double reductionFamilyPack =5;
    final int minAdultToGetPromotion =2;
    final int minChildrenToGetPromotion=2;

        @Override
        public double calculateDiscount(List<Ticket> tickets) {

          int nbAdultDiscountGroups =(getnbAdultTickets(tickets)/minAdultToGetPromotion);
          int nbChildrenDiscoutGroups =(getnbChildrentTickets(tickets)/minChildrenToGetPromotion);
            
            if (nbAdultDiscountGroups>=1 && nbChildrenDiscoutGroups >= 1) {
                return Math.min(nbAdultDiscountGroups,nbChildrenDiscoutGroups)*reductionFamilyPack;  // 5 euros de réduction par pack famille
            }
            return 0;
        }
    }
