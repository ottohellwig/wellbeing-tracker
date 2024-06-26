package com.example.wellbeing_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.wellbeing_project.login.LoginApplication;

import java.io.IOException;

public class HomeApplication extends Application {
    public static final String TITLE = "Wellbeing Tracker";
    public static final int WIDTH = 890;
    public static final int HEIGHT = 500;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),WIDTH,HEIGHT);
        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}









