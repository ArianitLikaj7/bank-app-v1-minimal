package org.example.server.commands;

import org.example.server.Bank;

/**
 * Base abstract class for all commands.
 */
public abstract class Command {
    public abstract String execute(String[] parts, Bank bank);
}
