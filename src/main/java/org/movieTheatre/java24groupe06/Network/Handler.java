package org.movieTheatre.java24groupe06.Network;

public abstract class Handler implements Runnable {
    protected ObjectSocket objectSocket;

    public Handler(ObjectSocket ObjectSocket) {
        this.objectSocket = ObjectSocket;
    }
}
