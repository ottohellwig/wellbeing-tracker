package dao;

import java.sql.Connection;

import com.example.wellbeing_project.universal.AppUser;
import com.example.wellbeing_project.universal.DBConnection;

import java.sql.*;

public class UsageDAO {
    // Create database connection
    private Connection connection;
    public UsageDAO() {
        // Get the connection instance from DBConnection
        connection = DBConnection.getInstance();
    }
    // Add app data that user is using to database
    public void addUsage(String appName, Timestamp startTime, Timestamp endTime) {
        String query = "INSERT INTO Usage (UserId, AppName, StartTime, EndTime) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
            statement.setString(2, appName);
            statement.setTimestamp(3, startTime);
            statement.setTimestamp(4, endTime);
            statement.executeUpdate();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
