package com.example.wellbeing_project.universal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

// Class implements app user interface
public class DBAppUserDao implements IAppUserDAO {
    // Create database connection
    private Connection connection;
    // Generate a salt
    private static final String SALT = BCrypt.gensalt(); 

    public DBAppUserDao() {
        // Get the connection instance from DBConnection
        connection = DBConnection.getInstance();
    }
    // Method to create user and add to database
    @Override
    public void addUser(AppUser appUser) {
        String query = "INSERT INTO User (Name, Email, Password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, AppUser.getName());
            statement.setString(2, AppUser.getEmail());
            // Hash the password
            String hashedPassword = hashPassword(appUser.getPassword()); 
            statement.setString(3, hashedPassword);
            statement.executeUpdate();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to hash the password
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, SALT);
    }

    // Method to verify password
    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    // Method to update user entry in database
    @Override
    public void updateUser(AppUser appUser) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE User SET");
        List<Object> values = new ArrayList<>();

        if (appUser.getName() != null) {
            queryBuilder.append(" Name = ?,");
            values.add(appUser.getName());
        }
        if (appUser.getEmail() != null) {
            queryBuilder.append(" Email = ?,");
            values.add(appUser.getEmail());
        }
        if (appUser.getPassword() != null) {
            queryBuilder.append(" Password = ?,");
            values.add(appUser.getPassword());
        }

        // Remove last comma
        queryBuilder.deleteCharAt(queryBuilder.length() - 1);

        queryBuilder.append(" WHERE UserID = ?");
        String query = queryBuilder.toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (Object value : values) {
                statement.setObject(index++, value);
            }
            // Set the UserID for the WHERE 
            statement.setInt(index, appUser.getUserId());

            // Debugging
            int rowsUpdated = statement.executeUpdate(); // Check the number of rows updated
            System.out.println("Rows updated: " + rowsUpdated); // Print the number of rows updated
            connection.commit(); // Commit the transaction

            System.out.println("User with ID " + appUser.getUserId() + " updated successfully."); // Optional: Print a success message
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method from interface, not used
    @Override
    public void deleteUser(AppUser appUser) {

    }    

    // Method to retrieve current user
    public AppUser getUser(int userId) {
        String query = "SELECT * FROM User WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
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
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                AppUser user = new AppUser(
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password")
                );
                user.setUserId(resultSet.getInt("UserID")); // Set the user ID                
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method from interface, not used
    @Override
    public List<AppUser> getAllUsers() {
        return null;
    }
}
