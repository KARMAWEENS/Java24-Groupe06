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


    public void setTotalPriceLabel(double totalPrice){
        totalPriceLabel.setText("Total: "+totalPrice+"€");
    }
    public void setTicketAdultLabel(int nbTicketAdult){
        nbSelectedAdultSeats.setText(nbTicketAdult+ "  Tickets Adultes");
    }
    public void setTicketChildrenLabel(int nbTicketChildren){
        nbSelectedChildrenSeats.setText(nbTicketChildren+ "  Tickets Enfants");
    }
    public void setTicketVIPLabel(int nbTicketVIP){
        nbSelectedVIPSeats.setText(nbTicketVIP+" Tickets VIP");
    }
    public void setTicketHandicapLabel(int nbTicketHandicap){
        nbSelectedHandicapSeats.setText(nbTicketHandicap+" Tickets Handicapés");
    }

    public void OnButtonPlusDisabledClicked(MouseEvent mouseEvent) {
        listener.OnButtonPlusDisabledClicked();
    }
    public void OnButtonMinusDisabledClicked(MouseEvent mouseEvent) {
        listener.OnButtonMinusDisabledClicked();
    }
    public void OnButtonPlusVIPClicked(MouseEvent mouseEvent) {
        listener.OnButtonPlusVIPClicked();
    }
    public void OnButtonMinusVIPClicked(MouseEvent mouseEvent) {
        listener.OnButtonMinusVIPClicked();
    }
    public void OnButtonPlusChildrenClicked(MouseEvent mouseEvent) {
        listener.OnButtonPlusChildrenClicked();
    }
    public void OnButtonPlusAdultClicked(MouseEvent mouseEvent) {
        listener.OnButtonPlusAdultClicked();
    }
    public void OnButtonMinusChildrenClicked(MouseEvent mouseEvent) {
        listener.OnButtonMinusChildrenClicked();
    }
    public void OnButtonMinusAdultClicked(MouseEvent mouseEvent) {
        listener.OnButtonMinusAdultClicked();
    }

    public interface Listener {
        void OnButtonPlusAdultClicked();
        void OnButtonMinusAdultClicked();
        void OnButtonMinusChildrenClicked();
        void OnButtonPlusChildrenClicked();
        void OnButtonMinusVIPClicked();
        void OnButtonPlusVIPClicked();
        void OnButtonMinusDisabledClicked();
        void OnButtonPlusDisabledClicked();
    }
}
