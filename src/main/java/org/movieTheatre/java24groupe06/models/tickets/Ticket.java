package org.movieTheatre.java24groupe06.models.tickets;

import org.movieTheatre.java24groupe06.models.Session;

public  abstract class Ticket {
    private double price;
    private boolean isPromotionApplied = false;
    private Session session;
    public Ticket(Session session) {
        this.session=session;
    }

    public abstract double getPrice();
    public void setPromotionApplied(boolean isPromotionApplied) {
        this.isPromotionApplied=isPromotionApplied;
    }
    public  boolean isPromotionApplied(){
        return isPromotionApplied;
    }
}
