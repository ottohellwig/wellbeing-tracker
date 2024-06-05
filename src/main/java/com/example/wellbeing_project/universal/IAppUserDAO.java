package com.example.wellbeing_project.universal;


// Interface to use for database connection operations
public interface IAppUserDAO {
    // Add user
    void addUser(AppUser appUser);

    // Update User
    void updateUser(AppUser appUser);

    // Get user
    AppUser getUser(int userId);


}
