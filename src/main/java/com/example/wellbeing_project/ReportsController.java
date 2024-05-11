package com.example.wellbeing_project;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
public class ReportsController {
    @FXML
    private BarChart<String, Number> barChart;

    public void initialize() {
        // Sample data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Mon", 50));
        series.getData().add(new XYChart.Data<>("Tue", 60));
        series.getData().add(new XYChart.Data<>("Wed", 70));
        series.getData().add(new XYChart.Data<>("Thu", 65));
        series.getData().add(new XYChart.Data<>("Fri", 80));
        series.getData().add(new XYChart.Data<>("Sat", 50));
        series.getData().add(new XYChart.Data<>("Sun", 90));

        // Add data to the chart
        barChart.getData().add(series);
    }
}
