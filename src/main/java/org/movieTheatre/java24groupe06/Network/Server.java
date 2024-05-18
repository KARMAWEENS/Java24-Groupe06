package org.movieTheatre.java24groupe06.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static ServerSocket ticketServerSocket;

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.go();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void go() throws IOException {
        ServerSocket mainServerSocket = new ServerSocket(7999);
         ticketServerSocket = new ServerSocket(8000);
        while (true) {
            Socket client = mainServerSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(client);
            Thread clientRequestHandler = new Thread(new ClientRequestHandler(objectSocket));
            clientRequestHandler.start();
        }

    }

}