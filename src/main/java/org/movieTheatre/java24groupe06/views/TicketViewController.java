package org.movieTheatre.java24groupe06.views;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

    public interface Listener {
        void onButtonPlusAdultClicked();
        void onButtonMinusAdultClicked();
        void onButtonMinusChildrenClicked();
        void onButtonPlusChildrenClicked();
        void onButtonMinusVIPClicked();
        void onButtonPlusVIPClicked();
        void onButtonMinusDisabledClicked();
        void onButtonPlusDisabledClicked();
    }
}
