package com.example.wellbeing_project.profile;

import com.example.wellbeing_project.signup.SignupApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Retrieve fxml document
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/wellbeing_project/profile-view.fxml"));
        stage.setTitle("Wellbeing Tracker");

        // Create scene
        Scene scene = new Scene(root, SignupApplication.WIDTH, SignupApplication.HEIGHT);

        // CSS stylesheet
        String stylesheet = com.example.wellbeing_project.signup.SignupApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply CSS Stylesheet
        scene.getStylesheets().add(stylesheet);

        // Set scene to the stage
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

