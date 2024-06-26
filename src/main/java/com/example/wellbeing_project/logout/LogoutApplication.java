package com.example.wellbeing_project.logout;

import com.example.wellbeing_project.login.LoginApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LogoutApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Retrieve fmxl document
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/wellbeing_project/logout-view.fxml"));
        stage.setTitle("Wellbeing Tracker");

        // Create a scene and set the root
        Scene scene = new Scene(root, 400, 300);

        // Load CSS stylesheet
        String stylesheet = LogoutApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);

        // Set scene to stage
        stage.setScene(scene);
        stage.show();
    }

    // Method that closes current stage and opens the login stage
    public void openLogin(Stage stage) {
        LoginApplication login = new LoginApplication();
        try {
            login.start(new Stage());
            stage.close();
        } catch (Exception e) {
            e.printStackTrace(); // See errors
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
