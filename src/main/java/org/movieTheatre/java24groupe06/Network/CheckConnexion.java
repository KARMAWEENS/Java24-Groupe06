package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.Network.exceptions.ClassNotFoundExceptionHandler;

import java.io.IOException;

public class CheckConnexion extends Thread{

    SessionHandler sessionHandler;
    ObjectSocket objectSocket;
    Listener listener;
    ClassNotFoundExceptionHandler exceptionHandler;

    public CheckConnexion(SessionHandler sessionHandler, ObjectSocket objectSocket, Listener listener){
        this.sessionHandler = sessionHandler;
        this.objectSocket = objectSocket;
        this.listener = listener;
        this.exceptionHandler = new ClassNotFoundExceptionHandler();
    }

    @Override
    public void run(){
        try {
            objectSocket.read();
        } catch (IOException e) {
            this.listener.onConnexionLost(sessionHandler);
        } catch (ClassNotFoundException e) {
            exceptionHandler.handle(e);
        }
    }
    public interface Listener{
        void onConnexionLost(SessionHandler sessionHandler);
    }
}