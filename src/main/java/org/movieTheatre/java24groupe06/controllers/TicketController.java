package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Promotion.*;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.tickets.*;
import org.movieTheatre.java24groupe06.server.ObjectSocket;
import org.movieTheatre.java24groupe06.views.TicketViewController;

import java.io.IOException;
import java.net.Socket;


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
        try {
            this.ticketViewController = new TicketViewController(this);
            this.ticketManager = new TicketManager(session);
            this.promotionManager = new PromotionManager(ticketManager.getTicketsList());
            ticketViewController.openOnNewStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private <T extends Ticket> void updateTicketCountAndUI(Class<T> ticketClass, boolean isIncrement) {
        int count = ticketManager.updateCount(ticketClass, isIncrement);
        double price = promotionManager.calculateBestPrice();
        updateUI(ticketClass,count, price);
    }
    private <T extends Ticket> void updateUI(Class<T> ticketClass, int count, double price) {
        String className = ticketClass.getSimpleName();
        switch (className) {
            case "TicketAdult":
                setNbSelectedSelectedAdultSeats(count);
                ticketViewController.updateTicketAdultLabel(count);
                updateRegularSeatsLabel();
                break;
            case "TicketChildren":
                setNbSelectedChildrenSeats(count);
                ticketViewController.updateTicketChildrenLabel(count);
                updateRegularSeatsLabel();
                break;
            case "TicketVIP":
                setNbSelectedVIPSeats(count);
                ticketViewController.updateTicketVIPLabel(count);
                ticketViewController.updateAvailableVIPSeatsLabel(session.getNbVIPSeats()-count);
                break;
            case "TicketHandicap":
                setNbSelectedHandicapSeats(count);
                ticketViewController.updateTicketHandicapLabel(count);
                ticketViewController.updateAvailableHandicapSeatsLabel(session.getNbHandicapsSeats()-count);
                break;
        }
        ticketViewController.updateTotalPriceLabel(price);
    }

    private void updateRegularSeatsLabel() {
        ticketViewController.updateAvailableChildrenSeatsLabel(calculatedRegularSeats());
        ticketViewController.updateAvailableAdultSeatsLabel(calculatedRegularSeats());
    }
    private int calculatedRegularSeats() {
        return session.getNbRegularSeats() - ticketManager.countTicketsOfType(TicketChildren.class) - ticketManager.countTicketsOfType(TicketAdult.class);
    }

    @Override
    public void onButtonBuyClicked() {
        try {
           Socket socket = new Socket("localhost", 8082);
            ObjectSocket objectSocket = new ObjectSocket(socket);
            objectSocket.write(new DTOBuy(session,nbSelectedAdultSeats+nbSelectedChildrenSeats,nbSelectedVIPSeats,nbSelectedHandicapSeats));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
