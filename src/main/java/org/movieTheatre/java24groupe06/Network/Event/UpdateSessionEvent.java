package org.movieTheatre.java24groupe06.Network.Event;

import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;

import java.io.Serializable;

public class UpdateSessionEvent implements Serializable {
    public DTOBuy getDtoBuy() {
        return dtoBuy;
    }

    DTOBuy dtoBuy;
    public UpdateSessionEvent(DTOBuy dtoBuy) {
        this.dtoBuy = dtoBuy;
    }
}
