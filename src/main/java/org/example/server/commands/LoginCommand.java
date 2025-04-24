package org.example.server.commands;

import org.example.server.Bank;

public class LoginCommand extends Command {
    @Override
    public String execute(String[] parts, Bank bank) {
        if (parts.length < 3)
            return "ERROR|Usage: LOGIN|username|password";
        return bank.login(parts[1], parts[2]);
    }
}
