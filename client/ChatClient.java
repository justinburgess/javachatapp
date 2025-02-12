import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws IOException {

        // Initialize connection socket and connect on port 5000
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server.");
    }
}
