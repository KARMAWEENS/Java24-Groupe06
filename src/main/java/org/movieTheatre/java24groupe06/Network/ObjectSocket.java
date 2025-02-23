package org.movieTheatre.java24groupe06.Network;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class   ObjectSocket  {

    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    /**
     * Create a new ObjectSocket from a socket
     * @param socket The socket to create the ObjectSocket from
     * @throws IOException If the socket cannot be created
     */
    public ObjectSocket(Socket socket) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Write an object to the socket
     * @param object The object to write
     * @throws IOException If the object cannot be written
     */
    public void write(Object object) throws IOException {
        out.reset();
        out.writeObject(object);
    }

    /**
     * Read an object from the socket
     * @return The object read
     * @param <T> The type of the object to read (deduced from the context of the method call)
     * @throws IOException If the object cannot be read
     * @throws ClassNotFoundException If the object class cannot be found
 */
    public <T> T read() throws IOException, ClassNotFoundException {
        return (T) in.readObject();
    }
    /**
     * Close the socket
     * @throws IOException If the socket cannot be closed
     */
    public void close() throws IOException{
            this.in.close();
            this.out.close();
    }
}
