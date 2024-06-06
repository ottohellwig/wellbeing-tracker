package dao;
import java.sql.Connection;

import com.example.wellbeing_project.ReportsController;
import com.example.wellbeing_project.universal.AppUser;
import com.example.wellbeing_project.universal.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.*;

public class ReportDAO {

    private Connection connection;

    public ReportDAO() {
        connection = DBConnection.getInstance();
    }

    public XYChart.Series<String, Number> getDailyReport() {
        XYChart.Series<String, Number> dailySeries = new XYChart.Series<>();
        String query = "SELECT " +
                "CASE " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '00' AND '00' THEN '12am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '01' AND '01' THEN '1am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '02' AND '02' THEN '2am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '03' AND '03' THEN '3am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '04' AND '04' THEN '4am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '05' AND '05' THEN '5am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '06' AND '06' THEN '6am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '07' AND '07' THEN '7am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '08' AND '08' THEN '8am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '09' AND '09' THEN '9am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '10' AND '10' THEN '10am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '11' AND '11' THEN '11am' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '12' AND '12' THEN '12pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '13' AND '13' THEN '1pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '14' AND '14' THEN '2pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '15' AND '15' THEN '3pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '16' AND '16' THEN '4pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '17' AND '17' THEN '5pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '18' AND '18' THEN '6pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '19' AND '19' THEN '7pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '20' AND '20' THEN '8pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '21' AND '21' THEN '9pm' " +
                "WHEN strftime('%H', StartTime / 1000, 'unixepoch') BETWEEN '22' AND '22' THEN '10pm' " +
                "ELSE '11pm' " +
                "END AS Hour, " +
                "SUM((EndTime / 1000) - (StartTime / 1000)) / 60 AS Duration " +
                "FROM " +
                "Usage " +
                "WHERE " +
                "UserId = ? " +
                "AND DATE(StartTime / 1000, 'unixepoch') = DATE('now') " +
                "GROUP BY " +
                "Hour " +
                "ORDER BY " +
                "strftime('%H', StartTime / 1000, 'unixepoch')";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String hour = resultSet.getString("Hour");
                double timeInHours = resultSet.getInt("Duration") / 60.0;
                dailySeries.getData().add(new XYChart.Data<>(hour, timeInHours));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dailySeries;
    }

    public ObservableList<ReportsController.Activity> getDailyReportDetail() {
        ObservableList<ReportsController.Activity> data = FXCollections.observableArrayList();
        String query = "SELECT " +
                "Application, " +
                "printf('%d hours %d minutes %d seconds', " +
                "total_seconds / 3600, " +
                "(total_seconds % 3600) / 60, " +
                "total_seconds % 60) AS TimeSpent " +
                "FROM " +
                "(SELECT Application, " +
                "SUM((EndTime / 1000) - (StartTime / 1000)) AS total_seconds " +
                "FROM " +
                "Usage " +
                "WHERE " +
                "UserId = ? " +
                "AND DATE(StartTime / 1000, 'unixepoch')  = DATE('now') " +
                "GROUP BY " +
                "Application)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String application = resultSet.getString("Application");
                String timeSpent = resultSet.getString("TimeSpent");
                data.add(new ReportsController.Activity(application, timeSpent));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;


    }

    public XYChart.Series<String, Number> getWeeklyReport() {
        XYChart.Series<String, Number> weeklySeries = new XYChart.Series<>();
        String query = "SELECT " +
                "CASE " +
                "WHEN strftime('%w',  StartTime / 1000, 'unixepoch') = '0' THEN 'Sunday' " +
                "WHEN strftime('%w',  StartTime / 1000, 'unixepoch') = '1' THEN 'Monday' " +
                "WHEN strftime('%w',  StartTime / 1000, 'unixepoch') = '2' THEN 'Tuesday' " +
                "WHEN strftime('%w',  StartTime / 1000, 'unixepoch') = '3' THEN 'Wednesday' " +
                "WHEN strftime('%w',  StartTime / 1000, 'unixepoch') = '4' THEN 'Thursday' " +
                "WHEN strftime('%w',  StartTime / 1000, 'unixepoch') = '5' THEN 'Friday' " +
                "WHEN strftime('%w',  StartTime / 1000, 'unixepoch') = '6' THEN 'Saturday' " +
                "END AS Day, " +
                "SUM((EndTime / 1000) - (StartTime / 1000)) / 60 AS Duration " +
                "FROM Usage " +
                "WHERE UserId = ? " +
                "AND DATE(StartTime / 1000, 'unixepoch') BETWEEN DATE('now', '-7 days') AND DATE('now') " +
                "GROUP BY strftime('%w', StartTime / 1000, 'unixepoch')";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String day = resultSet.getString("Day");
                double timeInHours = resultSet.getInt("Duration") /60.0;
                weeklySeries.getData().add(new XYChart.Data<>(day, timeInHours));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weeklySeries;

    }

    public ObservableList<ReportsController.Activity> getWeeklyReportDetail() {
        ObservableList<ReportsController.Activity> data = FXCollections.observableArrayList();
        String query = "SELECT " +
                "Application, " +
                "printf('%d hours %d minutes %d seconds', " +
                "total_seconds / 3600, " +
                "(total_seconds % 3600) / 60, " +
                "total_seconds % 60) AS TimeSpent " +
                "FROM " +
                "(SELECT Application, " +
                "SUM((EndTime / 1000) - (StartTime / 1000)) AS total_seconds " +
                "FROM " +
                "Usage " +
                "WHERE " +
                "UserId = ? " +
                "AND DATE(StartTime / 1000, 'unixepoch')  BETWEEN DATE('now', '-7 days') AND DATE('now')  " +
                "GROUP BY " +
                "Application)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, AppUser.getUserId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String application = resultSet.getString("Application");
                String timeSpent = resultSet.getString("TimeSpent");
                data.add(new ReportsController.Activity(application, timeSpent));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }
}
