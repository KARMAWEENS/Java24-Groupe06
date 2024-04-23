package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.views.TicketViewController;

public class TicketReservationController implements TicketViewController.Listener{

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
