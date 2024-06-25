import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import java.text.DecimalFormat;


// Abstract class for the digital wallet
abstract class DigitalWallet {

    // Properties
    protected double balance;
    protected ArrayList<Transaction> transactionHistory;
    protected String userName;
    protected String userNumber;
    protected String birthDate;
    protected String refNumber;
    
    // Constructor
    public DigitalWallet() {
        this.balance = 1000.0;
        this.transactionHistory = new ArrayList<>();
        this.userName = "Christian Andrei";
        this.userNumber = "09086233792";
        this.birthDate = "Dec 28, 2002";
    }
    // Getters
    public String getUserName() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }


    // Abstract methods to be implemented by subclasses
    abstract void cashIn(double amount);
    abstract void sendMoney(double amount, String recipient);
    abstract void payBill(double amount, String billType);
    abstract void viewTransactionHistory();

    // Inner class to represent a transaction
    protected class Transaction {
        private double amount;
        private String description;
        private Date date;
        
        // Constructor
        public Transaction(double amount, String description) {
            this.amount = amount;
            this.description = description;
            this.date = new Date();
        }
        
        // Getters
        public double getAmount() {
            return amount;
        }

        public String getDescription() {
            return description;
        }

        public Date getDate() {
            return date;
        }
        
        // Override toString() method to return a string representation of the transaction
        @Override
        public String toString() {
            return "[" + date.toString() + "] " + description + ": $" + amount;
        }
    }
}

// Implementation of DigitalWallet for a specific user
class UserDigitalWallet extends DigitalWallet {
    private JLabel balanceLabel;
    private DecimalFormat decimalFormat;
    
    // Constructor
    public UserDigitalWallet() {
        decimalFormat = new DecimalFormat("0.00");
    }

     // Getters
    public double getBalance() {
        return balance;
    }
    // Setters
    public void setBalanceLabel(JLabel label) {
        this.balanceLabel = label;
        balanceLabel.setText("Current balance: $" + decimalFormat.format(balance));
    }
    
    // Override abstract methods
    @Override
    void cashIn(double amount) {
        // Add the cash-in amount to the balance
        balance += amount;
         // Add a new transaction to the history
        transactionHistory.add(new Transaction(amount, "Cash-In"));
        // Update the balance label
        balanceLabel.setText("Current balance: $" + decimalFormat.format(balance));
    }

    @Override
    void sendMoney(double amount, String recipient) {
         // Check if the balance is sufficient for the transaction
        if (balance >= amount) {
            // Deduct the sent amount from the balance
            balance -= amount;
             // Add a new transaction to the history
            transactionHistory.add(new Transaction(amount, "Sent to " + recipient));
            // Update the balance label
            balanceLabel.setText("Current balance: $" + decimalFormat.format(balance));
        } else {
             // Display an error message for insufficient balance
            JOptionPane.showMessageDialog(null, "Insufficient balance. Transaction failed.");
        }
    }

    @Override
    void payBill(double amount, String billType) {
         // Check if the balance is sufficient for the bill payment
        if (balance >= amount) {
            // Deduct the bill payment amount from the balance
            balance -= amount;
            // Add a new transaction to the history
            transactionHistory.add(new Transaction(amount, "Bill payment for " + billType));
            // Update the balance label
            balanceLabel.setText("Current balance: $" + decimalFormat.format(balance));
        } else {
            // Display an error message for insufficient balance
            JOptionPane.showMessageDialog(null, "Insufficient balance. Transaction failed.");
        }
    }

    @Override
    void viewTransactionHistory() {
        // Build a string with the transaction history
        StringBuilder transactionHistoryStr = new StringBuilder("Transaction History:\n");
        for (Transaction transaction : transactionHistory) {
            transactionHistoryStr.append(transaction.toString()).append("\n");
        }
        // Display the transaction history in a message dialog
        JOptionPane.showMessageDialog(null, transactionHistoryStr.toString(), "View History", JOptionPane.PLAIN_MESSAGE);
    }
}


