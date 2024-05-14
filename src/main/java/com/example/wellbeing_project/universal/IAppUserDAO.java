package com.example.wellbeing_project.universal;

import java.util.List;

/**
 * Interface for the Contact Data Access Object that handles
 * the CRUD operations for the Contact class with the database.
 */
public interface IAppUserDAO {
    /**
     * Adds a new contact to the database.
     * @param appUser The contact to add.
     */
    public void addUser(AppUser appUser);
    /**
     * Updates an existing appUser in the database.
     * @param appUser The appUser to update.
     */
    public void updateUser(AppUser appUser);
    /**
     * Deletes a contact from the database.
     * @param appUser The contact to delete.
     */
    public void deleteUser(AppUser appUser);
    /**
     * Retrieves a contact from the database.
     * @param userId The id of the contact to retrieve.
     * @return The contact with the given id, or null if not found.
     */
    public AppUser getUser(int userId);
    /**
     * Retrieves all contacts from the database.
     * @return A list of all contacts in the database.
     */
    public List<AppUser> getAllUsers();
}
