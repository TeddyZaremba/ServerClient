# Server Client

## Description
Morra is a game where each person (client) guesses a number 1 through 5
and reveals that number to their opponennt. If a player guesses the correct total of the two numbers guessed then they get a point. This is a java implementation of Mora where two clients connect to a server and reveal their guesses and score through the server.

## Usage
Start the server
```bash
$ cd ServerSide
$ mvn clean compile exec:java
```

Start the client
```bash
$ cd ClientSide
$ mvn clean compile exec:java
```

## Testing
Test the server
```bash
$ cd ServerSide
$ mvn test
```


Test the client
```bash
$ cd ClientSide
$ mvn test
```
