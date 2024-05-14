package com.example.wellbeing_project.signup;

import com.example.wellbeing_project.login.LoginApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Constructor method
public class SignupApplication extends Application {
    // Variables for height and width of some pages
    public static final double WIDTH = 400;
    public static final double HEIGHT = 500;
    
    @Override
    public void start(Stage stage) throws IOException {
        // Retrieve fxml document
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/wellbeing_project/signup-view.fxml"));
        stage.setTitle("Wellbeing Tracker");

        // Create scene
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // CSS stylesheet
        String stylesheet = SignupApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply CSS Stylesheet
        scene.getStylesheets().add(stylesheet);

        // Set scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    // Method to open login page
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
