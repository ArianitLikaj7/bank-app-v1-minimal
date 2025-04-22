package org.example.server;


import org.example.common.Account;
import org.example.common.Message;

import java.io.*;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

public class BankServer {
    private static ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Bank Server started on port 12345.");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
    }

    private static void handleClient(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] parts = inputLine.split("\\|");
                String command = parts[0];

                switch (command) {
                    case "REGISTER":
                        out.println(handleRegister(parts));
                        break;
                    case "LOGIN":
                        out.println(handleLogin(parts));
                        break;
                    case "DEPOSIT":
                        out.println(handleDeposit(parts));
                        break;
                    case "WITHDRAW":
                        out.println(handleWithdraw(parts));
                        break;
                    case "BALANCE":
                        out.println(handleBalance(parts));
                        break;
                    default:
                        out.println(Message.error("Unknown command"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String handleRegister(String[] parts) {
        if (parts.length < 3) return Message.error("Usage: REGISTER|username|password");
        String user = parts[1], pass = parts[2];
        if (accounts.containsKey(user)) return Message.error("Account already exists");
        accounts.put(user, new Account(user, pass));
        return Message.success("Account registered");
    }

    private static String handleLogin(String[] parts) {
        if (parts.length < 3) return Message.error("Usage: LOGIN|username|password");
        String user = parts[1], pass = parts[2];
        Account acc = accounts.get(user);
        return (acc != null && acc.authenticate(pass)) ? Message.success("Login successful") : Message.error("Invalid credentials");
    }

    private static String handleDeposit(String[] parts) {
        if (parts.length < 4) return Message.error("Usage: DEPOSIT|username|password|amount");
        String user = parts[1], pass = parts[2];
        double amount = Double.parseDouble(parts[3]);
        Account acc = accounts.get(user);
        if (acc == null || !acc.authenticate(pass)) return Message.error("Invalid credentials");
        acc.deposit(amount);
        return Message.success("Deposit successful. New balance: $" + acc.getBalance());
    }

    private static String handleWithdraw(String[] parts) {
        if (parts.length < 4) return Message.error("Usage: WITHDRAW|username|password|amount");
        String user = parts[1], pass = parts[2];
        double amount = Double.parseDouble(parts[3]);
        Account acc = accounts.get(user);
        if (acc == null || !acc.authenticate(pass)) return Message.error("Invalid credentials");
        if (!acc.withdraw(amount)) return Message.error("Insufficient funds");
        return Message.success("Withdrawal successful. New balance: $" + acc.getBalance());
    }

    private static String handleBalance(String[] parts) {
        if (parts.length < 3) return Message.error("Usage: BALANCE|username|password");
        String user = parts[1], pass = parts[2];
        Account acc = accounts.get(user);
        if (acc == null || !acc.authenticate(pass)) return Message.error("Invalid credentials");
        return Message.success("Current balance: $" + acc.getBalance());
    }
}
