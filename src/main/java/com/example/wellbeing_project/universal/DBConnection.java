package com.example.wellbeing_project.universal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;



/**
 * Class for managing connection to the database.
 */
public class DBConnection {
    private static final String URL = "jdbc:sqlite:Wellness_Tracker.db/";
    private static Connection connection;

    // Get instance to use

    /**
     * Returns the database connection instance.
     *
     * @return The database Connection.
     * @throws RuntimeException if there's an error connecting to the database.
     */
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
