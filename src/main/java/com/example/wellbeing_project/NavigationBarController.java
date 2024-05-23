package com.example.wellbeing_project;

import com.example.wellbeing_project.signup.SignupApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.example.wellbeing_project.login.LoginApplication;
import com.example.wellbeing_project.profile.*;

import java.io.IOException;

public class NavigationBarController
{
    @FXML
    private Button homeButton;
    @FXML
    private Button reportsButton;
    @FXML
    private Button resourcesButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button profileButton;

    @FXML
    protected void onHomeButtonClick() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HomeApplication.WIDTH, HomeApplication.HEIGHT);
        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    protected void onReportsButtonClick() throws IOException {
        Stage stage = (Stage) reportsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("reports-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HomeApplication.WIDTH, HomeApplication.HEIGHT);
        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    protected void onResourcesButtonClick() throws IOException {
        Stage stage = (Stage) resourcesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("resources-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HomeApplication.WIDTH, HomeApplication.HEIGHT);
        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    protected void onLogoutButtonClick() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("logout-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HomeApplication.WIDTH, HomeApplication.HEIGHT);
        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    @FXML
    private void onFocusPeriodButtonClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/wellbeing_project/focus-period-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Set Focus Period");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onProfileButtonClick() throws IOException {
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), SignupApplication.WIDTH, SignupApplication.HEIGHT );
        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }
}
