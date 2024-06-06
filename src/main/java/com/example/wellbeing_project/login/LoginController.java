package com.example.wellbeing_project.login;

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

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMeCheckbox;

    @FXML
    private Button signupButton;

    // Create new user dao
    private final DBAppUserDao userDao = new DBAppUserDao();
    // Create a variable for the user preferences
    private static final Preferences preferences = Preferences.userNodeForPackage(LoginController.class);

    // Method to retrieve user preferences on launch of application
    @FXML
    public void initialize() {
        // Load saved user data
        emailField.setText(preferences.get("email", ""));
        rememberMeCheckbox.setSelected(preferences.getBoolean("remember", false));
    }

    // Method for sign in
    @FXML
    void signIn() {
        // Get email and password entered by the user
        String email = emailField.getText();
        String password = passwordField.getText();
        // Retrieve user using email input
        AppUser user = userDao.getUserByEmail(email);

        if (user != null && userDao.checkPassword(password, user.getPassword())) {
            // Login successful
            if (rememberMeCheckbox.isSelected()) {
                // Save user data if remember me is checked
                preferences.put("email", email);
                preferences.putBoolean("remember", true);
            } else {
                // Clear user data if remember me is not checked
                preferences.remove("email");
                preferences.putBoolean("remember", false);
            }
            // Set the logged-in user's ID in the session
            AppSession.setLoggedInID(user.getUserId());
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

    // Method to open the home page
    private void openHome() throws IOException {
        HomeApplication homeApplication = new HomeApplication();
        Stage stage = (Stage) emailField.getScene().getWindow();
        homeApplication.start(stage);
    }

    // Method that launches sign up page from sign up button
    @FXML
    private void openSignup() {
        // Get sign up stage
        Stage stage = (Stage) signupButton.getScene().getWindow();

        // Get openSignup method from LoginApplication class
        LoginApplication loginApp = new LoginApplication();
        loginApp.openSignup(stage);
    }
}
