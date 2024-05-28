package com.example.wellbeing_project.universal;

import java.util.List;

// Interface to use for database connection operations
public interface IAppUserDAO {
    // Add user
    public void addUser(AppUser appUser);
    // Update User
    public void updateUser(AppUser appUser);
    // Delete user
    public void deleteUser(AppUser appUser);
    // Get user
    public AppUser getUser(int userId);

    // List all users
    public List<AppUser> getAllUsers();
}
