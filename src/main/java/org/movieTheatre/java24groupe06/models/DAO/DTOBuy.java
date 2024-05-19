package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Session;

import java.io.Serializable;
import java.util.Objects;

public class DTOBuy implements Serializable {
    private Session session;
    private int nbRegularSeatsBuy;
    private int nbHandicapsSeatsBuy;
    private int nbVIPSeatsBuy;

    public DTOBuy(Session session, int nbRegularSeatsBuy, int nbVIPSeatsBuy, int nbHandicapsSeatsBuy) {
        this.session = Objects.requireNonNull(session , "La session ne peut pas être nulle");
        if (nbRegularSeatsBuy < 0 || nbVIPSeatsBuy < 0 || nbHandicapsSeatsBuy < 0) {
            throw new IllegalArgumentException("Le nombre de places ne peut pas être négatif");
        }
        this.nbRegularSeatsBuy = nbRegularSeatsBuy;
        this.nbVIPSeatsBuy = nbVIPSeatsBuy;
        this.nbHandicapsSeatsBuy = nbHandicapsSeatsBuy;
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
