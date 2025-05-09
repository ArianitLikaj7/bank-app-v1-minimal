package org.example;

/**
 * Command for depositing money.
 */
public class DepositCommand extends Command {
    @Override
    public String execute(String[] parts, Bank bank) {
        if (parts.length < 4) {
            return "ERROR|Usage: DEPOSIT|username|password|amount";
        }
        try {
            double amount = Double.parseDouble(parts[3]);
            return bank.deposit(parts[1], parts[2], amount);
        } catch (NumberFormatException e) {
            return "ERROR|Invalid amount";
        }
    }
}