package com.example.wellbeing_project.universal;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class DBAppUserDao implements IAppUserDAO {
    private Connection connection;
    private static final String SALT = BCrypt.gensalt(); // Generate a salt

    public DBAppUserDao() {
        // Get the connection instance from DBConnection
        connection = DBConnection.getInstance();
    }
    @Override
    public void addUser(AppUser appUser) {
        String query = "INSERT INTO User (Name, Email, Password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, AppUser.getName());
            statement.setString(2, AppUser.getEmail());
            String hashedPassword = hashPassword(appUser.getPassword()); // Hash the password
            statement.setString(3, hashedPassword);
            statement.executeUpdate();
            connection.commit(); // Commit the transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to hash the password
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, SALT);
    }

    // Method to verify a password
    public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

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

        // Remove the last comma
        queryBuilder.deleteCharAt(queryBuilder.length() - 1);

        queryBuilder.append(" WHERE UserID = ?");
        String query = queryBuilder.toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int index = 1;
            for (Object value : values) {
                statement.setObject(index++, value);
            }
            // Set the UserID for the WHERE clause
            statement.setInt(index, appUser.getUserId());

            int rowsUpdated = statement.executeUpdate(); // Check the number of rows updated
            System.out.println("Rows updated: " + rowsUpdated); // Print the number of rows updated
            connection.commit(); // Commit the transaction

            System.out.println("User with ID " + appUser.getUserId() + " updated successfully."); // Optional: Print a success message
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(AppUser appUser) {

    }

    public AppUser getUserByEmailAndPassword(String email, String password) {
        return null;
    }

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
                // You may set other properties here if needed
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to retrieve user by email from the database
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
                // You may set other properties here if needed
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return null;
    }
}
