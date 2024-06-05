package com.example.wellbeing_project.logout;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.*;


public class LogoutController {

    @FXML
    private Button returnButton;

    // Method that launches login page from login button
    @FXML
    private void startLogin() {
        // Get stage
        Stage stage = (Stage) returnButton.getScene().getWindow();

        // Get openLogin method from LogoutApplication class
        LogoutApplication logoutApp = new LogoutApplication();
        logoutApp.openLogin(stage);
    }
}
