package com.example.wellbeing_project;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import dao.ReportDAO;

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

    private final ReportDAO reportDAO = new ReportDAO();

    public void initialize() {
        showWeeklyReport();
        applicationColumn.setCellValueFactory(new PropertyValueFactory<>("application"));
        timeSpentColumn.setCellValueFactory(new PropertyValueFactory<>("timeSpent"));
    }

    @FXML
    private void showDailyReport() {
        isWeeklyReport = false;

        updateGraph(reportDAO.getDailyReport(), true);
        detailedActivityTable.setVisible(false);
    }

    @FXML
    private void showWeeklyReport() {
        isWeeklyReport = true;

        updateGraph(reportDAO.getWeeklyReport(), false);
        detailedActivityTable.setVisible(false);
    }

    @FXML
    private void showDetailedActivityClick() {
       if(isWeeklyReport) {
           detailedActivityTable.setItems(reportDAO.getWeeklyReportDetail());
       } else {
           detailedActivityTable.setItems(reportDAO.getDailyReportDetail());
       }

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
