package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.models.PortConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        PortConfig portConfig = new PortConfig();
        portConfig.loadConfig("src/main/resources/conf.txt");
        ServerSocket mainServerSocket = new ServerSocket(PortConfig.mainPort);
         ticketServerSocket = new ServerSocket(PortConfig.ticketPort);
        while (true) {
            Socket client = mainServerSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(client);
            ClientRequestHandlerThread clientRequestHandlerThread = new ClientRequestHandlerThread(objectSocket);
            clientRequestHandlerThread.start();
        }

    }

}