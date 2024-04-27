package org.movieTheatre.java24groupe06.views;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import java.net.URL;

public class TicketViewController extends AbstractViewController<TicketViewController.Listener>{
    int nbTicketAdult;
    int nbTicketChildren;
    int nbTicketHandicap;
    int nbTicketVIP;
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
    @Override
    protected String getTitle() {
        return "TicketsShop";
    }

    public TicketViewController(Listener listener) {
        super(listener);
    }
    private Session session;

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void OnButtonPlusdDisabledClicked(MouseEvent mouseEvent) {
        System.out.println("Button plus disabled clicked");
        listener.OnButtonPlusDisabledClicked();
    }

    public void setTotalPrice(double totalPrice){
        totalPriceLabel.setText("Total: "+totalPrice+"€");
    }
    public void setTicketAdult(int nbTicketAdult){
        nbSelectedAdultSeats.setText(nbTicketAdult+ "  Tickets Adultes");
    }
    public void setTicketChildren(int nbTicketChildren){
        nbSelectedChildrenSeats.setText(nbTicketChildren+"  Tickets Enfants");
    }
    public void setTicketVIP(int nbTicketVIP){
        nbSelectedVIPSeats.setText(nbTicketVIP+" Tickets VIP");
    }
    public void setTicketHandicap(int nbTicketHandicap){
        nbSelectedHandicapSeats.setText(nbTicketHandicap+" Tickets Handicapés");
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

    @Override
    public String getFXMLPath() {
        return "ticket-View.fxml";
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
