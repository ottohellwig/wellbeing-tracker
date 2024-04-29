package com.example.wellbeing_project.login;

import com.example.wellbeing_project.signup.SignupApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.example.wellbeing_project.universal.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    // Sign in method that retrieves user input if correct, otherwise displays error
    @FXML
    private void signin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (login(email, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed!");
            // Show error message
            showErrorAlert("Login Failed", "Invalid email or password.");
        }
    }

    // Method that determines whether user is registered in database
    private boolean login(String email, String password) {
        // SQL query
        String query = "SELECT * FROM User WHERE email = ?";
        // Connect to database and determine whether email is registered
        try (Connection conn = ConnectDatabase.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            // If executed, email exists within the database
            if (rs.next()) {
                // Variable assigned to password related to email
                String dbPassword = rs.getString("password");
                // Check if the entered password matches the one in the database
                if (dbPassword.equals(password)) {
                    return true; // Login successful
                }
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage()); // See errors
        }
        return false; // Login failed
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
    private void startSignup(ActionEvent event) {
        // Get stage
        Stage stage = (Stage) signupButton.getScene().getWindow();

        // Call openSignup method from LoginApplication class
        LoginApplication loginApp = new LoginApplication();
        loginApp.openSignup(stage);
    }
}
