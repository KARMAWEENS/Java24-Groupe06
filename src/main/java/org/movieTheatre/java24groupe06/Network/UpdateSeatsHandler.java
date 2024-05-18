package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

public class UpdateSeatsHandler extends NetworkHandler {

    DTOBuy dtoBuy;
    Listener listener;
    public UpdateSeatsHandler(ObjectSocket objectSocket, DTOBuy dtoBuy, Listener listener) {
        super(objectSocket);
        this.dtoBuy = dtoBuy;
        this.listener = listener;
    }

    @Override
    public void run() {

            try {
                SessionDAO sessionDAO = new SessionDAO();
                sessionDAO.update(dtoBuy.getSession(),dtoBuy.getNbRegularSeatsBuy(),dtoBuy.getNbVIPSeatsBuy(),dtoBuy.getNbHandicapsSeatsBuy());
                listener.onSeatsUpdated(dtoBuy.getSession());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        
    }
    public interface Listener {
        void onSeatsUpdated(Session session);
    }
}
