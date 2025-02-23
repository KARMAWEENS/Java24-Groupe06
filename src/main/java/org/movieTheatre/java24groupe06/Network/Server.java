package org.movieTheatre.java24groupe06.Network;

import org.movieTheatre.java24groupe06.Network.exceptions.HandleExceptions;
import org.movieTheatre.java24groupe06.models.PortConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * The Server class provides methods for managing the server.
 */
public class Server {


    public static ServerSocket ticketServerSocket;
    /**
     * The main method is the entry point of the server.
     * @param args the arguments of the server.
     */
    public static void main(String[] args) {
        HandleExceptions exceptionHandler = new HandleExceptions();
        Server server = new Server();
        try {
            server.go();
        } catch (IOException e) {
            exceptionHandler.handleException("Erreur lors du lancement du serveur", e);
        }
    }
    /**
     * The go method starts the server.
     * @throws IOException if an I/O error occurs.
     */
    private void go() throws IOException {
        PortConfig portConfig = new PortConfig();
        portConfig.loadConfig();
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