package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class CreateSessionHandlerThread extends Handler {

    private DTOCreateSession dtoCreateSession;
    ServerSocket serverSocket;
    Listener listener;
    private TicketHandler ticketHandler;

    public TicketHandler getTicketHandler(){
        return ticketHandler;
    }

    public CreateSessionHandlerThread(ObjectSocket objectSocket,DTOCreateSession dtoCreateSession,ServerSocket serverSocket,Listener listener) {
        super(objectSocket);
        this.dtoCreateSession = dtoCreateSession;
        this.serverSocket = serverSocket;
        this.listener = listener;
    }
    @Override
    public void run() {
        try {

                // On attend connexion de createTicketStage
                // On attend l'object DTO

                SessionDAO sessionDAO = new SessionDAO();
                // On cree un session a l'aide de l'object DTO
                Session session = sessionDAO.getSessionBySessionId(dtoCreateSession.getSessionID(), dtoCreateSession.getMovie());
                // On renvoie la session crée grace a DTO
                objectSocket.write(session);

                Socket socket = serverSocket.accept();
                ObjectSocket objectSocket = new ObjectSocket(socket);
                ticketHandler = new TicketHandler(objectSocket,session);

        } catch (IOException  | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public interface Listener {
        void onSeatsUpdated(Session session);
    }
}