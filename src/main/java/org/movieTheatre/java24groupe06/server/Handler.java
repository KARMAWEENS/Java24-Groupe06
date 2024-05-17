package org.movieTheatre.java24groupe06.server;

import java.net.ServerSocket;

public abstract class Handler implements Runnable {
    protected ObjectSocket objectSocket;

    public Handler(ObjectSocket ObjectSocket) {
        this.objectSocket = ObjectSocket;
    }
}
