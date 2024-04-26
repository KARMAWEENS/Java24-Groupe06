package org.movieTheatre.java24groupe06.controllers;

import javafx.stage.Stage;
import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.models.exceptions.CantLoadFXMLException;
import org.movieTheatre.java24groupe06.views.TicketViewController;

import java.io.IOException;

public class TicketController implements TicketViewController.Listener {
    public Listener listener;
    public Session session;
    public TicketController(Listener listener, Session session) {
        this.listener = listener;
        this.session = session;
    }

    public void initializeTicket() throws CantLoadFXMLException {
        TicketViewController ticketViewController = new TicketViewController(this);
        try {
            ticketViewController.openOnNewStage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    public interface Listener {

    }
}
