package com.example.wellbeing_project.universal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

// Class to connect application to database
public class DBConnection {
    private static final String URL = "jdbc:sqlite:Wellness_Tracker.db/";
    private static Connection connection;

    // Get instance to use
    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to connect to the database.", e);
            }
        }
        return connection;
    }

}
