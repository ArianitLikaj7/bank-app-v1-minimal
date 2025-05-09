package org.example;

/**
 * Command for withdrawing money.
 */
public class WithdrawCommand extends Command {
    @Override
    public String execute(String[] parts, Bank bank) {
        if (parts.length < 4) {
            return "ERROR|Usage: WITHDRAW|username|password|amount";
        }
        try {
            double amount = Double.parseDouble(parts[3]);
            return bank.withdraw(parts[1], parts[2], amount);
        } catch (NumberFormatException e) {
            return "ERROR|Invalid amount";
        }
    }
}
