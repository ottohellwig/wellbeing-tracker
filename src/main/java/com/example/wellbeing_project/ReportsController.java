package com.example.wellbeing_project;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        dailySeries.getData().add(new XYChart.Data<>("12am", 5));
        dailySeries.getData().add(new XYChart.Data<>("6am", 10));
        dailySeries.getData().add(new XYChart.Data<>("12pm", 20));
        dailySeries.getData().add(new XYChart.Data<>("6pm", 35));

        updateGraph(dailySeries);
    }

    @FXML
    private void showWeeklyReport() {
        XYChart.Series<String, Number> weeklySeries = new XYChart.Series<>();
        weeklySeries.getData().add(new XYChart.Data<>("Mon", 50));
        weeklySeries.getData().add(new XYChart.Data<>("Tue", 60));
        weeklySeries.getData().add(new XYChart.Data<>("Wed", 70));
        weeklySeries.getData().add(new XYChart.Data<>("Thu", 65));
        weeklySeries.getData().add(new XYChart.Data<>("Fri", 80));
        weeklySeries.getData().add(new XYChart.Data<>("Sat", 50));
        weeklySeries.getData().add(new XYChart.Data<>("Sun", 90));

        updateGraph(weeklySeries);
    }

    private void updateGraph(XYChart.Series<String, Number> series) {
        barChart.getData().clear();

        barChart.getData().add(series);


        double totalMinutes = series.getData().stream()
                .mapToDouble(data -> data.getYValue().doubleValue())
                .sum();
        double averageMinutes = totalMinutes / series.getData().size();

        int hours = (int) (averageMinutes / 60);
        int minutes = (int) (averageMinutes % 60);

        averageLabel.setText(String.format("%d hours %d minutes", hours, minutes));
    }
}
