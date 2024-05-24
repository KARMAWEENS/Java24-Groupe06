package org.movieTheatre.java24groupe06.Network.Event;

import org.movieTheatre.java24groupe06.models.DAO.PurchaseDTO;

import java.io.Serializable;
/**
 * The UpdateSessionSeatsEvent class provides methods for managing the update session seats event.
 */
public class UpdateSessionSeatsEvent implements Serializable {
    public PurchaseDTO getDtoBuy() {
        return purchaseDto;
    }

    PurchaseDTO purchaseDto;
    public UpdateSessionSeatsEvent(PurchaseDTO purchaseDto) {
        this.purchaseDto = purchaseDto;
    }
}
