package org.example.server;

import org.example.server.commands.Command;
import org.example.server.commands.CommandFactory;

import java.io.*;
import java.net.Socket;

/**
 * Handles a single client in a separate thread.
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private final Bank bank;

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
            while ((inputLine = in.readLine()) != null) {
                String[] parts = inputLine.split("\\|");
                Command command = CommandFactory.createCommand(parts[0]);
                String response = (command != null) ? command.execute(parts, bank) : "ERROR|Unknown command";
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
