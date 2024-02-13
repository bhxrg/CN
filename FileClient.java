package Javaprgm;

import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        new FileClient();
    }

    public FileClient() {
        // Create a BufferedReader to read input from the console
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Prompt the user to enter the IP address of the server
            System.out.println("Enter IP address of the server:");
            String saddr = bufReader.readLine(); // Read the IP address from the console
            
            // Connect to the server using the provided IP address and port 8000
            Socket clientsocket = new Socket(saddr, 8000);
            System.out.println("Connecting to Server.");
            
            // Open input and output streams for communication with the server
            DataInputStream input = new DataInputStream(clientsocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientsocket.getOutputStream());
            
            // Prompt the user to enter the name of the file to request from the server
            System.out.println("Enter File Name:");
            String Name = bufReader.readLine(); // Read the file name from the console
            
            // Send the file name to the server
            output.writeUTF(Name);
            
            // Receive the contents of the file from the server
            String EchoedFile = input.readUTF();
            System.out.println(" ");
            System.out.println("Content of a File:\n\n" + EchoedFile); // Display the contents of the file to the user
            System.out.println(" ");
            
            // Close the socket once communication is complete
            clientsocket.close();
        } catch (IOException ex) {
            // Print stack trace if any IOException occurs
            ex.printStackTrace();
        }
    }
}
