package com.example.wellbeing_project.universal;

import java.util.List;



/**
 * Interface for accessing and managing AppUser data in the database.
 */
public interface IAppUserDAO {

    /**
     * Adds a new user to the database.
     *
     * @param appUser The AppUser object representing the user to be added.
     */
    public void addUser(AppUser appUser);


    /**
     * Updates the information of an existing user in the database.
     *
     * @param appUser The AppUser object representing the user with updated information.
     */
    public void updateUser(AppUser appUser);

    /**
     * Deletes a user from the database.
     *
     * @param appUser The AppUser object representing the user to be deleted.
     */
    public void deleteUser(AppUser appUser);

    /**
     * Retrieves a user from the database based on the provided user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The AppUser object representing the retrieved user, or null if not found.
     */
    public AppUser getUser(int userId);

    /**
     * Retrieves a list of all users from the database.
     *
     * @return A List of AppUser objects representing all users in the database.
     */
    public List<AppUser> getAllUsers();
}
