package org.movieTheatre.java24groupe06.controllers;

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


    public TicketController(Listener listener, Session session) {
        this.listener = listener;
        this.session = session;
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

    public void onButtonPlusClicked(Class<? extends Ticket> ticketClass) {
        try {
            Ticket ticket = ticketClass.getConstructor(Session.class).newInstance(session);
            ticketsList.add(ticket);
            System.out.println(ticketsList.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Ticket> int countTicketsOfType(Class<T> ticketClass) {
        return (int) ticketsList.stream().filter(ticketClass::isInstance).count();
    }

    public <T extends Ticket> void removeFirstTicketOfType(Class<T> ticketClass) {
        ticketsList.stream()
                .filter(ticketClass::isInstance)
                .findFirst()
                .ifPresent(ticketsList::remove);
    }

    @Override
    public void OnButtonPlusAdultClicked() {
        onButtonPlusClicked(TicketAdult.class);
        setNbSelectedSelectedAdultSeats(countTicketsOfType(TicketAdult.class));
        ticketViewController.setTicketAdultLabel(nbSelectedAdultSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());
    }

    @Override
    public void OnButtonMinusAdultClicked() {
        removeFirstTicketOfType(TicketAdult.class);
        setNbSelectedSelectedAdultSeats(countTicketsOfType(TicketAdult.class));
        ticketViewController.setTicketAdultLabel(nbSelectedAdultSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());
    }

    @Override
    public void OnButtonMinusChildrenClicked() {
     removeFirstTicketOfType(TicketChildren.class);
        setNbSelectedChildrenSeats(countTicketsOfType(TicketChildren.class));
        ticketViewController.setTicketChildrenLabel(nbSelectedChildrenSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());
    }

    @Override
    public void OnButtonPlusChildrenClicked() {
        onButtonPlusClicked(TicketChildren.class);
        setNbSelectedChildrenSeats(countTicketsOfType(TicketChildren.class));
        ticketViewController.setTicketChildrenLabel(nbSelectedChildrenSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());
    }

    @Override
    public void OnButtonMinusVIPClicked() {
        removeFirstTicketOfType(TicketVIP.class);
        setNbSelectedVIPSeats(countTicketsOfType(TicketVIP.class));
        ticketViewController.setTicketVIPLabel(nbSelectedVIPSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());
    }

    @Override
    public void OnButtonPlusVIPClicked() {
        onButtonPlusClicked(TicketVIP.class);
        setNbSelectedVIPSeats(countTicketsOfType(TicketVIP.class));
        ticketViewController.setTicketVIPLabel(nbSelectedVIPSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());

    }

    @Override
    public void OnButtonMinusDisabledClicked() {
        removeFirstTicketOfType(TicketHandicap.class);
        setNbSelectedHandicapSeats(countTicketsOfType(TicketHandicap.class));
        ticketViewController.setTicketHandicapLabel(nbSelectedHandicapSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());
    }

    @Override
    public void OnButtonPlusDisabledClicked() {
        onButtonPlusClicked(TicketHandicap.class);
        setNbSelectedHandicapSeats(countTicketsOfType(TicketHandicap.class));
        ticketViewController.setTicketHandicapLabel(nbSelectedHandicapSeats);
        ticketViewController.setTotalPriceLabel(calculateTotalPrice());
    }

    public interface Listener {

    }
}
