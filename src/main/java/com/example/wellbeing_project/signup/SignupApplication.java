package com.example.signup01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Create
public class SignupApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup-view.fxml"));
        stage.setTitle("Wellbeing Tracker");

        // Create scene
        Scene scene = new Scene(root, 400, 500);

        // CSS stylesheet
        String stylesheet = SignupApplication.class.getResource("stylesheet2.css").toExternalForm();

        // Apply CSS Stylesheet
        scene.getStylesheets().add(stylesheet);

        // Set the scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}