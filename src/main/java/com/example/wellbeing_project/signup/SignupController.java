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

    // Method to store user inputs to variables and create a new user class
    @FXML
    private void manageSignup() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        User user = new User(name, email, password);
        if (signup(user)) {
            System.out.println("Signup successful!");
        } else {
            ErrorAlert.displayError("Sign Up Failed", "Invalid email or password.");
        }
    }

    // Create method to insert new user data into database, or display error if unable to
    private boolean signup(User user) {
        // SQL query variable
        String query = "INSERT INTO User (email, name, password) VALUES (?, ?, ?)";
        // Connect to database
        try (Connection conn = ConnectDatabase.connect();
             // Query execution steps
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Signup error: " + e.getMessage()); // See errors
            return false;
        }
    }

    @FXML
    private Button loginButton;

    // Method to launch login page from log in button
    @FXML
    private void startLogin(ActionEvent event) {
        // Get the stage from the button
        Stage stage = (Stage) loginButton.getScene().getWindow();

        // Call openLogin method from SignupApplication class
        SignupApplication signupApp = new SignupApplication();
        signupApp.openLogin(stage);
    }
}
