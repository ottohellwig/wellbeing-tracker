package com.example.wellbeing_project.login;

import com.example.wellbeing_project.signup.SignupApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;

// Constructor method
public class LoginApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Retrieve fmxl document
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/wellbeing_project/login-view.fxml"));
        stage.setTitle("Wellbeing Tracker");

        // Create a scene and set the root
        Scene scene = new Scene(root, SignupApplication.WIDTH, SignupApplication.HEIGHT);

        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);

        // Set scene to the stage
        stage.setScene(scene);
        stage.show();
    }
    // Variable for setting the remember me function in the login page
    private static final Preferences preferences = Preferences.userNodeForPackage(LoginApplication.class);

    // Method to open signup
    public void openSignup(Stage stage) {
        SignupApplication signup = new SignupApplication();
        try {
            signup.start(new Stage());
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Save preferences when closing application
    @Override
    public void stop() throws Exception {
        preferences.flush();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
