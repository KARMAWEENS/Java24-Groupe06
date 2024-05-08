package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Session;

import java.io.Serializable;

public class DTOBuy implements Serializable {
    private Session session;
    private int nbRegularSeatsBuy;
    private int nbHandicapsSeatsBuy;
    private int nbVIPSeatsBuy;

    public DTOBuy(Session session, int nbRegularSeats,  int nbVIPSeats,int nbHandicapsSeats) {
        this.session = session;
        this.nbRegularSeatsBuy = nbRegularSeats;
        this.nbHandicapsSeatsBuy = nbHandicapsSeats;
        this.nbVIPSeatsBuy = nbVIPSeats;
    }

    public Session getSession() {
        return session;
    }

    public int getNbRegularSeatsBuy() {
        return nbRegularSeatsBuy;
    }

    public int getNbHandicapsSeatsBuy() {
        return nbHandicapsSeatsBuy;
    }

    public int getNbVIPSeatsBuy() {
        return nbVIPSeatsBuy;
    }
}
