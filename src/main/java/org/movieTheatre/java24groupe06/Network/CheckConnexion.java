package org.movieTheatre.java24groupe06.Network;

import java.io.IOException;

public class CheckConnexion extends Thread{

    SessionHandler sessionHandler;
    ObjectSocket objectSocket;
    Listener listener;
    public CheckConnexion(SessionHandler sessionHandler, ObjectSocket objectSocket, Listener listener){
        this.sessionHandler = sessionHandler;
        this.objectSocket = objectSocket;
        this.listener = listener;
    }

    @Override
    public void run(){
        try {
            objectSocket.read();
        } catch (IOException e) {
            this.listener.onConnexionLost(sessionHandler);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public interface Listener{
        void onConnexionLost(SessionHandler sessionHandler);
    }
}
