package com.example.wellbeing_project.signup;

import com.example.wellbeing_project.login.LoginApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Constructor method

/**
 * Application class to manage the sign-up view.
 */
public class SignupApplication extends Application {
    // Variables for height and width of some pages
    public static final double WIDTH = 400;
    public static final double HEIGHT = 500;

    /**
     * Starts the sign-up application by setting up the primary stage with the sign-up view.
     * @param stage the primary stage for the sign-up application.
     * @throws IOException if the FXML file cannot be loaded.
     */
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

    /**
     * Opens the login page by creating a new instance of LoginApplication and starting it.
     * Closes the current stage.
     * @param stage current stage to be closed.
     */
    public void openLogin(Stage stage) {
        LoginApplication login = new LoginApplication();
        try {
            login.start(new Stage());
            stage.close();
        } catch (Exception e) {
            e.printStackTrace(); // See errors
        }
    }

    /**
     * Main method used to launch the signup application.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}
