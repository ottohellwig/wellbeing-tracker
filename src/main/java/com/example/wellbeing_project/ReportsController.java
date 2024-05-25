package com.example.wellbeing_project;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportsController {
    @FXML
    private Label averageLabel;
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private TableView<Activity> detailedActivityTable;

    @FXML
    private TableColumn<Activity, String> applicationColumn;

    @FXML
    private TableColumn<Activity, Integer> timeSpentColumn;

    private boolean isWeeklyReport = true;

    public void initialize() {
        showWeeklyReport();
        applicationColumn.setCellValueFactory(new PropertyValueFactory<>("application"));
        timeSpentColumn.setCellValueFactory(new PropertyValueFactory<>("timeSpent"));
    }

    @FXML
    private void showDailyReport() {
        isWeeklyReport = false;
        XYChart.Series<String, Number> dailySeries = new XYChart.Series<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Wellness_Tracker.db");
             Statement stmt = conn.createStatement()) {

            String query = "SELECT " +
            "CASE " +
            "WHEN strftime('%H', StartTime) BETWEEN '00' AND '00' THEN '12am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '01' AND '01' THEN '1am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '02' AND '02' THEN '2am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '03' AND '03' THEN '3am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '04' AND '04' THEN '4am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '05' AND '05' THEN '5am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '06' AND '06' THEN '6am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '07' AND '07' THEN '7am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '08' AND '08' THEN '8am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '09' AND '09' THEN '9am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '10' AND '10' THEN '10am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '11' AND '11' THEN '11am' " +
            "WHEN strftime('%H', StartTime) BETWEEN '12' AND '12' THEN '12pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '13' AND '13' THEN '1pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '14' AND '14' THEN '2pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '15' AND '15' THEN '3pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '16' AND '16' THEN '4pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '17' AND '17' THEN '5pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '18' AND '18' THEN '6pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '19' AND '19' THEN '7pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '20' AND '20' THEN '8pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '21' AND '21' THEN '9pm' " +
            "WHEN strftime('%H', StartTime) BETWEEN '22' AND '22' THEN '10pm' " +
            "ELSE '11pm' " +
            "END AS Hour, " +
            "SUM(strftime('%s', EndTime) - strftime('%s', StartTime)) / 60 AS Duration " +
            "FROM " +
            "Usage " +
            "WHERE " +
            "UserId = 1 " +
            "AND DATE(StartTime) = DATE('now') " +
            "GROUP BY " +
            "Hour " +
            "ORDER BY " +
            "strftime('%H', StartTime)";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String hour = rs.getString("Hour");
                double timeInHours = rs.getInt("Duration") / 60.0;
                dailySeries.getData().add(new XYChart.Data<>(hour, timeInHours));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        updateGraph(dailySeries, true);
        detailedActivityTable.setVisible(false);
    }

    @FXML
    private void showWeeklyReport() {
        isWeeklyReport = true;
       XYChart.Series<String, Number> weeklySeries = new XYChart.Series<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Wellness_Tracker.db");
             Statement stmt = conn.createStatement()) {

            String query = "SELECT " +
                    "CASE " +
                    "WHEN strftime('%w', StartTime) = '0' THEN 'Sunday' " +
                    "WHEN strftime('%w', StartTime) = '1' THEN 'Monday' " +
                    "WHEN strftime('%w', StartTime) = '2' THEN 'Tuesday' " +
                    "WHEN strftime('%w', StartTime) = '3' THEN 'Wednesday' " +
                    "WHEN strftime('%w', StartTime) = '4' THEN 'Thursday' " +
                    "WHEN strftime('%w', StartTime) = '5' THEN 'Friday' " +
                    "WHEN strftime('%w', StartTime) = '6' THEN 'Saturday' " +
                    "END AS Day, " +
                    "SUM(strftime('%s', EndTime) - strftime('%s', StartTime)) / 60 AS Duration " +
                    "FROM Usage " +
                    "WHERE UserId = 1 " +
                    "AND DATE(StartTime) BETWEEN DATE('now', '-7 days') AND DATE('now') " +
                    "GROUP BY strftime('%w', StartTime)";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String day = rs.getString("Day");
                double timeInHours = rs.getInt("Duration") /60.0;
                weeklySeries.getData().add(new XYChart.Data<>(day, timeInHours));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        updateGraph(weeklySeries, false);
        detailedActivityTable.setVisible(false);
    }

    @FXML
    private void showDetailedActivityClick() {
        // Retrieve and display detailed activity data
        detailedActivityTable.setItems(getDetailedActivityData(isWeeklyReport));
        detailedActivityTable.setVisible(true);
    }

    private void updateGraph(XYChart.Series<String, Number> series, boolean calculateTotal) {
        barChart.getData().clear();

        barChart.getData().add(series);

        double totalOrAverageMinutes = calculateTotal ? calculateTotalMinutes(series) : calculateAverageMinutes(series);

        int hours = (int) (totalOrAverageMinutes / 60);
        int minutes = (int) (totalOrAverageMinutes % 60);

        if (calculateTotal) {
            averageLabel.setText(String.format("Total: %d hours %d minutes", hours, minutes));
        } else {
            averageLabel.setText(String.format("Average: %d hours %d minutes", hours, minutes));
        }

        barChart.setAnimated(false);
    }

    private double calculateTotalMinutes(XYChart.Series<String, Number> series) {
        return series.getData().stream()
                .mapToDouble(data -> data.getYValue().doubleValue() * 60)
                .sum();
    }

    private double calculateAverageMinutes(XYChart.Series<String, Number> series) {
        double totalMinutes = calculateTotalMinutes(series);
        return totalMinutes / series.getData().size();
    }

    private ObservableList<Activity> getDetailedActivityData(boolean isWeeklyReport) {
        ObservableList<Activity> data = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Wellness_Tracker.db");
             Statement stmt = conn.createStatement()) {

            String query;
            if(isWeeklyReport) {
                query = "SELECT " +
                        "Application, " +
                        "SUM((strftime('%s', EndTime) - strftime('%s', StartTime)) / 3600) || ' hours ' || SUM(((strftime('%s', EndTime) - strftime('%s', StartTime)) % 3600) / 60) || ' minutes' AS TimeSpent " +
                        "FROM " +
                        "Usage " +
                        "WHERE " +
                        "UserId = 1 " +
                        "AND DATE(StartTime) BETWEEN DATE('now', '-7 days') AND DATE('now')  " +
                        "GROUP BY " +
                        "Application";
            } else {
                query = "SELECT " +
                        "Application, " +
                        "SUM((strftime('%s', EndTime) - strftime('%s', StartTime)) / 3600) || ' hours ' || SUM(((strftime('%s', EndTime) - strftime('%s', StartTime)) % 3600) / 60) || ' minutes' AS TimeSpent " +
                        "FROM " +
                        "Usage " +
                        "WHERE " +
                        "UserId = 1 " +
                        "AND DATE(StartTime) = DATE('now') " +
                        "GROUP BY " +
                        "Application";
            }

            ResultSet rs = stmt.executeQuery(query);

            // Populate the table data with the retrieved data
            while (rs.next()) {
                String application = rs.getString("Application");
                String timeSpent = rs.getString("TimeSpent");
                data.add(new Activity(application, timeSpent));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static class Activity {
        private final SimpleStringProperty application;
        private final SimpleStringProperty timeSpent;

        public Activity(String application, String timeSpent) {
            this.application = new SimpleStringProperty(application);
            this.timeSpent = new SimpleStringProperty(timeSpent);
        }

        public String getApplication() {
            return application.get();
        }

        public void setApplication(String application) {
            this.application.set(application);
        }

        public String getTimeSpent() {
            return timeSpent.get();
        }

        public void setTimeSpent(String timeSpent) {
            this.timeSpent.set(timeSpent);
        }
    }
}
