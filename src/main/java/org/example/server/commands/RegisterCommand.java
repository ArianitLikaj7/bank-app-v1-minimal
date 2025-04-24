package org.example.server.commands;

import org.example.server.Bank;

public class RegisterCommand extends Command {
    @Override
    public String execute(String[] parts, Bank bank) {
        if (parts.length < 3)
            return "ERROR|Usage: REGISTER|username|password";
        return bank.register(parts[1], parts[2]);
    }
}
