package org.example;

/**
 * Base class for all commands.
 */
public abstract class Command {
    public abstract String execute(String[] parts, Bank bank);
}
