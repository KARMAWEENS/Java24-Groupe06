package org.movieTheatre.java24groupe06.models.Promotion;

import org.movieTheatre.java24groupe06.models.tickets.*;

import java.util.List;
import java.util.stream.Collectors;

public interface IPromotion {
    double calculateDiscount(List<Ticket> tickets);

    default int getnbAdultTickets(List<Ticket> tickets){
         return  (int) tickets.stream().filter(t -> t instanceof TicketAdult).count();
    }
    default int getnbChildrentTickets(List<Ticket> tickets){
        return  (int) tickets.stream().filter(t -> t instanceof TicketChildren).count();
    }
    default int getnbHandicapTickets(List<Ticket> tickets){
        return  (int) tickets.stream().filter(t -> t instanceof TicketHandicap).count();
    }
    default int getnbVIPTickets(List<Ticket> tickets){
        return  (int) tickets.stream().filter(t -> t instanceof TicketVIP).count();
    }
   default List<Ticket> getEligibleTickets(List<Ticket> ticketsList){
       return ticketsList.stream()
               .filter(ticket -> !ticket.isPromotionApplied())
               .collect(Collectors.toList());
   }


}
