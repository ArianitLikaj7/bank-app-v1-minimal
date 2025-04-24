# Bank Application (OOP Version)

This project is a TCP-based client-server banking application implemented in Java using Object-Oriented Programming principles.

## Features
- Account registration and login
- Deposit, withdraw, and check balance
- Thread-safe operations using synchronized methods
- Modular design with Command Pattern
- Each client handled on a separate thread

## Technologies
- Java
- Sockets (TCP)
- Multithreading
- OOP Principles (Encapsulation, Abstraction, Polymorphism)

## Project Structure

- `org.example.client.BankClient`: Command-line client to interact with the server.
- `org.example.server.BankServer`: Entry point for server application.
- `org.example.server.Bank`: Core banking logic (register, login, etc).
- `org.example.server.commands`: Package for command handling using polymorphism.
- `org.example.common`: Shared classes like `Account` and `Message`.

## Author
**ADEM ŠALJANIN**