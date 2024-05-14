package com.example.wellbeing_project.signup;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.example.wellbeing_project.universal.*;

import javafx.event.ActionEvent;
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

    // Method to manage signup
    @FXML
    void manageSignup() {
        // Get data from the form fields
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        // Check if any field is empty
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            ErrorAlert.displayError("Error", "Please fill in all fields.");
            return;
        }

        // Create new AppUser object
        AppUser newUser = new AppUser(name, email, password);

        // Add user to the database
        DBAppUserDao userDao = new DBAppUserDao();
        try {
            userDao.addUser(newUser);
            // Show success message
            System.out.println("User added successfully!");            
        } catch (Exception e) {
            e.printStackTrace();
            ErrorAlert.displayError("Error", "Failed to add user to the database.");
        }
    }

    // Method to open login page
    @FXML
    void startLogin() {
        SignupApplication signupApplication = new SignupApplication();
        Stage stage = (Stage) nameField.getScene().getWindow(); // get the current stage
        signupApplication.openLogin(stage);
    }

    @FXML
    private Button loginButton;

    // Method to launch login page from login button
    @FXML
    private void startLogin(ActionEvent event) {
        // Get the stage from the button
        Stage stage = (Stage) loginButton.getScene().getWindow();

        // Call openLogin method from SignupApplication class
        SignupApplication signupApp = new SignupApplication();
        signupApp.openLogin(stage);
    }
}
