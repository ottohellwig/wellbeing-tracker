package com.example.wellbeing_project.universal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;



/**
 * Data Access Object (DAO) class for managing AppUser data in the database
 * Implements the IAppUserDAO interface.
 */
public class DBAppUserDao implements IAppUserDAO {
    // Create database connection
    private Connection connection;
    // Generate a salt
    private static final String SALT = BCrypt.gensalt();

    /**
     * Constructor for the DBAppUserDao Class.
     * Initializes the database connection.
     */

    public DBAppUserDao() {
        // Get the connection instance from DBConnection
        connection = DBConnection.getInstance();
    }

    /**
     * Adds a new user to the database.
     * @param appUser the AppUser object to be added to the database.
     */

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

    /**
     * Hashes the provided password using BCrypt.
     * @param plainTextPassword The plain text password to be hashed.
     * @return The hashed password.
     */

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, SALT);
    }

    /**
     * Verifies whether the proved plain text password match the hashed password.
     * @param plainTextPassword The plain text password.
     * @param hashedPassword The hashed password.
     * @return True if the passwords match, false otherwise.
     */

    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    /**
     * Updates the user entry in the database.
     * @param appUser The AppUser object with updated information.
     */

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

    /**
     * Retrieves the user with the specified ID from the database.
     *
     * @param userId The ID of the user to retrieve
     * @return The AppUser object representing the retrieved user.Return null if user is not found.
     */

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

    /**
     * Retrieves the user with the specified email from the database.
     *
     * @param email The email of the user to retrieve.
     * @return The AppUser object representing the retrieved user. Return null if user is not found.
     */


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
