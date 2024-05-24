package org.movieTheatre.java24groupe06.models.DAO;

import org.movieTheatre.java24groupe06.models.Session;

import java.io.Serializable;
import java.util.Objects;
/**
 * The PurchaseDTO class provides methods for creating a purchase.
 */
public class PurchaseDTO implements Serializable {
    private Session session;
    private int nbRegularSeatsBuy;
    private int nbHandicapsSeatsBuy;
    private int nbVIPSeatsBuy;
    /**
     * The constructor for the PurchaseDTO class.
     *
     * @param session the session for the purchase.
     * @param nbRegularSeatsBuy the number of regular seats to buy.
     * @param nbVIPSeatsBuy the number of VIP seats to buy.
     * @param nbHandicapsSeatsBuy the number of handicapped seats to buy.
     */
    public PurchaseDTO(Session session, int nbRegularSeatsBuy, int nbVIPSeatsBuy, int nbHandicapsSeatsBuy) {
        this.session = Objects.requireNonNull(session , "La session ne peut pas être nulle");
        if (nbRegularSeatsBuy < 0 || nbVIPSeatsBuy < 0 || nbHandicapsSeatsBuy < 0) {
            throw new IllegalArgumentException("Le nombre de places ne peut pas être négatif");
        }
        this.nbRegularSeatsBuy = nbRegularSeatsBuy;
        this.nbVIPSeatsBuy = nbVIPSeatsBuy;
        this.nbHandicapsSeatsBuy = nbHandicapsSeatsBuy;
    }
    /**
     * Retrieves the session for the purchase.
     *
     * @return the session for the purchase.
     */
    public Session getSession() {
        return session;
    }
    /**
     * Retrieves the number of regular seats to buy.
     *
     * @return the number of regular seats to buy.
     */
    public int getNbRegularSeatsBuy() {
        return nbRegularSeatsBuy;
    }
    /**
     * Retrieves the number of handicapped seats to buy.
     *
     * @return the number of handicapped seats to buy.
     */
    public int getNbHandicapsSeatsBuy() {
        return nbHandicapsSeatsBuy;
    }
    /**
     * Retrieves the number of VIP seats to buy.
     *
     * @return the number of VIP seats to buy.
     */
    public int getNbVIPSeatsBuy() {
        return nbVIPSeatsBuy;
    }
}
