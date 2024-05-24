package org.movieTheatre.java24groupe06.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;
import org.movieTheatre.java24groupe06.models.DAO.PurchaseDTO;
import org.movieTheatre.java24groupe06.models.PortConfig;
import org.movieTheatre.java24groupe06.models.Promotion.*;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.models.tickets.*;
import org.movieTheatre.java24groupe06.Network.Event.UpdateSessionSeatsEvent;
import org.movieTheatre.java24groupe06.Network.ObjectSocket;
import org.movieTheatre.java24groupe06.views.TicketViewController;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

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

    public void initializeTicket() throws CantLoadFXMLException, CustomExceptions {
        try {
            ticketViewController = new TicketViewController(this);
            ticketManager = new TicketManager(session);
            promotionManager = new PromotionManager(ticketManager.getTicketsList());
            ticketViewController.openOnNewStage();
            updateSeatsLabel();
            getStage().setOnCloseRequest(event -> handleWindowCloseRequest(event));
            initializeSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stage getStage() {
        return ticketViewController.getStage();
    }

    public void initializeSocket() throws CustomExceptions{
        try {
            Socket socket = new Socket(PortConfig.host, PortConfig.ticketPort);
            ObjectSocket objectSocket = new ObjectSocket(socket);
            readTicketThread = new ReadTicketThread(session,this,objectSocket);
            readTicketThread.start();
        } catch (IOException e) {
            AlertManager.showErrorAlert("Erreur lors de l'ouverture de la page des tickets", e);
            throw new CustomExceptions("Failed to open ticket page", e, ErrorCode.INITIALIZE_TICKETS_ERROR);
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


    public void updateSeatsLabel(){
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
    public void onButtonBuyClicked() throws CustomExceptions {
        if(nbSelectedAdultSeats == 0 && nbSelectedChildrenSeats == 0 && nbSelectedVIPSeats == 0 && nbSelectedHandicapSeats == 0){
            AlertManager.showErrorAlert("Vous n'avez pas sélectionné de tickets");
            return;
        }
        try {
            UpdateSessionSeatsEvent updateSessionSeatsEvent = new UpdateSessionSeatsEvent(new PurchaseDTO(session,nbSelectedAdultSeats+nbSelectedChildrenSeats,nbSelectedVIPSeats,nbSelectedHandicapSeats));
            objectSocket.write(updateSessionSeatsEvent);
            resetTicketCount();
            AlertManager.TicketBoughtAlert();
            ticketViewController.updateTotalPriceLabel(0);
            ticketViewController.resetCount();
        } catch (IOException e) {
            AlertManager.showErrorAlert("Erreur lors de la connexion au serveur", e);
            throw new CustomExceptions("Failed to buy tickets", e, ErrorCode.BUY_TICKET_ERROR);
        }
    }

    private boolean areSeatsSelected(){
        return !(
        nbSelectedAdultSeats == 0 &&
        nbSelectedChildrenSeats == 0 &&
        nbSelectedVIPSeats == 0 &&
        nbSelectedHandicapSeats == 0
        );
    }

    private void resetTicketCount(){
        setNbSelectedSelectedAdultSeats(0);
        setNbSelectedChildrenSeats(0);
        setNbSelectedVIPSeats(0);
        setNbSelectedHandicapSeats(0);
    }


    @Override
    public void onButtonPlusAdultClicked() {
        int availableSeats = session.getNbRegularSeats() - nbSelectedAdultSeats - nbSelectedChildrenSeats;
        if(availableSeats > 0){
            updateTicketCountAndUI(TicketAdult.class,true);
        }else{
            AlertManager.showErrorAlert("Plus de place disponibles");
        }
    }
    @Override
    public void onButtonMinusAdultClicked() {updateTicketCountAndUI(TicketAdult.class,false);}
    @Override
    public void onButtonPlusChildrenClicked() {
        int availableSeats = session.getNbRegularSeats() - nbSelectedAdultSeats - nbSelectedChildrenSeats;
        if(availableSeats > 0){
            updateTicketCountAndUI(TicketChildren.class,true);
        } else {
            AlertManager.showErrorAlert("Plus de place disponibles");
        }
    }
    @Override
    public void onButtonMinusChildrenClicked() {updateTicketCountAndUI(TicketChildren.class,false);}
    @Override
    public void onButtonPlusVIPClicked() {
        int availableSeats = session.getNbVIPSeats() - nbSelectedVIPSeats;
        if(availableSeats > 0){
            updateTicketCountAndUI(TicketVIP.class,true);
        } else {
            AlertManager.showErrorAlert("Plus de place disponibles");
        }
    }
    @Override
    public void onButtonMinusVIPClicked() {updateTicketCountAndUI(TicketVIP.class, false);}
    @Override
    public void onButtonPlusDisabledClicked() {
        int availableSeats = session.getNbHandicapsSeats() - nbSelectedHandicapSeats;
        if(availableSeats > 0){
            updateTicketCountAndUI(TicketHandicap.class,true);
        } else {
            AlertManager.showErrorAlert("Plus de place disponibles");
        }
    }
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
        updateSeatsLabel();
    }

    public interface Listener {
        void closeTicketView();
    }
}
