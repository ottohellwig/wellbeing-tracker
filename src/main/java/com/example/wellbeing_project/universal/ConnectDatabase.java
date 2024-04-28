package com.example.wellbeing_project.universal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    // Create variable that holds URL to SQLite database
    private static final String URL = "jdbc:sqlite:../Wellness_Tracker.db/";

    // Create method to connect application to database
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Connection successful");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection failed - Reason: " + e.getMessage());
            return null;
        }
    }
}
