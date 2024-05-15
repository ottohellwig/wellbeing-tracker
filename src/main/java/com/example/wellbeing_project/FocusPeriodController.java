package com.example.wellbeing_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Label focusPeriodStatusLabel;

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private FocusPeriodService focusPeriodService = new FocusPeriodService();

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
            focusPeriodStatusLabel.setText("Focus period for \"" + taskName + "\" started from " + startTime + " to " + endTime + ".");
        } catch (DateTimeParseException e) {
            focusPeriodStatusLabel.setText("Invalid time format. Please use HH:mm.");
        }
    }
}