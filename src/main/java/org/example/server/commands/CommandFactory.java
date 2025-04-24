package org.example.server.commands;

/**
 * Factory to create command instances based on input.
 */
public class CommandFactory {

    public static Command createCommand(String name) {
        switch (name.toUpperCase()) {
            case "REGISTER":
                return new RegisterCommand();
            case "LOGIN":
                return new LoginCommand();
            case "DEPOSIT":
                return new DepositCommand();
            case "WITHDRAW":
                return new WithdrawCommand();
            case "BALANCE":
                return new BalanceCommand();
            default:
                return null;
        }
    }
}
