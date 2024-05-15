package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateSessionHandlerThread extends Handler<CreateSessionHandlerThread.Listener>  {
    List<TicketHandlerThread> ticketHandlerThreadslist = new ArrayList<>();
    private DTOCreateSession dtoCreateSession;
    public CreateSessionHandlerThread(ObjectSocket objectSocket,Listener listener,DTOCreateSession dtoCreateSession){
        super(objectSocket, listener);
        this.dtoCreateSession = dtoCreateSession;
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

                //Maj de seatRoomLeft
                TicketHandlerThread ticketHandlerThread = new TicketHandlerThread(objectSocket,session);
                ticketHandlerThreadslist.add(ticketHandlerThread);
                Thread thread = new Thread(ticketHandlerThread);
                thread.start();
        } catch (IOException  | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void broadcast(Session session){
        for (TicketHandlerThread ticketHandlerThread : ticketHandlerThreadslist) {
           if(ticketHandlerThread.getSession().getSessionID() == session.getSessionID()){
               System.out.println("faut changer ui ");
               ticketHandlerThread.updateUI(session);
           }
           else {
               System.out.println("faut pas changer ui ");
           }
        }
    }
    public interface Listener {
    }
}