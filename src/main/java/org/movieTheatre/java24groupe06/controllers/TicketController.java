package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.models.Promotion.FamilyPackPromotion;
import org.movieTheatre.java24groupe06.models.Promotion.GroupPackPromotion;
import org.movieTheatre.java24groupe06.models.Promotion.HandicapPackPromotion;
import org.movieTheatre.java24groupe06.models.Promotion.SchoolPackPromotion;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.tickets.*;
import org.movieTheatre.java24groupe06.views.TicketViewController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketController implements TicketViewController.Listener {
    List<Ticket> ticketsList =new ArrayList<>();
    TicketViewController ticketViewController;
    public Listener listener;
    public Session session;
    private int nbSelectedAdultSeats;
    private int nbSelectedChildrenSeats;
    private int nbSelectedVIPSeats;
    private int nbSelectedHandicapSeats;

    public TicketController(Listener listener, Session session) {
        this.listener = listener;
        this.session = session;
    }

    public void setNbSelectedSelectedAdultSeats(int nbSelectedAdultSeats) {
        this.nbSelectedAdultSeats = nbSelectedAdultSeats;
    }
    public void setNbSelectedChildrenSeats(int nbChildrenSeats) {
        this.nbSelectedChildrenSeats = nbChildrenSeats;
    }
    public void setNbSelectedVIPSeats(int nbVIPSeats) {
        this.nbSelectedVIPSeats = nbVIPSeats;
    }
    public void setNbSelectedHandicapSeats(int nbHandicapSeats) {
        this.nbSelectedHandicapSeats = nbHandicapSeats;
    }

    public void initializeTicket() throws CantLoadFXMLException {
       ticketViewController = new TicketViewController(this);
        try {
            ticketViewController.openOnNewStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private double calculateTotalPrice(){
        double price= 0;
        for(Ticket ticket : ticketsList){
            price+=ticket.getPrice();
        }
        return price;
}

    public <T extends Ticket> int countTicketsOfType(Class<T> ticketClass) {
        return (int) ticketsList.stream().filter(ticketClass::isInstance).count();
    }
    public void AddTicketOfType(Class<? extends Ticket> ticketClass) {
        try {
            Ticket ticket = ticketClass.getConstructor(Session.class).newInstance(session);
            ticketsList.add(ticket);
            System.out.println(ticketsList.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public <T extends Ticket> void removeTicketOfType(Class<T> ticketClass) {
        ticketsList.stream()
                .filter(ticketClass::isInstance)
                .findFirst()
                .ifPresent(ticketsList::remove);
    }

    private <T extends Ticket> void updateTicketCountAndUI(Class<T> ticketClass, boolean isIncrement) {
        int count = updateCount(ticketClass, isIncrement);
        UpdateUI(ticketClass, count);
    }
    private <T extends Ticket> int updateCount(Class<T> ticketClass, boolean isIncrement) {
        if (isIncrement) AddTicketOfType(ticketClass);
        else removeTicketOfType(ticketClass);
        int count = countTicketsOfType(ticketClass);
        return count;
    }
    private <T extends Ticket> void UpdateUI(Class<T> ticketClass, int count) {
        String className = ticketClass.getSimpleName();
        switch (className) {
            case "TicketAdult":
                setNbSelectedSelectedAdultSeats(count);
                ticketViewController.updateTicketAdultLabel(count);
                break;
            case "TicketChildren":
                setNbSelectedChildrenSeats(count);
                ticketViewController.updateTicketChildrenLabel(count);
                break;
            case "TicketVIP":
                setNbSelectedVIPSeats(count);
                ticketViewController.updateTicketVIPLabel(count);
                break;
            case "TicketHandicap":
                setNbSelectedHandicapSeats(count);
                ticketViewController.updateTicketHandicapLabel(count);
                break;
        }

        double price = calculateTotalPrice();
        System.out.println("Avant réduction: "+price);

        FamilyPackPromotion familyPackPromotion = new FamilyPackPromotion();
        price -= familyPackPromotion.calculateDiscount(ticketsList);

        SchoolPackPromotion schoolPackPromotion = new SchoolPackPromotion();
        price -= schoolPackPromotion.calculateDiscount(ticketsList);

        GroupPackPromotion groupPackPromotion = new GroupPackPromotion();
        price -= groupPackPromotion.calculateDiscount(ticketsList);

        HandicapPackPromotion handicapPackPromotion = new HandicapPackPromotion();
        price -= handicapPackPromotion.calculateDiscount(ticketsList);

        ticketViewController.updateTotalPriceLabel(price);
        System.out.println(("Après réduction: " + price));
    }

    @Override
    public void OnButtonPlusAdultClicked() {updateTicketCountAndUI(TicketAdult.class,true);}
    @Override
    public void OnButtonMinusAdultClicked() {updateTicketCountAndUI(TicketAdult.class,false);}

    @Override
    public void OnButtonPlusChildrenClicked() {updateTicketCountAndUI(TicketChildren.class,true);}
    @Override
    public void OnButtonMinusChildrenClicked() {updateTicketCountAndUI(TicketChildren.class,false);}
    @Override
    public void OnButtonPlusVIPClicked() {updateTicketCountAndUI(TicketVIP.class, true);}
    @Override
    public void OnButtonMinusVIPClicked() {updateTicketCountAndUI(TicketVIP.class, false);}
    @Override
    public void OnButtonPlusDisabledClicked() {updateTicketCountAndUI(TicketHandicap.class, true);}
    @Override
    public void OnButtonMinusDisabledClicked() {updateTicketCountAndUI(TicketHandicap.class, false);}

    public interface Listener {

    }
}
