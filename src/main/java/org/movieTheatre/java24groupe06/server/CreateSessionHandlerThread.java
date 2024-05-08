package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.DAO.DTOCreateSession;
import org.movieTheatre.java24groupe06.models.DAO.SessionDAO;
import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class CreateSessionHandlerThread extends Handler<CreateSessionHandlerThread.Listener> implements TicketHandlerThread.Listener {
    public CreateSessionHandlerThread(ServerSocket serverSocketCreateSession,Listener listener){
        super(serverSocketCreateSession, listener);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket client = serverSocket.accept();
                ObjectSocket objectSocket = new ObjectSocket(client);
                Thread thread = new Thread(new TicketHandlerThread(objectSocket,this));
                thread.start();
                DTOCreateSession dtoCreateSession = objectSocket.read();
                SessionDAO sessionDAO = new SessionDAO();
                Session session = sessionDAO.getSessionBySessionId(dtoCreateSession.getSessionID(), dtoCreateSession.getMovie());
                objectSocket.write(session);
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public interface Listener {
    }
}