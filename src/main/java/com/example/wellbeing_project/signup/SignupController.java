package com.example.wellbeing_project.signup;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.wellbeing_project.universal.*;

import javafx.scene.control.Button;
import javafx.stage.Stage;

// Constructor method
public class SignupController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    // Method for signup
    @FXML
    void Signup() {
        // Get data from fields
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Check if any field is empty
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            ErrorAlert.displayError("Error", "Please fill in all fields.");
            return;
        }

        // Create new AppUser
        AppUser newUser = new AppUser(name, email, password);

        // Add user to the database
        DBAppUserDao userDao = new DBAppUserDao();
        try {
            userDao.addUser(newUser);
            // Show sign up confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Successfully signed up!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            // Show error alert
            ErrorAlert.displayError("Error", "Failed to add user to the database.");
        }
    }

    // Method to launch login page from login button
    @FXML
    private void openLogin() {
        // Get the login stage
        Stage stage = (Stage) loginButton.getScene().getWindow();

        // Get openLogin method from SignupApplication class
        SignupApplication signupApp = new SignupApplication();
        signupApp.openLogin(stage);
    }
}
