package com.example.wellbeing_project.logout;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;


public class LogoutController {

    @FXML
    private Button returnButton;

    // Method that launches login page from login button
    @FXML
    private void startLogin(ActionEvent event) {
        // Get stage
        Stage stage = (Stage) returnButton.getScene().getWindow();

        // Call openLogin method from LogoutApplication class
        LogoutApplication logoutApp = new LogoutApplication();
        logoutApp.openLogin(stage);
    }
}
