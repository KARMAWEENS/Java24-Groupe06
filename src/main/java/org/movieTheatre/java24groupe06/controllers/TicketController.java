package org.movieTheatre.java24groupe06.controllers;

import javafx.fxml.Initializable;
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


    public void setNbSelectedAdultSeats(int nbSelectedAdultSeats) {
        this.nbSelectedAdultSeats = nbSelectedAdultSeats;
    }

    public void setNbChildrenSeats(int nbChildrenSeats) {
        this.nbSelectedChildrenSeats = nbChildrenSeats;
    }

    public void setNbVIPSeats(int nbVIPSeats) {
        this.nbSelectedVIPSeats = nbVIPSeats;
    }

    public void setNbHandicapSeats(int nbHandicapSeats) {
        this.nbSelectedHandicapSeats = nbHandicapSeats;
    }

    public int getNbSelectedAdultSeats() {
        return nbSelectedAdultSeats;
    }

    public int getNbSelectedChildrenSeats() {
        return nbSelectedChildrenSeats;
    }

    public int getNbSelectedVIPSeats() {
        return nbSelectedVIPSeats;
    }

    public int getNbSelectedHandicapSeats() {
        return nbSelectedHandicapSeats;
    }

    private int nbSelectedAdultSeats;

    private int nbSelectedChildrenSeats;
    private int nbSelectedVIPSeats;
    private int nbSelectedHandicapSeats;

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
    /*removeFirstTicketOfType(TicketAdult.class);*/

    @Override
    public void OnButtonPlusAdultClicked() {
        onButtonPlusClicked(TicketAdult.class);
        setNbSelectedAdultSeats(countTicketsOfType(TicketAdult.class));
        ticketViewController.setTicketAdult(nbSelectedAdultSeats);
        ticketViewController.setTotalPrice(calculateTotalPrice());
    }

    @Override
    public void OnButtonMinusAdultClicked() {
        removeFirstTicketOfType(TicketAdult.class);
        setNbSelectedAdultSeats(countTicketsOfType(TicketAdult.class));
        ticketViewController.setTicketAdult(countTicketsOfType(TicketAdult.class));
        ticketViewController.setTotalPrice(calculateTotalPrice());
    }


    @Override
    public void OnButtonMinusChildrenClicked() {
     removeFirstTicketOfType(TicketChildren.class);

        ticketViewController.setTicketChildren(countTicketsOfType(TicketChildren.class));
        ticketViewController.setTotalPrice(calculateTotalPrice());
    }

    @Override
    public void OnButtonPlusChildrenClicked() {
        onButtonPlusClicked(TicketChildren.class);
        ticketViewController.setTicketChildren(countTicketsOfType(TicketChildren.class));
        ticketViewController.setTotalPrice(calculateTotalPrice());
    }

    @Override
    public void OnButtonMinusVIPClicked() {
        removeFirstTicketOfType(TicketVIP.class);
        ticketViewController.setTicketVIP(countTicketsOfType(TicketVIP.class));
        ticketViewController.setTotalPrice(calculateTotalPrice());
    }

    @Override
    public void OnButtonPlusVIPClicked() {
        onButtonPlusClicked(TicketVIP.class);
        ticketViewController.setTicketVIP(countTicketsOfType(TicketVIP.class));
        ticketViewController.setTotalPrice(calculateTotalPrice());

    }

    @Override
    public void OnButtonMinusDisabledClicked() {
        removeFirstTicketOfType(TicketHandicap.class);
        ticketViewController.setTicketHandicap(countTicketsOfType(TicketHandicap.class));
        ticketViewController.setTotalPrice(calculateTotalPrice());
    }

    @Override
    public void OnButtonPlusDisabledClicked() {
        onButtonPlusClicked(TicketHandicap.class);
        ticketViewController.setTicketHandicap(countTicketsOfType(TicketHandicap.class));
        ticketViewController.setTotalPrice(calculateTotalPrice());
    }

    public interface Listener {

    }
}
