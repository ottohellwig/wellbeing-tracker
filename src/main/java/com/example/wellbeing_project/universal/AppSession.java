package com.example.wellbeing_project.universal;

public class AppSession {
    // Static variable to store the logged-in user's ID
    public static int loggedInUserId;

    // Getter method for the logged-in user's ID
    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    // Setter method for the logged-in user's ID
    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }
}
