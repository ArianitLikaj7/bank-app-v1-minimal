package org.example;

import java.io.*;
import java.net.Socket;

/**
 * Handles communication with a single client.
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Bank bank;

    // Constructor to pass the socket and shared bank object
    public ClientHandler(Socket socket, Bank bank) {
        this.socket = socket;
        this.bank = bank;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String inputLine;

            // Read commands from client
            while ((inputLine = in.readLine()) != null) {
                String[] parts = inputLine.split("\\|"); // Split input using |
                Command command = CommandFactory.createCommand(parts[0]);

                if (command != null) {
                    String response = command.execute(parts, bank);
                    out.println(response);
                } else {
                    out.println("ERROR|Unknown command");
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + e.getMessage());
        }
    }
}
