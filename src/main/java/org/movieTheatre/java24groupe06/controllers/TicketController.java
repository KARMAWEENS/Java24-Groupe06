package org.movieTheatre.java24groupe06.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.Promotion.*;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.tickets.*;
import org.movieTheatre.java24groupe06.Network.Event.UpdateSessionEvent;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;
import org.movieTheatre.java24groupe06.views.TicketViewController;

import java.io.IOException;
import java.net.Socket;


public class TicketController implements TicketViewController.Listener, ReadTicketThread.Listener{
    TicketViewController ticketViewController;
    PromotionManager promotionManager;
    ReadTicketThread readTicketThread;
    TicketManager ticketManager;
    public Listener listener;
    ObjectSocket objectSocket;
    private Session session;
    private int nbSelectedAdultSeats;
    private int nbSelectedChildrenSeats;
    private int nbSelectedVIPSeats;
    private int nbSelectedHandicapSeats;

    public TicketController(Listener listener, Session session,ObjectSocket objectSocket) {
        this.listener = listener;
        this.session = session;
this.objectSocket = objectSocket;
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

    public Session getSession(){
        return session;
    }
    public void initializeTicket() throws CantLoadFXMLException {
        try {
            this.ticketViewController = new TicketViewController(this);
            this.ticketManager = new TicketManager(session);
            this.promotionManager = new PromotionManager(ticketManager.getTicketsList());
            ticketViewController.openOnNewStage();
            ticketsBoughtUpdateUI();
            Stage stage = ticketViewController.getStage();
            stage.setOnCloseRequest(event -> handleWindowCloseRequest(event));
            initializeSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void initializeSocket(){
        try {
            Socket socket = new Socket("localhost", 8000);
            ObjectSocket objectSocket = new ObjectSocket(socket);
            readTicketThread = new ReadTicketThread(session,this,objectSocket);
            readTicketThread.start();
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


    public void ticketsBoughtUpdateUI(){
        System.out.println("j utilise ticketsBoughtUpdateUI");
        ticketViewController.updateAvailableAdultSeatsLabel(session.getNbRegularSeats()-nbSelectedAdultSeats-nbSelectedChildrenSeats);
        ticketViewController.updateAvailableChildrenSeatsLabel(session.getNbRegularSeats()-nbSelectedAdultSeats-nbSelectedChildrenSeats);
        ticketViewController.updateAvailableVIPSeatsLabel(session.getNbVIPSeats()-nbSelectedVIPSeats);
        ticketViewController.updateAvailableHandicapSeatsLabel(session.getNbHandicapsSeats()-nbSelectedHandicapSeats);
    }



    private void updateRegularSeatsLabel() {
        ticketViewController.updateAvailableChildrenSeatsLabel(calculatedRegularSeats());
        ticketViewController.updateAvailableAdultSeatsLabel(calculatedRegularSeats());
    }
    private int calculatedRegularSeats() {
        return session.getNbRegularSeats() - ticketManager.countTicketsOfType(TicketChildren.class) - ticketManager.countTicketsOfType(TicketAdult.class);
    }
    private void handleWindowCloseRequest(WindowEvent event) {
        event.consume();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez-vous vraiment fermer la fenêtre?");
        alert.setContentText("Aucun ticket n'a été acheté");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.YES) {
                listener.closeTicketView();
            }
        });
    }
    @Override
    public void onButtonBuyClicked() {
        try {
            // Je me connect a UpdateSessionSeatsHandlerThread
            // On envoie a UpdateSessionSeatsHandlerThread les places achetées
            UpdateSessionEvent updateSessionEvent = new UpdateSessionEvent(new DTOBuy(session,nbSelectedAdultSeats+nbSelectedChildrenSeats,nbSelectedVIPSeats,nbSelectedHandicapSeats));
            objectSocket.write(updateSessionEvent);
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
    @Override
    public void onReturnButtonClicked(){
        listener.closeTicketView();
    }

    public void close() {
        ticketViewController.close();
        readTicketThread.closeSocket();
    }

    @Override
    public void updateUITicketBought(Session session) {
        listener.updateUITicketBought(session);
    }

    public interface Listener {
        void closeTicketView();

        void updateUITicketBought(Session session);
    }
}
