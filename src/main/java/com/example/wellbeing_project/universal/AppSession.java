package com.example.wellbeing_project.universal;

/**
 * Utility class for managing the application session.
 * Stores and retrieves the logged-in user's ID.
 */
public class AppSession {
    // Variable to store logged in user's ID
    public static int loggedInUserId;

    /**
     * Retrives the logged-in user's ID.
     * @return the logged-in user's ID.
     */
    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    /**
     * Sets the logged-in user's ID.
     * @param userId ID of the user who has logged in.
     */

    // Setter method for the logged in user's ID
    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }
}
