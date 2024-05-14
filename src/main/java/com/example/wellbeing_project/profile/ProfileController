package com.example.wellbeing_project.profile;

import com.example.wellbeing_project.HomeApplication;
import com.example.wellbeing_project.login.LoginApplication;
import com.example.wellbeing_project.universal.AppUser;
import com.example.wellbeing_project.universal.DBAppUserDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.example.wellbeing_project.universal.AppSession;
import javafx.stage.Stage;

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

    // Method to handle saving changes
    @FXML
    void saveChanges() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Get the logged-in user's ID
        int userId = AppSession.getLoggedInUserId();

        // Fetch the existing user from the database
        DBAppUserDao userDao = new DBAppUserDao();
        AppUser existingUser = userDao.getUser(userId);

        // Create an updated AppUser object with non-null fields
        AppUser updatedUser = new AppUser(
                name.isEmpty() ? existingUser.getName() : name,
                email.isEmpty() ? existingUser.getEmail() : email,
                password.isEmpty() ? existingUser.getPassword() : password
        );
        updatedUser.setUserId(userId);

        // Update the user in the database
        userDao.updateUser(updatedUser); // Update user information

        // Show a confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Profile updated successfully!");
        alert.showAndWait();
    }

    public void initialize() {
        // Get the logged-in user's ID from AppSession
        int userId = AppSession.getLoggedInUserId();
        System.out.println("Logged-in user ID: " + userId); // Print the logged-in user's ID for debugging

        // Fetch user information from the database
        DBAppUserDao userDao = new DBAppUserDao();
        AppUser user = userDao.getUser(userId);
        System.out.println("Retrieved user: " + user); // Print the retrieved user for debugging

        // Populate fields with user information
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
    }

    // Handle the action when "Back" button is clicked
    @FXML
    private void goBack() throws IOException {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HomeApplication.WIDTH, HomeApplication.HEIGHT);
        // Load  CSS stylesheet
        String stylesheet = LoginApplication.class.getResource("/com/example/wellbeing_project/stylesheet2.css").toExternalForm();

        // Apply stylesheet to scene
        scene.getStylesheets().add(stylesheet);
        stage.setScene(scene);
    }
}
