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
        String query = "INSERT INTO Usage (UserId, StartTime, EndTime, Application) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
            statement.setTimestamp(2, startTime);
            statement.setTimestamp(3, endTime);
            statement.setString(4, appName);
            statement.executeUpdate();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
