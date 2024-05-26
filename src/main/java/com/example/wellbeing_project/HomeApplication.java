package com.example.wellbeing_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.wellbeing_project.login.LoginApplication;

import java.io.IOException;

/**
 * JavaFX Application clss for the home screen.
 */
public class HomeApplication extends Application {
    //Contrants for title, width, and height of the application window.
    public static final String TITLE = "Wellbeing Tracker";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    /**
     * Starts the JavaFX application by loading the home view FXML and setting up the scene.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while opening the FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        //Load the FXML file for the home view
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        //Creates the scene with the loaded FXML and specified width and height
        Scene scene = new Scene(fxmlLoader.load(),WIDTH,HEIGHT);
        // Load the CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();
        //Apply the CSS stylesheet
        scene.getStylesheets().add(stylesheet);
        //Set the title and scene for the stage. then display the stage
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command-line Arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}




