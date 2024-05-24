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
    public void addUsage() {
        String query = "INSERT INTO Usage (UserId, StartTime, EndTime) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
            statement.setTimestamp(2, AppUser.getStartTime());
            statement.setTimestamp(3, AppUser.getEndTime());
            statement.executeUpdate();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
