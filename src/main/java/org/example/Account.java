package org.example;

/**
 * Represents a user account in the banking system.
 */
public class Account {
    private final String username;
    private final String password;
    private double balance;

    // Constructor to create a new account
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0; // New accounts start with 0 balance
    }

    // Deposit money into the account
    public synchronized void deposit(double amount) {
        balance += amount;
    }

    // Withdraw money from the account
    public synchronized boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Not enough funds
        }
        balance -= amount;
        return true;
    }

    // Get current balance
    public synchronized double getBalance() {
        return balance;
    }

    // Check if the provided password matches the account password
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
