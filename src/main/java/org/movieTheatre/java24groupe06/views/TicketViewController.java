package org.movieTheatre.java24groupe06.views;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    // a mettre dans abstrat
    public Stage getStage(){
        return stage;
    }

    public TicketViewController(Listener listener) {
        super(listener);
    }

    @Override
    protected String getTitle() {
        return "TicketsShop";
    }
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
    public void OnButtonBuyClicked(MouseEvent mouseEvent) {
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
        void onButtonBuyClicked();
        void onReturnButtonClicked();
    }
}
