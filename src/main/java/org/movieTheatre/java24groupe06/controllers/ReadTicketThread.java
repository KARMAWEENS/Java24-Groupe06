package org.movieTheatre.java24groupe06.controllers;

import org.movieTheatre.java24groupe06.models.Session;
import org.movieTheatre.java24groupe06.server.ObjectSocket;

import java.io.IOException;
import java.net.Socket;

public class ReadTicketThread implements Runnable{

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 8082);
            ObjectSocket objectSocket = new ObjectSocket(socket);
            while(true){
               Session session = objectSocket.read();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
