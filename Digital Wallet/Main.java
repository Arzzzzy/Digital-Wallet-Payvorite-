import javax.swing.*;

public class Main {

    // This class is the main entry point for the application.
    public static void main(String[] args) {

        // This method is called when the program starts.
        // Use SwingUtilities.invokeLater() to ensure that the GUI is created on the
        // event dispatch thread.
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                // Create a new LoginAndLogout object.
                Login loginAndLogout = new Login();

                // Display the login form.
                loginAndLogout.displayLoginForm();
            }
        });
    }
}
