package com.example.wellbeing_project;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private Button dailyReportButton;
    @FXML
    private Button weeklyReportButton;


    public void initialize() {
        showWeeklyReport();
    }

    @FXML
    private void showDailyReport() {
        XYChart.Series<String, Number> dailySeries = new XYChart.Series<>();

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Wellness_Tracker.db");
             Statement stmt = conn.createStatement()) {

            String query = "SELECT \n" +
                    "    CASE \n" +
                    "        WHEN strftime('%H', StartTime) BETWEEN '00' AND '05' THEN '12am'\n" +
                    "        WHEN strftime('%H', StartTime) BETWEEN '06' AND '11' THEN '6am'\n" +
                    "        WHEN strftime('%H', StartTime) BETWEEN '12' AND '17' THEN '12pm'\n" +
                    "        ELSE '6pm'\n" +
                    "    END AS TimeOfDay,\n" +
                    "    SUM(strftime('%s', EndTime) - strftime('%s', StartTime)) / 60 AS Duration\n" +
                    "FROM \n" +
                    "    Usage\n" +
                    "WHERE \n" +
                    "    UserId = 1\n" +
                    "    AND DATE(StartTime) = DATE('now')\n" +
                    "GROUP BY \n" +
                    "    TimeOfDay;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String day = rs.getString("TimeOfDay");
                int time = rs.getInt("Duration");
                dailySeries.getData().add(new XYChart.Data<>(day, time));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        updateGraph(dailySeries, true);
    }

    @FXML
    private void showWeeklyReport() {
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
                int time = rs.getInt("Duration");
                weeklySeries.getData().add(new XYChart.Data<>(day, time));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        updateGraph(weeklySeries, false);
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

    }

    private double calculateTotalMinutes(XYChart.Series<String, Number> series) {
        return series.getData().stream()
                .mapToDouble(data -> data.getYValue().doubleValue())
                .sum();
    }

    private double calculateAverageMinutes(XYChart.Series<String, Number> series) {
        double totalMinutes = calculateTotalMinutes(series);
        return totalMinutes / series.getData().size();
    }
}
