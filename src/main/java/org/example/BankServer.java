package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BankServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter server port number: ");

        int port;
        try {
            port = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid port number. Exiting.");
            return;
        }

        Bank bank = new Bank();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Bank Server started on port " + port);

            // Server will keep running and accept clients continuously
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Handle each client in a new thread
                Thread clientThread = new Thread(new ClientHandler(clientSocket, bank));
                clientThread.start();
            }

        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }
}