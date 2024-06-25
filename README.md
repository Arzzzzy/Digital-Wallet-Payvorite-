# Digital-Wallet-Payvorite-
This project implements a simple digital wallet system using Java. The digital wallet allows users to perform various financial transactions such as cashing in money, sending money to other users, and paying bills. Additionally, users can view their transaction history.The wallet maintains a balance for the user and records each transaction with details such as amount, description, and date.

## Features
1. Cash In: Add money to the wallet balance.
2. Send Money: Transfer money to another user.
3. Pay Bills: Pay various types of bills directly from the wallet.
4. View Transaction History: Display a detailed list of all transactions performed by the user.

## Classes and Structure
###DigitalWallet (Abstract Class)
*This class defines the core properties and methods for the digital wallet:

## Properties:
*balance: The current balance in the wallet.
*transactionHistory: A list to keep track of all transactions.
*userName, userNumber, birthDate, refNumber: User details.

## Methods:
* cashIn(double amount): Abstract method to add money to the wallet.
* sendMoney(double amount, String recipient): Abstract method to send money to another user.
* payBill(double amount, String billType): Abstract method to pay a bill.
* viewTransactionHistory(): Abstract method to view the transaction history.

## Transaction (Inner Class):
* Represents a transaction with properties for amount, description, and date.
* Overrides toString() method to provide a string representation of the transaction.

## UserDigitalWallet (Concrete Class)
* This class extends the DigitalWallet class and provides concrete implementations for the abstract methods:

## Constructor:
* Initializes the wallet with a default balance and user details.
* Formats the balance display using DecimalFormat.

## Methods:
* cashIn(double amount): Adds the specified amount to the wallet balance and updates the transaction history.
* sendMoney(double amount, String recipient): Transfers the specified amount to the recipient if the balance is sufficient, otherwise displays an error message.
* payBill(double amount, String billType): Pays the specified bill amount if the balance is sufficient, otherwise displays an error message.
* viewTransactionHistory(): Displays the transaction history in a message dialog.

## Requirements
* Java Development Kit (JDK) 8 or higher
* Swing library for GUI components (included in JDK)

### Author
arzadonchristianandrei@gmail.com
