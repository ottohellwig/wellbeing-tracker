package com.example.wellbeing_project.login;

import com.example.wellbeing_project.signup.SignupApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Constructor method
public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Retrieve fmxl document
        Parent root = FXMLLoader.load(getClass().getResource("/login-view.fxml"));
        stage.setTitle("Wellbeing Tracker");

        // Create a scene and set the root
        Scene scene = new Scene(root, 400, 500);

        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);

        // Set scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    // Method to close stage and open signup stage
    public void openSignup(Stage stage) {
        SignupApplication signup = new SignupApplication();
        try {
            signup.start(new Stage());
            stage.close();
        } catch (Exception e) {
            e.printStackTrace(); // See errors
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
