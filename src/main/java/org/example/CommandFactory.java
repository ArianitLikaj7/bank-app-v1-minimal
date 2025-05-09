package org.example;

/**
 * Creates command objects based on user input.
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
