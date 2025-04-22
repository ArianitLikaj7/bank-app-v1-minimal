
# Bank Application – Version 1 (Rapid Prototype)

## Description
This is a simple Java-based client-server banking application that supports:
- Account registration
- Login authentication
- Deposits and withdrawals
- Balance checking

## Project Structure
```
bank-app/
└── src/
    └── main/
        └── java/
            └── org/
                └── example/
                    ├── client/
                    │   └── BankClient.java
                    ├── server/
                    │   └── BankServer.java
                    └── common/
                        ├── Account.java
                        └── Message.java
```

## How to Compile

Open Command Prompt and run from inside the `src/main/java` directory:

```bash
cd src\main\java
javac org\example\server\BankServer.java org\example\client\BankClient.java org\example\common\Account.java org\example\common\Message.java
```

## How to Run

### Start the server (Terminal 1):
```bash
java org.example.server.BankServer
```

### Start the client (Terminal 2):
```bash
java org.example.client.BankClient
```

## Commands to Test

Inside the client terminal, enter the following test cases:

```text
REGISTER|john|1234
LOGIN|john|1234
DEPOSIT|john|1234|100
WITHDRAW|john|1234|50
BALANCE|john|1234
```

Optional error test:
```text
HELLO|test
```

## Notes
- This version supports one client at a time.
- Data is stored in-memory (not persisted to disk).
- All communication happens via TCP socket using text commands.
