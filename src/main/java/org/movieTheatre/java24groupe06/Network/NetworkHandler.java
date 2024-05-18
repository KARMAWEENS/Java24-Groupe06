package org.movieTheatre.java24groupe06.Network;

public abstract class NetworkHandler implements Runnable {
    protected ObjectSocket objectSocket;

    public NetworkHandler(ObjectSocket ObjectSocket) {
        this.objectSocket = ObjectSocket;
    }
}
