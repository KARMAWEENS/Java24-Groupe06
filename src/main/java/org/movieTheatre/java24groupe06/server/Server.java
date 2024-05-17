package org.movieTheatre.java24groupe06.server;

import org.movieTheatre.java24groupe06.models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    List<CreateSessionHandlerThread> createSessionHandlerThreads =new ArrayList<>();
    public static void main(String[] args) {
        Server serve = new Server();
        try {
            serve.go();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void go() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7999);
        ServerSocket serverSocket2 = new ServerSocket(8000);
        while (true) {
            Socket client = serverSocket.accept();
            ObjectSocket objectSocket = new ObjectSocket(client);
            Thread readObjectThread = new Thread(new ReadObjectThread(objectSocket,serverSocket2,createSessionHandlerThreads));
            readObjectThread.start();
        }

    }

}