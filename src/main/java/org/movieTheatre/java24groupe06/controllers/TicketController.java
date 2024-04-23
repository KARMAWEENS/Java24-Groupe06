package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.TicketViewController;

public class TicketController implements TicketViewController.Listener{

    public void initializeTicket() throws CantLoadFXMLException {
        TicketViewController ticketViewController = new TicketViewController().showInStage(new Stage());
        ticketViewController.setListener(this);

    }
    @Override
    public void OnButtonPlusAdultClicked() {
        System.out.println("jsuis ici");
    }

    @Override
    public void OnButtonMinusAdultClicked() {

    }

    @Override
    public void OnButtonMinusChildrenClicked() {

    }

    @Override
    public void OnButtonPlusChildrenClicked() {

    }

    @Override
    public void OnButtonMinusVIPClicked() {

    }

    @Override
    public void OnButtonPlusVIPClicked() {

    }

    @Override
    public void OnButtonMinusDisabledClicked() {

    }

    @Override
    public void OnButtonPlusdDisabledClicked() {

    }

}
