package org.example.server;

import org.example.common.Account;
import org.example.common.Message;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Central banking class for managing accounts and transactions.
 */
public class Bank {
    private ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    public String register(String username, String password) {
        if (accounts.containsKey(username))
            return Message.error("Account already exists");
        accounts.put(username, new Account(username, password));
        return Message.success("Account registered");
    }

    public String login(String username, String password) {
        Account acc = accounts.get(username);
        return (acc != null && acc.authenticate(password)) ?
                Message.success("Login successful") : Message.error("Invalid credentials");
    }

    public String deposit(String username, String password, double amount) {
        Account acc = accounts.get(username);
        if (acc == null || !acc.authenticate(password)) return Message.error("Invalid credentials");
        acc.deposit(amount);
        return Message.success("Deposit successful. New balance: $" + acc.getBalance());
    }

    public String withdraw(String username, String password, double amount) {
        Account acc = accounts.get(username);
        if (acc == null || !acc.authenticate(password)) return Message.error("Invalid credentials");
        if (!acc.withdraw(amount)) return Message.error("Insufficient funds");
        return Message.success("Withdrawal successful. New balance: $" + acc.getBalance());
    }

    public String balance(String username, String password) {
        Account acc = accounts.get(username);
        if (acc == null || !acc.authenticate(password)) return Message.error("Invalid credentials");
        return Message.success("Current balance: $" + acc.getBalance());
    }
}
