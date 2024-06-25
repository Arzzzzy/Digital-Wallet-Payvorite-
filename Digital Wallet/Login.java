import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.MaskFormatter;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.awt.Dimension;

public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JFormattedTextField passwordField;
    private JButton loginButton;
    private DigitalWalletGUI digitalWalletGUI;

    // This constructor initializes the login form.
    public Login() {
        initializeUI();
    }

    // This method initializes the login form.
    private void initializeUI() {
        setTitle("Payvorite (Login)");
        setSize(300, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add an image label (MADI NA IOUTPUT TAY IMAGE)
        ImageIcon imageIcon = new ImageIcon("payvorite.png");
        JLabel imageLabel = new JLabel(imageIcon);
        add(imageLabel);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Phonenumber:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        // Set the default username
        usernameField = new JTextField("09086233792");
        usernameField.setEditable(true); // Enable user input
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("4 PIN Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        try {
            MaskFormatter mpinMask = new MaskFormatter("####");
            mpinMask.setPlaceholderCharacter('_');
            passwordField = new JFormattedTextField(mpinMask);
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        loginButton.addActionListener(this);

        setLocationRelativeTo(null);
        add(panel);
    }

    public void displayLoginForm() {
        // This method displays the login form.
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // This method is called when the user clicks the login button.
        if (e.getSource() == loginButton) {
            // Simulate user authentication
            boolean isAuthenticated = authenticateUser();

            if (isAuthenticated) {
                // If the user is authenticated, generate OTP and show the OTP field.
                String otp = generateOTP();
                saveOTP(otp); // Save OTP to a text file
                showOTPField(otp);
            } else {
                // If the user is not authenticated, show an error message.
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean authenticateUser() {
        // This method simulates user authentication.
        // It checks if the username and password are valid.
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if the username and password are valid
        return "09086233792".equals(username) && "1234".equals(password);
    }

    private String generateOTP() {
        // This method generates a random 6-digit OTP.
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000); // Generate a 6-digit number
        return String.valueOf(otpNumber);
    }

    private void saveOTP(String otp) {
        // This method saves the OTP to a text file.
        try (FileWriter writer = new FileWriter("otp.txt")) {
            writer.write(otp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showOTPField(String otp) {
        // Create a new frame to display OTP field
        JFrame otpFrame = new JFrame("OTP Verification");
        otpFrame.setSize(300, 200);
        otpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5);
    
        JLabel otpSentLabel = new JLabel("Your One Time Password (OTP) has been sent.");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(otpSentLabel, constraints);
    
        JLabel otpLabel = new JLabel("OTP:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(otpLabel, constraints);
    
        JTextField otpField = new JTextField();
        otpField.setEditable(true); // Enable user input
        otpField.setPreferredSize(new Dimension(150, 25)); // Set preferred size
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(otpField, constraints);
    
        JButton verifyButton = new JButton("Verify");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(verifyButton, constraints);
    
        verifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verify the OTP here
                String enteredOTP = otpField.getText(); // Get the entered OTP
    
                if (enteredOTP.equals(otp)) {
                    // If OTP is correct, show the digital wallet GUI.
                    digitalWalletGUI = new DigitalWalletGUI();
                    digitalWalletGUI.setVisible(true);
                    setVisible(false);
                    otpFrame.dispose(); // Close the OTP frame
                } else {
                    // If OTP is incorrect, show an error message.
                    JOptionPane.showMessageDialog(otpFrame, "Invalid OTP", "Verification Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    
        otpFrame.add(panel);
        otpFrame.setLocationRelativeTo(null);
        otpFrame.setVisible(true);
    }
    

    public static void main(String[] args) {
        // This method is called when the program starts.
        SwingUtilities.invokeLater(() -> {
            // Create a new login form and display it.
            Login loginForm = new Login();
            loginForm.displayLoginForm();
        });
    }
}
