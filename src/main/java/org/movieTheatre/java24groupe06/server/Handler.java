package org.movieTheatre.java24groupe06.server;

import java.net.ServerSocket;

public abstract class Handler<T> implements Runnable {
    protected ServerSocket serverSocket;
    protected T listener;

    public Handler(ServerSocket serverSocket, T listener) {
        this.serverSocket = serverSocket;
        this.listener = listener;
    }
}
