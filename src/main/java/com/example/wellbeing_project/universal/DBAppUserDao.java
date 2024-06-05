package com.example.wellbeing_project.universal;

import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

// Class implements app user interface
public class DBAppUserDao implements IAppUserDAO {
    // Create database connection
    private Connection connection;
    // Generate a salt
    private static final String SALT = BCrypt.gensalt();

    // Method to hash password
    private String hashPassword(String originalPassword) {
        return BCrypt.hashpw(originalPassword, SALT);
    }

    // Method to check password
    public boolean checkPassword(String originalPassword, String hashedPassword) {
        return BCrypt.checkpw(originalPassword, hashedPassword);
    }

    public DBAppUserDao() {
        // Get the connection instance from DBConnection
        connection = DBConnection.getInstance();
    }

    // Method to create user and add to database
    @Override
    public void addUser(AppUser appUser) {
        // SQL Query
        String sqlQuery = "INSERT INTO User (Name, Email, Password) VALUES (?, ?, ?)";
        // Retrieve variables from user's input and insert to database
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, AppUser.getName());
            statement.setString(2, AppUser.getEmail());
            // Hash password
            String hashedPassword = hashPassword(appUser.getPassword());
            statement.setString(3, hashedPassword);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update existing user profile
    @Override
    public void updateUser(AppUser appUser) {
        // Set currently logged in user's new information
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE User SET Name = ?, Email = ? WHERE UserId = ?");
            statement.setString(1, AppUser.getName());
            statement.setString(2, AppUser.getEmail());
            statement.setInt(3, AppUser.getUserId());
            statement.executeUpdate();
            connection.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve current user
    public AppUser getUser(int userId) {
        String query = "SELECT * FROM User WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Get user from their ID
            statement.setInt(1, userId);
            // Get user's information
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                AppUser user = new AppUser(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password")
                );
                user.setUserId(resultSet.getInt("UserID"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to retrieve user by email from database
    public AppUser getUserByEmail(String email) {
        String query = "SELECT * FROM User WHERE Email = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Get user from their email
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            // Get user's information
            if (resultSet.next()) {
                AppUser user = new AppUser(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password")
                );
                user.setUserId(resultSet.getInt("UserID"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
