package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.DTOBuy;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

import java.net.ServerSocket;
import java.net.Socket;

public class UpdateSessionSeatsHandlerThread extends Handler {
    public UpdateSessionSeatsHandlerThread(ServerSocket serverSocketUpdateSessionSeats,Listener listener) {
        super(serverSocketUpdateSessionSeats, listener);
    }

    @Override
    public void run() {
while (true) {
            try {
                Socket client = serverSocket.accept();
                ObjectSocket objectSocket = new ObjectSocket(client);
                DTOBuy dtoBuy = objectSocket.read();
                SessionDAO sessionDAO = new SessionDAO();
                sessionDAO.update(dtoBuy.getSession(),dtoBuy.getNbRegularSeatsBuy(),dtoBuy.getNbVIPSeatsBuy(),dtoBuy.getNbHandicapsSeatsBuy());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public interface Listener {
    }
}
