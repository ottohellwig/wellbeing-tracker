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

/**
 * Controller class for managing the profile for the application
 */
public class ProfileController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Button backBtn;

    /**
     * Handles saving changes to the user's profile information.
     * Retrieves the current values from the text fields and updates in the database.
     * Then displays a confirmation message.
     */

    @FXML
    void saveChanges() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        // Get the logged in user's ID
        int userId = AppSession.getLoggedInUserId();

        // Retrrieve existing user from the database
        DBAppUserDao userDao = new DBAppUserDao();
        AppUser existingUser = userDao.getUser(userId);

        // Create updated AppUser object, ignoring empty fields
        AppUser updatedUser = new AppUser(
                name.isEmpty() ? existingUser.getName() : name,
                email.isEmpty() ? existingUser.getEmail() : email,
                password.isEmpty() ? existingUser.getPassword() : password
        );
        updatedUser.setUserId(userId);

        // Update user details in the database
        userDao.updateUser(updatedUser); 

        // Show  confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Profile updated successfully!");
        alert.showAndWait();
    }

    /**
     * Initializes the profile view with the current user's information
     * Retrieves details from the database for the current logged-in user and populates the text fields with the current user's information.
     */

    public void initialize() {
        // Get the logged in user's ID from AppSession
        int userId = AppSession.getLoggedInUserId();
        System.out.println("Logged-in user ID: " + userId); // Used for debugging, ensuring correct user retrieved

        // Retrieve user information from the database
        DBAppUserDao userDao = new DBAppUserDao();
        AppUser user = userDao.getUser(userId);
        System.out.println("Retrieved user: " + user); // Print retrieved user for debugging

        // Insert current user information
        nameField.setText(user.getName());
        emailField.setText(user.getEmail());
    }



    /**
     * Handles the action when "Back" button is clicked
     * Closes the current profile view and opens the home view
     *
     * @throws IOException if the home view FXML cannot be loaded.
     */
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
