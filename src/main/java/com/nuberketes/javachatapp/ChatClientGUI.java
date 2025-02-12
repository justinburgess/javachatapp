package main.java.com.nuberketes.javachatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ChatClientGUI extends JFrame {
    private JTextArea messageArea;
    private JTextField textField;
    private ChatClient client;

    public ChatClientGUI () {
        super( "Chat Application" );
        setSize( 400, 500 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        messageArea = new JTextArea();
        messageArea.setEditable( false );
        add( new JScrollPane(), BorderLayout.CENTER );

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                client.sendMessage( textField.getText() );
                textField.setText( "" );
            }
        });
        add( textField, BorderLayout.SOUTH );

        try {
            this.client = new ChatClient( "127.0.0.1", 5000, this::onMessageRecieved );
            client.startClient();
        } catch ( IOException e ) {
            e.printStackTrace();
            JOptionPane.showMessageDialog( this, "Error connecting to the server", "Connection error", JOptionPane.ERROR_MESSAGE );
            System.exit(1);
        }
    }

    private void onMessageReceived( String message ) {
        SwingUtilities.invokeLater( () -> messageArea.append( message + "\n") );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChatClientGUI().setVisible( true );
        });
    }
}
