package com.example.wellbeing_project.login;

import com.example.wellbeing_project.signup.SignupApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.wellbeing_project.universal.AppUser;
import com.example.wellbeing_project.universal.DBAppUserDao;
import com.example.wellbeing_project.universal.AppSession;
import com.example.wellbeing_project.HomeApplication;
import com.example.wellbeing_project.universal.ErrorAlert;
import java.util.prefs.Preferences;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class for managing the login function for the application using users email and password
 */
public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMeCheckbox;
    // Create new user database access object, then create a variable for the user preferences
    private final DBAppUserDao userDao = new DBAppUserDao();
    private static final Preferences prefs = Preferences.userNodeForPackage(LoginController.class);

    // Method to retrieve user preferences on launch of application
    @FXML
    public void initialize() {
        // Load the saved user data
        emailField.setText(prefs.get("email", ""));
        rememberMeCheckbox.setSelected(prefs.getBoolean("remember", false));
    }

    // Method to handle signin

    /**
     * Handles the sign-in process by verifying the user credentials inputted against the database. Also manages the "Remember Me" method.
     * If sign-in is successful the application moves to the home page.
     */
    @FXML
    void signin() {
        // Get email and password entered by the user
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        // Retrieve user using email input
        AppUser user = userDao.getUserByEmail(email);
        
        if (user != null && userDao.verifyPassword(password, user.getPassword())) {
            // Login successful
            if (rememberMeCheckbox.isSelected()) {
                // Save user data if remember me is checked
                prefs.put("email", email);
                prefs.putBoolean("remember", true);
            } else {
                // Clear user data if remember me is not checked
                prefs.remove("email");
                prefs.putBoolean("remember", false);
            }
            // Set the logged-in user's ID in the session
            AppSession.setLoggedInUserId(user.getUserId());
            // Continue login process
            try {
                openHome();
            } catch (IOException e) {
                e.printStackTrace();
                ErrorAlert.displayError("Error", "Failed to open the home page.");
            }
        } else {
            // Login failed
            ErrorAlert.displayError("Error", "Invalid email or password.");
        }
    }

    /**
     * Opens the home page after the login is successful.
     *
     * @throws IOException if there is an error while opening the home page.
     */

    // Method to open the home page
    private void openHome() throws IOException {
        HomeApplication homeApplication = new HomeApplication();
        Stage stage = (Stage) emailField.getScene().getWindow();
        homeApplication.start(stage);
    }    
    
    @FXML
    private Button signupButton;

    /**
     * Method that launches the sign-up page if the sign-up button is clicked.
     */

    // Method that launches sign up page from sign up button
    @FXML
    private void startSignup() {
        // Get stage
        Stage stage = (Stage) signupButton.getScene().getWindow();

        // Call openSignup method from LoginApplication class
        LoginApplication loginApp = new LoginApplication();
        loginApp.openSignup(stage);
    }
}
