package org.example.common;

public class Account {
    private String username;
    private String password;
    private double balance;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
