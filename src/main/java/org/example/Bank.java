package org.example;

import java.util.HashMap;

/**
 * This class manages all user accounts and handles actions like register, login, deposit, withdraw, and balance check.
 */
public class Bank {

    // Stores all accounts, mapped by username
    private final HashMap<String, Account> accounts = new HashMap<>();

    // Register a new account
    public synchronized String register(String username, String password) {
        if (accounts.containsKey(username)) {
            return Message.error("Account already exists");
        }
        accounts.put(username, new Account(username, password));
        return Message.success("Account registered successfully");
    }

    // Log in to an existing account
    public synchronized String login(String username, String password) {
        Account account = accounts.get(username);
        if (account != null && account.authenticate(password)) {
            return Message.success("Login successful");
        }
        return Message.error("Invalid username or password");
    }

    // Deposit money into account
    public synchronized String deposit(String username, String password, double amount) {
        Account account = accounts.get(username);
        if (account == null || !account.authenticate(password)) {
            return Message.error("Invalid username or password");
        }
        account.deposit(amount);
        return Message.success("Deposit successful. New balance: $" + account.getBalance());
    }

    // Withdraw money from account
    public synchronized String withdraw(String username, String password, double amount) {
        Account account = accounts.get(username);
        if (account == null || !account.authenticate(password)) {
            return Message.error("Invalid username or password");
        }
        if (!account.withdraw(amount)) {
            return Message.error("Insufficient funds");
        }
        return Message.success("Withdrawal successful. New balance: $" + account.getBalance());
    }

    // Check account balance
    public synchronized String balance(String username, String password) {
        Account account = accounts.get(username);
        if (account == null || !account.authenticate(password)) {
            return Message.error("Invalid username or password");
        }
        return Message.success("Current balance: $" + account.getBalance());
    }
}
