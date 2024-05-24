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
     * The stage
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
     * The getTitle method returns the title.
     * @return
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

    /**
     * update the total price label
     * @param totalPrice
     */
    public void updateTotalPriceLabel(double totalPrice){
        totalPriceLabel.setText("Total: "+totalPrice+"€");
    }


    /**
     * update the price of the adult ticket
     * @param nbTicketAdult
     */
    public void updateTicketAdultLabel(int nbTicketAdult){
        nbSelectedAdultSeats.setText(nbTicketAdult+ "  Tickets Adultes");
    }


    /**
     * update the price of the children ticket
     * @param nbTicketChildren
     */
    public void updateTicketChildrenLabel(int nbTicketChildren){
        nbSelectedChildrenSeats.setText(nbTicketChildren+ "  Tickets Enfants");
    }


    /**
     * update the price of the VIP ticket
     * @param nbTicketVIP
     */
    public void updateTicketVIPLabel(int nbTicketVIP){
        nbSelectedVIPSeats.setText(nbTicketVIP+" Tickets VIP");
    }


    /**
     * update the price of the handicap ticket
     * @param nbTicketHandicap
     */
    public void updateTicketHandicapLabel(int nbTicketHandicap){
        nbSelectedHandicapSeats.setText(nbTicketHandicap+" Tickets Handicapés");
    }


    /**
     * reset the count of the tickets
     */
    public void resetCount(){
        updateTicketAdultLabel(0);
        updateTicketChildrenLabel(0);
        updateTicketVIPLabel(0);
        updateTicketHandicapLabel(0);
    }

    /**
     * update the available adults seats label
     * @param seatsAdultLeft
     */
    public void updateAvailableAdultSeatsLabel(int seatsAdultLeft){
        availableAdultSeats.setText(String.valueOf(seatsAdultLeft));
    }
    /**
     * update the available children seats label
     * @param seatsChildrenLeft
     */
    public void updateAvailableChildrenSeatsLabel(int seatsChildrenLeft){
        availableChildrenSeats.setText(String.valueOf(seatsChildrenLeft));
    }
    /**
     * update the available VIP seats label
     * @param seatsVIPLeft
     */
    public void updateAvailableVIPSeatsLabel(int seatsVIPLeft){
        availableVIPSeats.setText(String.valueOf(seatsVIPLeft));
    }
    /**
     * update the available handicap seats label
     * @param seatsHandicapLeft
     */
    public void updateAvailableHandicapSeatsLabel(int seatsHandicapLeft){
        availableHandicapSeats.setText(String.valueOf(seatsHandicapLeft));
    }

    /**
     * listener on the return button clicked
     * @param event
     */
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

    /**
     * The Listener interface provides methods for managing the listener.
     */
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
