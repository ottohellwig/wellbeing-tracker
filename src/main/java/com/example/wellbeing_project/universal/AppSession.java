package com.example.wellbeing_project.universal;

public class AppSession {
    // Variable for logged in user's ID
    public static int loggedInID;

    // Method to get logged in user's ID
    public static int getLoggedInID() {
        return loggedInID;
    }

    // Method to set logged in user's ID
    public static void setLoggedInID(int userId) {
        loggedInID = userId;
    }
}
