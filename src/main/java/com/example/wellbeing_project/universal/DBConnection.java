package com.example.wellbeing_project.universal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class to connect application to database
public class DBConnection {
    // Path to database
    private static final String path = "jdbc:sqlite:Wellness_Tracker.db/";
    private static Connection connection;

    // Get instance to use
    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(path);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
