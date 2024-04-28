package com.example.signup01;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Create a class to display errors in application
public class ErrorAlert {

    public static void displayError(String errorTitle, String message) {
        // Create a new Alert
        Alert alert = new Alert(AlertType.ERROR);

        // Set the title of the alert
        alert.setTitle(errorTitle);

        // Set the content text (the message)
        alert.setContentText(message);

        // Show the alert
        alert.showAndWait();
    }
}
