package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

import java.net.ServerSocket;
import java.net.Socket;

public class UpdateSessionSeatsHandlerThread extends Handler<UpdateSessionSeatsHandlerThread.Listener> {
    public UpdateSessionSeatsHandlerThread(ObjectSocket objectSocket,Listener listener,DTOBuy dtoBuy) {
        super(objectSocket, listener);
    this.dtoBuy = dtoBuy;
    }
DTOBuy dtoBuy;
    @Override
    public void run() {

            try {
                // On attend un connexion de ButtonClicked
                // On attend l'envoie des places achetées
                SessionDAO sessionDAO = new SessionDAO();
                // On retire les places achetées dans la DB
                sessionDAO.update(dtoBuy.getSession(),dtoBuy.getNbRegularSeatsBuy(),dtoBuy.getNbVIPSeatsBuy(),dtoBuy.getNbHandicapsSeatsBuy());
                // On check si on doit informer d'autres clients qui sont sur la meme session
                listener.onSeatsUpdated(dtoBuy.getSession());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        
    }
    public interface Listener {
        void onSeatsUpdated(Session session);
    }
}
