package org.movieTheatre.java24groupe06.server;

import java.net.ServerSocket;

public abstract class Handler<T> implements Runnable {
    protected ObjectSocket objectSocket;
    protected T listener;

    public Handler(ObjectSocket serverSocket, T listener) {
        this.objectSocket = serverSocket;
        this.listener = listener;
    }
}
