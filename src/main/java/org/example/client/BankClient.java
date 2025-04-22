package org.example.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        System.out.println("Connected to Bank Server. Enter commands:");
        while (true) {
            System.out.print("> ");
            String userInput = scanner.nextLine();
            out.println(userInput);
            String response = in.readLine();
            System.out.println("Server: " + response);
        }
    }
}
