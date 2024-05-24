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
    private Button backBtn;

    @FXML
    private Button changeBtn;

    private DBAppUserDao user;
    private AppUser existingUser;

    public ProfileController() {
        this.user = new DBAppUserDao();
    }

    public void initialize() {
        // Get the logged-in user's ID from AppSession
        int userId = AppSession.getLoggedInUserId();
        System.out.println("Logged-in user ID: " + userId);

        // Retrieve user information from the database
        existingUser = user.getUser(userId);
        System.out.println("Retrieved user: " + existingUser); // Debugging

        // Insert current user information
        nameField.setText(existingUser.getName());
        emailField.setText(existingUser.getEmail());

        // Initially disable save changes button
        changeBtn.setDisable(true);

        // Add listener to passwordField to enable button if password field is not null
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            changeBtn.setDisable(newValue.isEmpty());
        });
    }

    // Method to handle saving changes
    @FXML
    void saveChanges() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Verify password
        boolean correctPassword = BCrypt.checkpw(password, existingUser.getPassword());

        if (correctPassword) {
            // Create updated AppUser object, ignoring empty fields
            AppUser updatedUser = new AppUser(
                    name.isEmpty() ? existingUser.getName() : name,
                    email.isEmpty() ? existingUser.getEmail() : email,
                    hashPassword(password) // Hash password
            );
            updatedUser.setUserId(existingUser.getUserId());

            // Update user details in the database
            user.updateUser(updatedUser);

            // Show confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText(null);
            alert.setContentText("Profile updated successfully.");
            alert.showAndWait();
        } else {
            ErrorAlert.displayError("Error!", "Incorrect password. Please try again.");
        }


    }

    // Method to hash password
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    // Handle the action when "Back" button is clicked
    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HomeApplication.WIDTH, HomeApplication.HEIGHT);
        // Load CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }
}