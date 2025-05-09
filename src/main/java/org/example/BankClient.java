package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for host and port
        System.out.print("Enter server address (e.g., localhost): ");
        String host = scanner.nextLine();
        System.out.print("Enter server port: ");
        int port;

        try {
            port = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number. Exiting.");
            return;
        }

        // Try to connect to server
        try (
                Socket socket = new Socket(host, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Connected to Bank Server. Type a command:");

            while (true) {
                System.out.print("> ");
                String userInput = scanner.nextLine();

                out.println(userInput); // Send command to server

                String response = in.readLine(); // Read server response
                if (response == null) break; // If server disconnected

                System.out.println("Server: " + response);
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
