package main.java.com.nuberketes.javachatapp.server;

import java.io.*;
import java.net.*;
import java.util.*;


public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler( Socket socket, List<ClientHandler> clients ) throws IOException {
        this.clientSocket = socket;
        this.clients = clients;
        this.out = new PrintWriter( clientSocket.getOutputStream(), true );
        this.in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream() ) );
    }

    public void run() {

        try {
            String inputLine;
            while (( inputLine = in.readLine() ) != null ) {
                for ( ClientHandler aClient : clients ) {
                    aClient.out.println( inputLine );
                }
            }
        } catch ( IOException e ) {
            System.out.println( "An error occurred: " + e.getMessage() );

        } finally {

            try {
                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
