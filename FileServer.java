package Javaprgm;

import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        new FileServer(); // Create a new instance of the FileServer class
    }

    public FileServer() {
        try {
            // Create a server socket on port 8000
            ServerSocket serversocket = new ServerSocket(8000);
            System.out.println("Server Started ");
            System.out.println(" ");
            
            // Wait for a client connection
            Socket socket = serversocket.accept();
            
            // Open streams for communication with the client
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            // Read the file name sent by the client
            String str = input.readUTF();
            System.out.println("Requested File Name:" + str);
            System.out.println(" ");

            String everything;
            try {
                // Attempt to open the requested file for reading
                InputStream in = new FileInputStream(str);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder out = new StringBuilder();
                String line;
                
                System.out.println("Reading Contents of the File...");
                System.out.println(" ");
                
                // Read each line of the file and append it to the StringBuilder
                while ((line = reader.readLine()) != null) {
                    out.append(line + "\n");
                }
                
                // Convert the contents of the StringBuilder to a String
                everything = out.toString();
                System.out.println("File Contents sent to client...");
                System.out.println(" ");
            } catch (Exception ex) {
                // If the file is not found, set the contents to a message indicating so
                everything = "File Not Found!";
            }
            
            // Send the contents of the file (or error message) back to the client
            output.writeUTF(everything);
        } catch (Exception ex) {
            // Print stack trace if any exception occurs
            ex.printStackTrace();
        }
    }
}
