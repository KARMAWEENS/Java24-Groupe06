package org.movieTheatre.java24groupe06.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    List<CreateSessionNetworkHandlerThread> createSessionHandlerThreads =new ArrayList<>();
    public static void main(String[] args) {
        Server serve = new Server();
        try {
            serve.go();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void go() throws IOException {
        ServerSocket mainServerSocket = new ServerSocket(7999);
        ServerSocket ticketServerSocket = new ServerSocket(8000);
        while (true) {
            Socket client = mainServerSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(client);
            Thread readObjectThread = new Thread(new ClientRequestHandler(objectSocket,ticketServerSocket,createSessionHandlerThreads));
            readObjectThread.start();
        }

    }

}