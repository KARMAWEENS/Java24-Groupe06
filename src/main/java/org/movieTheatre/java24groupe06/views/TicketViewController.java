package org.movieTheatre.java24groupe06.views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.controllers.TicketController;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions;
import org.movieTheatre.java24groupe06.controllers.exceptions.CustomExceptions.*;
import org.movieTheatre.java24groupe06.models.tickets.Ticket;
import org.movieTheatre.java24groupe06.views.exceptions.AlertManager;

/**
 * The TicketViewController class provides methods for managing the ticket view controller.
 */
public class TicketViewController extends AbstractViewController<TicketViewController.Listener>{

    @FXML
    private Label nbSelectedAdultSeats;
    @FXML
    private Label nbSelectedChildrenSeats;
    @FXML
    private Label nbSelectedVIPSeats;
    @FXML
    private Label nbSelectedHandicapSeats;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Label availableAdultSeats;
    @FXML
    private Label availableChildrenSeats;
    @FXML
    private Label  availableHandicapSeats;
    @FXML
    private Label availableVIPSeats;
    @FXML
    private Button returnButton;
    @FXML
    private Label priceAdult;
    @FXML
    private Label priceChildren;
    @FXML
    private Label priceVIP;
    @FXML
    private Label pricedisabled;

   /**
     * The TicketViewController method is the constructor of the class.
     */
    public Stage getStage(){
        return stage;
    }
    /**
     * The TicketViewController method is the constructor of the class.
     * @param listener the listener.
     */
    public TicketViewController(Listener listener) {
        super(listener);
    }
    /**
     * The setTicket method sets the ticket.
     */
    @Override
    protected String getTitle() {
        return "TicketsShop";
    }
    /**
     * The getFXMLPath method returns the FXML path.
     * @return the FXML path.
     */
    @Override
    public String getFXMLPath() {
        return "ticket-View.fxml";
    }


    public void updateTotalPriceLabel(double totalPrice){
        totalPriceLabel.setText("Total: "+totalPrice+"€");
    }



    public void updateTicketAdultLabel(int nbTicketAdult){
        nbSelectedAdultSeats.setText(nbTicketAdult+ "  Tickets Adultes");
    }



    public void updateTicketChildrenLabel(int nbTicketChildren){
        nbSelectedChildrenSeats.setText(nbTicketChildren+ "  Tickets Enfants");
    }



    public void updateTicketVIPLabel(int nbTicketVIP){
        nbSelectedVIPSeats.setText(nbTicketVIP+" Tickets VIP");
    }



    public void updateTicketHandicapLabel(int nbTicketHandicap){
        nbSelectedHandicapSeats.setText(nbTicketHandicap+" Tickets Handicapés");
    }



    public void resetCount(){
        updateTicketAdultLabel(0);
        updateTicketChildrenLabel(0);
        updateTicketVIPLabel(0);
        updateTicketHandicapLabel(0);
    }

    public void updateAvailableAdultSeatsLabel(int seatsAdultLeft){
        availableAdultSeats.setText(String.valueOf(seatsAdultLeft));
    }

    public void updateAvailableChildrenSeatsLabel(int seatsChildrenLeft){
        availableChildrenSeats.setText(String.valueOf(seatsChildrenLeft));
    }
    public void updateAvailableVIPSeatsLabel(int seatsVIPLeft){
        availableVIPSeats.setText(String.valueOf(seatsVIPLeft));
    }
    public void updateAvailableHandicapSeatsLabel(int seatsHandicapLeft){
        availableHandicapSeats.setText(String.valueOf(seatsHandicapLeft));
    }
    public void onReturnButtonClicked(ActionEvent event){
        listener.onReturnButtonClicked();
    }



    public void OnButtonPlusDisabledClicked(MouseEvent mouseEvent) {
        listener.onButtonPlusDisabledClicked();
    }
    public void OnButtonMinusDisabledClicked(MouseEvent mouseEvent) {
        listener.onButtonMinusDisabledClicked();
    }
    public void OnButtonPlusVIPClicked(MouseEvent mouseEvent) {
        listener.onButtonPlusVIPClicked();
    }
    public void OnButtonMinusVIPClicked(MouseEvent mouseEvent) {
        listener.onButtonMinusVIPClicked();
    }
    public void OnButtonPlusChildrenClicked(MouseEvent mouseEvent) {
        listener.onButtonPlusChildrenClicked();
    }
    public void OnButtonPlusAdultClicked(MouseEvent mouseEvent) {
        listener.onButtonPlusAdultClicked();
    }
    public void OnButtonMinusChildrenClicked(MouseEvent mouseEvent) {
        listener.onButtonMinusChildrenClicked();
    }
    public void OnButtonMinusAdultClicked(MouseEvent mouseEvent) {
        listener.onButtonMinusAdultClicked();
    }
    public void OnButtonBuyClicked(MouseEvent mouseEvent) throws CustomExceptions {
        listener.onButtonBuyClicked();
    }


    public interface Listener {
        void onButtonPlusAdultClicked();
        void onButtonMinusAdultClicked();
        void onButtonMinusChildrenClicked();
        void onButtonPlusChildrenClicked();
        void onButtonMinusVIPClicked();
        void onButtonPlusVIPClicked();
        void onButtonMinusDisabledClicked();
        void onButtonPlusDisabledClicked();
        void onButtonBuyClicked() throws CustomExceptions;
        void onReturnButtonClicked();
    }
}
