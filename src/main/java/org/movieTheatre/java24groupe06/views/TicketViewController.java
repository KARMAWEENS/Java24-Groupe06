package org.movieTheatre.java24groupe06.views;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import java.net.URL;

public class TicketViewController extends AbstractViewController{
    public static URL getViewURL() {
        return TicketViewController.class.getResource("ticket-View.fxml");
    }
    private static String titleStage ="Shop";

    public void setSession(Session session) {
        this.session = session;
    }

    private Session session;
    Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public static TicketViewController showInStage(Stage mainStage) throws CantLoadFXMLException {
        mainStage.initModality(Modality.APPLICATION_MODAL);
        return showFXMLOnStage(getViewURL(), mainStage,titleStage);
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
