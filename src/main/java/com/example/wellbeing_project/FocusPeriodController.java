package com.example.wellbeing_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FocusPeriodController {
    @FXML
    private TextField taskNameField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private Label focusStatusLabel;
    @FXML
    private ListView<Task> taskListView;

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private FocusPeriodService focusPeriodService = new FocusPeriodService();
    private ObservableList<Task> taskList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        taskListView.setItems(taskList);
        taskListView.setCellFactory(lv -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(task.getStartTime() + " - " + task.getEndTime() + "\n" + task.getName());
                    setStyle("-fx-background-color: " + (task.isComplete() ? "lightgreen" : "lightcoral") + ";");
                }
            }
        });
    }

    @FXML
    private void setFocusPeriod() {
        String taskName = taskNameField.getText();
        String startTimeText = startTimeField.getText();
        String endTimeText = endTimeField.getText();

        try {
            LocalTime startTime = LocalTime.parse(startTimeText, timeFormatter);
            LocalTime endTime = LocalTime.parse(endTimeText, timeFormatter);

            // Save and enforce the focus period
            focusPeriodService.setFocusPeriod(taskName, startTime, endTime);
            taskList.add(new Task(taskName, startTimeText, endTimeText, false));

            // Update the status label to indicate the focus period has started
            focusStatusLabel.setText("Focus period for '" + taskName + "' started from " + startTimeText + " to " + endTimeText);
        } catch (DateTimeParseException e) {
            focusStatusLabel.setText("Invalid time format. Please use HH:mm.");
        }
    }

    @FXML
    private void goBackToMainPage() {
        try {
            Stage stage = (Stage) taskNameField.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/wellbeing_project/home-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void markTaskAsComplete() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null && !selectedTask.isComplete()) {
            selectedTask.setComplete(true);
            taskListView.refresh();
        }
    }

    @FXML
    private void markTaskAsNotComplete() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null && selectedTask.isComplete()) {
            selectedTask.setComplete(false);
            taskListView.refresh();
        }
    }

    @FXML
    private void deleteTask() {
        Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            taskList.remove(selectedTask);
        }
    }
}

class Task {
    private String name;
    private String startTime;
    private String endTime;
    private boolean complete;

    public Task(String name, String startTime, String endTime, boolean complete) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
