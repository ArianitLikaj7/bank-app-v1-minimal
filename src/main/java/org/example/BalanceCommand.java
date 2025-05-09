package org.example;

/**
 * Command for checking account balance.
 */
public class BalanceCommand extends Command {
    @Override
    public String execute(String[] parts, Bank bank) {
        if (parts.length < 3) {
            return "ERROR|Usage: BALANCE|username|password";
        }
        return bank.balance(parts[1], parts[2]);
    }
}
