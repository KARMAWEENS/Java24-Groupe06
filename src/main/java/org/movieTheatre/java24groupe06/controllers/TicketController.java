package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.models.Promotion.*;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.tickets.*;
import org.movieTheatre.java24groupe06.views.TicketViewController;

import java.io.IOException;


public class TicketController implements TicketViewController.Listener {
    TicketViewController ticketViewController;
    PromotionManager promotionManager;
    TicketManager ticketManager;
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
       ticketManager = new TicketManager(session);
       promotionManager = new PromotionManager(ticketManager.getTicketsList());
        try {
            ticketViewController.openOnNewStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private <T extends Ticket> void updateTicketCountAndUI(Class<T> ticketClass, boolean isIncrement) {
        int count = ticketManager.updateCount(ticketClass, isIncrement);
        double price = promotionManager.findBestPrice();
        updateUI(ticketClass,count, price);
    }

    private <T extends Ticket> void updateUI(Class<T> ticketClass, int count, double price) {
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
        ticketViewController.updateTotalPriceLabel(price);
    }

    @Override
    public void onButtonPlusAdultClicked() {updateTicketCountAndUI(TicketAdult.class,true);}
    @Override
    public void onButtonMinusAdultClicked() {updateTicketCountAndUI(TicketAdult.class,false);}
    @Override
    public void onButtonPlusChildrenClicked() {updateTicketCountAndUI(TicketChildren.class,true);}
    @Override
    public void onButtonMinusChildrenClicked() {updateTicketCountAndUI(TicketChildren.class,false);}
    @Override
    public void onButtonPlusVIPClicked() {updateTicketCountAndUI(TicketVIP.class, true);}
    @Override
    public void onButtonMinusVIPClicked() {updateTicketCountAndUI(TicketVIP.class, false);}
    @Override
    public void onButtonPlusDisabledClicked() {updateTicketCountAndUI(TicketHandicap.class, true);}
    @Override
    public void onButtonMinusDisabledClicked() {updateTicketCountAndUI(TicketHandicap.class, false);}

    public interface Listener {

    }
}
