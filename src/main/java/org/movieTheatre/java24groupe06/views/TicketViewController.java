package org.movieTheatre.java24groupe06.views;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import java.net.URL;

public class TicketViewController extends AbstractViewController<TicketViewController.Listener>{
    @Override
    protected String getTitle() {
        return "TicketsShop";
    }

    public TicketViewController(Listener listener) {
        super(listener);
    }
    private Session session;
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void OnButtonPlusdDisabledClicked(MouseEvent mouseEvent) {
        listener.OnButtonPlusdDisabledClicked();
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
        listener.OnButtonPlusChildrenClicked();
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

        void OnButtonPlusdDisabledClicked();
    }
}
