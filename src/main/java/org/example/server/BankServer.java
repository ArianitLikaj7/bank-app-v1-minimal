package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Bank server that listens for client connections and delegates work to ClientHandler.
 */
public class BankServer {
    public static void main(String[] args) throws IOException {
        Bank bank = new Bank();
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Bank Server started on port 12345.");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread clientThread = new Thread(new ClientHandler(clientSocket, bank));
            clientThread.start();
        }
    }
}
