package com.example.wellbeing_project.resources_page;

import com.example.wellbeing_project.login.LoginApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Constructor method
public class ResourcesApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Get fxml document
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/wellbeing_project/resources_page-view.fxml"));
        stage.setTitle("Wellbeing Tracker");

        // Create scene
        Scene scene = new Scene(root, 800, 500);

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
