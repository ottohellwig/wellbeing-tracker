package com.example.wellbeing_project.login;

import com.example.wellbeing_project.signup.SignupApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.wellbeing_project.universal.AppUser;
import com.example.wellbeing_project.universal.DBAppUserDao;
import com.example.wellbeing_project.universal.AppSession;
import com.example.wellbeing_project.HomeApplication;
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
                showErrorAlert("Error", "Failed to open the home page.");
            }
        } else {
            // Login failed
            showErrorAlert("Error", "Invalid email or password.");
        }
    }

    // Method to open the home page
    private void openHome() throws IOException {
        HomeApplication homeApplication = new HomeApplication();
        Stage stage = (Stage) emailField.getScene().getWindow();
        homeApplication.start(stage);
    }

    // Error Alert class call
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private Button signupButton;

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
