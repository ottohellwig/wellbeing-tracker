package com.example.wellbeing_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FocusPeriodApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/wellbeing_project/focus-period-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Focus Period Settings");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
