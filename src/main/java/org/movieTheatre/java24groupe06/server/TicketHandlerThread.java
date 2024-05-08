package org.movieTheatre.java24groupe06.server;

import java.net.ServerSocket;

public class TicketHandlerThread implements Runnable {
    ObjectSocket objectSocket;
    Listener listener;

    public TicketHandlerThread(ObjectSocket objectSocket, Listener listener) {
       this.objectSocket = objectSocket;
       this.listener = listener;
    }

    @Override
    public void run() {

    }
    public interface Listener {
    }
}
