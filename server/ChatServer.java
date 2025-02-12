import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws IOException {

        // Initialize server socket and listen on port 2000
        ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println("Server started. Waiting for clients...");

        // Receive connection request and accept
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected.");
    }
}
