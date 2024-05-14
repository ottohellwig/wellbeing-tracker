package com.example.wellbeing_project.login;

import com.example.wellbeing_project.signup.SignupApplication;
import com.example.wellbeing_project.HomeApplication;
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
        Scene scene = new Scene(root, 400, 500);

        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);

        // Set scene to the stage
        stage.setScene(scene);
        stage.show();
    }

    private static final Preferences prefs = Preferences.userNodeForPackage(LoginApplication.class);

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

    @Override
    public void stop() throws Exception {
        prefs.flush();
        super.stop();
    }

    // Method to close stage and open signup stage
    public void openHomeApplication(Stage stage) {
        HomeApplication signup = new HomeApplication();
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
