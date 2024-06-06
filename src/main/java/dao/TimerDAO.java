package dao;

import java.sql.Connection;

import com.example.wellbeing_project.universal.AppUser;
import com.example.wellbeing_project.universal.DBConnection;
import java.sql.*;

public class TimerDAO {
    // Create database connection
    private Connection connection;
    public TimerDAO() {
        // Get the connection instance from DBConnection
        connection = DBConnection.getInstance();
    }
    public void addTimer() {
        String query = "INSERT INTO Timers (UserId, TimerName, DurationInMinutes, StartTime, EndTime) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
            statement.setString(2, AppUser.getTimerName());
            statement.setInt(3, AppUser.getDurationMinutes());
            statement.setTimestamp(4, AppUser.getStartTime());
            statement.setTimestamp(5, AppUser.getEndTime());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
