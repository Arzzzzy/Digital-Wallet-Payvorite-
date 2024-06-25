import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

// GUI implementation of the digital wallet
class DigitalWalletGUI extends JFrame implements ActionListener {

    // This class represents the digital wallet GUI.
    private JButton cashInButton;
    private JButton sendMoneyButton;
    private JButton payBillButton;
    private JButton viewHistoryButton;
    private JLabel balanceLabel;
    private JButton userInfoButton;
    private JLabel nameLabel;
    private JLabel numberLabel;
    private JLabel birthdateLabel;

    private UserDigitalWallet userWallet;
    
    // This constructor initializes the digital wallet GUI.
    public DigitalWalletGUI() {
        userWallet = new UserDigitalWallet();
        initializeUI();
    }
    
    // This method initializes the digital wallet GUI.
    private void initializeUI() {
        setTitle("Payvorite (Digital Wallet)");
        setSize(420, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    
        // Create a panel for the buttons with a FlowLayout and gaps
        FlowLayout buttonLayout = new FlowLayout();
        buttonLayout.setHgap(20); // Set horizontal gap between buttons
        buttonLayout.setVgap(20); // Set vertical gap between buttons
        JPanel buttonPanel = new JPanel(buttonLayout);
        
        cashInButton = new JButton("Cash-In");
        sendMoneyButton = new JButton("Send Money");
        payBillButton = new JButton("Pay Bills");
        viewHistoryButton = new JButton("View History");
        balanceLabel = new JLabel("Current balance: ");
    
        balanceLabel.setForeground(Color.white); // Set the foreground color of the balance label to blue
        balanceLabel.setOpaque(true); // Set the label as opaque to allow background color
        balanceLabel.setBackground(Color.decode("#174be8")); // Set the background color of the label
    
        userWallet.setBalanceLabel(balanceLabel);
    
        cashInButton.addActionListener(this);
        sendMoneyButton.addActionListener(this);
        payBillButton.addActionListener(this);
        viewHistoryButton.addActionListener(this);
    
        Font buttonFont = new Font("Arial", Font.BOLD, 14); // Create a font object with the desired properties
        Color myColor = Color.decode("#3061E3");
        Dimension buttonSize = new Dimension(150, 50);
    
        cashInButton.setFont(buttonFont); // Set the font for the Cash-In button
        sendMoneyButton.setFont(buttonFont); // Set the font for the Send Money button
        payBillButton.setFont(buttonFont); // Set the font for the Pay Bill button
        viewHistoryButton.setFont(buttonFont); // Set the font for the View History button
    
        cashInButton.setForeground(myColor); // Set the text color of the Cash-In button to blue
        sendMoneyButton.setForeground(myColor); // Set the text color of the Send Money button to blue
        payBillButton.setForeground(myColor); // Set the text color of the Pay Bill button to blue    
        viewHistoryButton.setForeground(myColor);
    
        cashInButton.setPreferredSize(buttonSize);
        sendMoneyButton.setPreferredSize(buttonSize);
        payBillButton.setPreferredSize(buttonSize);
        viewHistoryButton.setPreferredSize(buttonSize);
    
        // Create user info button
        userInfoButton = new JButton("User Info");
        userInfoButton.addActionListener(this);
    
        // Create user info labels
        nameLabel = new JLabel();
        numberLabel = new JLabel();
        birthdateLabel = new JLabel();
        
        // User Info Set Colors
        userInfoButton.setForeground(Color.red);
        userInfoButton.setBackground(Color.CYAN);
    
        // Add components to button panel
        buttonPanel.add(cashInButton);
        buttonPanel.add(sendMoneyButton);
        buttonPanel.add(payBillButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(userInfoButton);
    
        // Set the background color
        buttonPanel.setBackground(myColor);  // Set the background color of the button panel
        getContentPane().setBackground(myColor); // Set the background color of the frame's content pane
    
        // Adjust the balance label
        int topMargin = 30;
        int bottomMargin = 0;
        int leftMargin = 20;
        int rightMargin = 20;
        Border balanceBorder = BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(topMargin, leftMargin, bottomMargin, rightMargin, myColor),
            BorderFactory.createLineBorder(Color.WHITE, 5));
    
        balanceLabel.setBorder(balanceBorder); // Set the border for the balance label
    
        // Resize the balance label
        int labelWidth = 380;
        int labelHeight = 100;
        balanceLabel.setPreferredSize(new Dimension(labelWidth, labelHeight));
    
        // Change the font style
        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 15);
        balanceLabel.setFont(labelFont);
    
        // Create a panel for the welcome message
        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome, " + userWallet.getUserName());
        welcomeLabel.setForeground(Color.WHITE); // Set the text color of the welcome label to white
        welcomeLabel.setFont(labelFont); // Apply the same font style to the welcome label
        welcomePanel.setBackground(myColor); // Set the background color of the welcome panel
        welcomePanel.add(welcomeLabel); // Add the welcome label to the welcome panel
    
        // Create a container panel for the balance label and welcome panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(myColor); // Set the background color of the top panel
        topPanel.add(welcomePanel, BorderLayout.NORTH); // Add the welcome panel to the top panel
        topPanel.add(balanceLabel, BorderLayout.CENTER); // Add the balance label to the top panel
    
        // Add the panels to the frame
        add(topPanel, BorderLayout.NORTH); // Add the top panel to the frame's north position
        add(buttonPanel, BorderLayout.CENTER); // Add the button panel to the frame's center position
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        // This button allows the user to add money to their digital wallet.
        if (e.getSource() == cashInButton) {

            // First, the user is prompted to choose a cash-in method.
            String[] options = { "Over the Counter", "Bank Account" };
            int choice = JOptionPane.showOptionDialog(DigitalWalletGUI.this, "Choose cash-in method:", "Cash-In",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            // If the user chooses to cash in over the counter, they are shown instructions on how to do so.        
            if (choice == 0) {
                // Over the Counter
                String steps = "Steps for Over the Counter Cash-In:\n\n"
                        + "Step 1: Go to CliQQ kiosk (7-Eleven) and select 'e-money'.\n"
                        + "Step 2: Select Payvorite and type in your Payvorite registered mobile number.\n"
                        + "Step 3: Type in the amount you want to Cash-in and confirm. Wait for the printed receipt.\n"
                        + "Step 4: Present receipt to the cashier and pay. A text confirmation upon successful Cash-In.";
                JOptionPane.showMessageDialog(DigitalWalletGUI.this, steps);
            } else if (choice == 1) {
                // If the user chooses to cash in via bank account, they are prompted to enter their card number, expiry date, CVV, and amount.
                JTextField cardNumberField = new JTextField("5365 9562 7611 3090");
                JTextField expiryField = new JTextField("08/24");
                JTextField cvvField = new JTextField("392");
                JTextField amountField = new JTextField();
    
                Object[] message = {
                    "Card Number:", cardNumberField,
                    "Expiry:", expiryField,
                    "CVV:", cvvField,
                    "Amount: (min. $50)", amountField
                };
                
                int option = JOptionPane.showConfirmDialog(DigitalWalletGUI.this, message, "Bank Account Cash-In", JOptionPane.OK_CANCEL_OPTION);

                // If the user clicks OK, their input is validated.
                if (option == JOptionPane.OK_OPTION) {
                    String cardNumber = cardNumberField.getText().trim();
                    String expiry = expiryField.getText().trim();
                    String cvv = cvvField.getText().trim();
                    String amountInput = amountField.getText().trim();
                    
                    if (!cardNumber.isEmpty() && !expiry.isEmpty() && !cvv.isEmpty() && !amountInput.isEmpty()) {
                        try {
                            double amount = Double.parseDouble(amountInput);
                            if (amount >= 50) { // Minimum cash in must be 50
                                JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Cash-In via Bank Account: $" + amount
                                        + " credited from card " + cardNumber,"Cash-In Successful", JOptionPane.PLAIN_MESSAGE);
                                userWallet.cashIn(amount);
                            } else {
                                JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Minimum cash-in amount is $50.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Invalid input. Please enter a valid amount.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Please enter all required information.", "Required", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        // This button allows the user to send money to another user.
        } else if (e.getSource() == sendMoneyButton) {

            // First, the user is prompted to enter the amount and recipient.
            JTextField amountField = new JTextField();
            JTextField recipientField = new JTextField("Mr.RyanAsuncion@gmail.com");
        
            Object[] message = {
                "Amount: (min. $50)", amountField,
                "Recipient:", recipientField
            };
        
            int option = JOptionPane.showConfirmDialog(DigitalWalletGUI.this, message, "Send Money", JOptionPane.OK_CANCEL_OPTION);
            
            // If the user clicks OK, their input is validated.
            if (option == JOptionPane.OK_OPTION) {
                String amountInput = amountField.getText().trim();
                String recipient = recipientField.getText().trim();
        
                if (!amountInput.isEmpty() && !recipient.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(amountInput);

                        // Check if the amount is below 50
                        if (amount < 50) {
                            JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Send amount should be at least $50.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                            return; // Exit the method without performing the transaction
                        }

                        // Check if the user has sufficient balance
                        if (userWallet.getBalance() >= amount) {
                            // Perform the transaction logic here
                            userWallet.sendMoney(amount, recipient);
        
                            // Create the transaction receipt message
                            LocalDate currentDate = LocalDate.now();
                            String timestamp = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            String receiptMessage = "Transaction Receipt\n"
                                    + "Amount: $" + amount + "\n"
                                    + "Recipient: " + recipient + "\n"
                                    + "Date: " + timestamp;
        
                            // Display the receipt
                            JOptionPane.showMessageDialog(DigitalWalletGUI.this, receiptMessage, "Successfully sent!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Insufficient balance. Please add funds to your wallet.", "Insufficient Balance", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Invalid input. Please enter a valid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Please enter an amount and recipient.", "Input Missing", JOptionPane.ERROR_MESSAGE);
                }
            }

          // This button allows the user to Paybills.
        } else if (e.getSource() == payBillButton) {

            // First, the user is prompted to enter the account number, amount, and choose what type of payment.
            JTextField accountNumberField = new JTextField("0317306482");
            JTextField amountField = new JTextField();
            JComboBox<String> billTypeComboBox = new JComboBox<>(new String[]{"PLDT", "Globe", "Smart"});
            JLabel transactionFeeLabel = new JLabel("Transaction Fee: 1%");
        
            Object[] message = {
                "Account Number:", accountNumberField,
                "Amount: (min. $10)", amountField,
                "Bill Type:", billTypeComboBox,
                transactionFeeLabel
            };
        
            int option = JOptionPane.showConfirmDialog(DigitalWalletGUI.this, message, "Pay Bill", JOptionPane.OK_CANCEL_OPTION);
        
            // If the user clicks OK, their input is validated.
            if (option == JOptionPane.OK_OPTION) {
                String accountNumberInput = accountNumberField.getText().trim();
                String amountInput = amountField.getText().trim();
                String billType = (String) billTypeComboBox.getSelectedItem();
        
                if (!accountNumberInput.isEmpty() && !amountInput.isEmpty()) {
                    try {
                        double amount = Double.parseDouble(amountInput);
        
                        // Check if the amount is at least $10
                        if (amount >= 10.0) {
                            double transactionFee = amount * 0.01; // Calculate transaction fee as 1% of the total amount
                            double totalAmount = amount + transactionFee; // Calculate the total amount including the transaction fee
        
                            // Check if the user has sufficient balance
                            if (userWallet.getBalance() >= totalAmount) {
                                userWallet.payBill(totalAmount, billType);
        
                                // Display transaction receipt with the message
                                String receiptMessage = "Your payment of $" + amount + " for " + billType + " has been processed.\n"
                                        + "Transaction Fee: $" + transactionFee + " (1%)" + "\n"
                                        + "Total Amount Paid: $" + totalAmount + "\n"
                                        + "Your payment will be posted within 24 hours.";
                                JOptionPane.showMessageDialog(DigitalWalletGUI.this, receiptMessage, "Transaction Receipt", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Insufficient balance. Please add funds to your account.", "Insufficient Balance", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Minimum bill payment amount is $10.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Invalid input. Please enter a valid amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(DigitalWalletGUI.this, "Please enter an account number, amount, and bill type.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
         
        // This button allows the user to view their transaction history.
        } else if (e.getSource() == viewHistoryButton) {
            userWallet.viewTransactionHistory();
        
        // This button displays the user's information, including their name, number, and birthday.
        } else if (e.getSource() == userInfoButton) {
            String name = userWallet.getUserName();
            String number = userWallet.getUserNumber();
            String birthday = userWallet.getBirthDate();
            
            nameLabel.setText("Name: " + name);
            numberLabel.setText("Number: " + number);
            birthdateLabel.setText("Birthdate: " + birthday);

            // Show user information as a dialog box
            JOptionPane.showMessageDialog(this, "Name: " + name + "\nNumber: " + number + "\nBirthdate: " + birthday, "User Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}