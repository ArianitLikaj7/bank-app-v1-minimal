package org.example.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Simple client for connecting to the Bank server.
 * Allows user to input commands like REGISTER, LOGIN, DEPOSIT, etc.
 *
 * @author ADEM ŠALJANIN
 * @version v1.0
 */
public class BankClient {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 12345);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected to Bank Server. Type a command:");

            while (true) {
                System.out.print("> ");
                String userInput = scanner.nextLine();
                out.println(userInput);

                String response = in.readLine();
                if (response == null) break;
                System.out.println("Server: " + response);
            }

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
        }
    }
}
