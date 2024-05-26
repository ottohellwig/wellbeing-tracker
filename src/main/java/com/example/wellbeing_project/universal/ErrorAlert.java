package com.example.wellbeing_project.universal;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Create a class to display errors in application

/**
 * Utility class for displaying error alerts.
 */
public class ErrorAlert {
    /**
     * Displays an error alert dialog with the specified title and message.
     *
     * @param errorTitle The title of the error alert.
     * @param message The message to display.
     */

    public static void displayError(String errorTitle, String message) {
        // Create a new Alert
        Alert alert = new Alert(AlertType.ERROR);

        // Set the title of the alert
        alert.setTitle(errorTitle);

        // Set the message
        alert.setContentText(message);

        // Show the alert
        alert.showAndWait();
    }
}
