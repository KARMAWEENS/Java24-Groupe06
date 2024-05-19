package org.movieTheatre.java24groupe06.Network;

import java.io.IOException;

public class Testtttt extends Thread{

    SessionHandler sessionHandler;
    ObjectSocket objectSocket;
    Listener listener;
    public Testtttt(SessionHandler sessionHandler, ObjectSocket objectSocket, Listener listener){
        this.sessionHandler = sessionHandler;
        this.objectSocket = objectSocket;
        this.listener = listener;
        System.out.println(sessionHandler + "  coucou ");
    }

    @Override
    public void run(){
        try {
            objectSocket.read();
        } catch (IOException e) {
            System.out.println("fdsf");
            System.out.println(sessionHandler);
            System.out.println(sessionHandler.getId());
            this.listener.planted(sessionHandler);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public interface Listener{
        void planted(SessionHandler sessionHandler);
    }
}
