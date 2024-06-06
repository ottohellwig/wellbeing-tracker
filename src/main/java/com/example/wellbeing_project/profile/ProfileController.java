package com.example.wellbeing_project.profile;

import com.example.wellbeing_project.HomeApplication;
import com.example.wellbeing_project.login.LoginApplication;
import com.example.wellbeing_project.universal.AppUser;
import com.example.wellbeing_project.universal.DBAppUserDao;
import com.example.wellbeing_project.universal.ErrorAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.wellbeing_project.universal.AppSession;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class ProfileController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    private DBAppUserDao user;
    private AppUser userExists;

    // Method to hash password
    private String hashPassword(String originalPassword) {
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt());
    }

    public ProfileController() {
        this.user = new DBAppUserDao();
    }

    public void initialize() {
        // Get the logged in user's ID from AppSession class
        int userId = AppSession.getLoggedInID();

        // Retrieve user information from the database
        userExists = user.getUser(userId);

        // Insert logged in user information
        nameField.setText(userExists.getName());
        emailField.setText(userExists.getEmail());

        // Set save changes button to disabled before password entered
        changeButton.setDisable(true);

        // Add listener to passwordField to enable save changes button if password field isn't empty
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            changeButton.setDisable(newValue.isEmpty());
        });
    }

    // Go back to home page when back button is clicked
    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HomeApplication.WIDTH, HomeApplication.HEIGHT);
        // Load CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }

    // Method to save changes
    @FXML
    void saveChanges() {
        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Check password
        boolean correctPassword = BCrypt.checkpw(password, userExists.getPassword());

        if (correctPassword) {
            // Create updated AppUser
            AppUser updatedUserProfile = new AppUser(
                    name.isEmpty() ? userExists.getName() : name,
                    email.isEmpty() ? userExists.getEmail() : email,
                    hashPassword(password)
            );
            updatedUserProfile.setUserId(userExists.getUserId());

            // Update user details in the database
            user.updateUser(updatedUserProfile);

            // Show confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Profile updated successfully.");
            alert.showAndWait();
        } else {
            // Show error
            ErrorAlert.displayError("Error!", "Incorrect password. Please try again.");
        }


    }


}