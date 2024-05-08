package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

import java.net.ServerSocket;
import java.net.Socket;

public class UpdateSessionSeatsHandlerThread extends Handler<UpdateSessionSeatsHandlerThread.Listener> {
    public UpdateSessionSeatsHandlerThread(ServerSocket serverSocketUpdateSessionSeats,Listener listener) {
        super(serverSocketUpdateSessionSeats, listener);
    }

    @Override
    public void run() {
    while (true) {
            try {
                // On attend un connexion de ButtonClicked
                Socket client = serverSocket.accept();
                ObjectSocket objectSocket = new ObjectSocket(client);
                // On attend l'envoie des places achetées
                DTOBuy dtoBuy = objectSocket.read();
                SessionDAO sessionDAO = new SessionDAO();
                // On retire les places achetées dans la DB
                sessionDAO.update(dtoBuy.getSession(),dtoBuy.getNbRegularSeatsBuy(),dtoBuy.getNbVIPSeatsBuy(),dtoBuy.getNbHandicapsSeatsBuy());
                // On check si on doit informer d'autres clients qui sont sur la meme session
                listener.onSeatsUpdated(dtoBuy.getSession());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public interface Listener {
        void onSeatsUpdated(Session session);
    }
}
