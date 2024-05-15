package com.example.wellbeing_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.application.Platform;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.Animation;

public class HomeController {
    public Timeline timeline;
    public Label timeRemainingLabel;

    @FXML
    public TextField hoursField, minutesField, secondsField;

    @FXML
    public void startTimer() {
        int hours = hoursField.getText().isEmpty() ? 0 : Integer.parseInt(hoursField.getText());
        int minutes = minutesField.getText().isEmpty() ? 0 : Integer.parseInt(minutesField.getText());
        int seconds = secondsField.getText().isEmpty() ? 0 : Integer.parseInt(secondsField.getText());
        AtomicInteger totalTimeInSeconds = new AtomicInteger(hours * 3600 + minutes * 60 + seconds);

        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            int remainingTime = totalTimeInSeconds.getAndDecrement();
            int remainingHours = remainingTime / 3600;
            int remainingMinutes = (remainingTime % 3600) / 60;
            int remainingSeconds = remainingTime % 60;

            Platform.runLater(() -> {
                timeRemainingLabel
                        .setText(String.format("%02d:%02d:%02d", remainingHours, remainingMinutes, remainingSeconds));
            });

            if (remainingTime <= 0) {
                timeline.stop();
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Timer");
                    alert.setHeaderText(null);
                    alert.setContentText("Time is up!");
                    alert.showAndWait();
                });
            }
        }));
        timeline.playFromStart();
    }

    @FXML
    private void pauseTimer() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.PAUSED) {
                timeline.play();
            } else {
                timeline.pause();
            }
        }
    }

    @FXML
    private CheckBox fifteenMinuteIntervalCheckbox;

    private Timeline fifteenMinuteTimeline;

    public void initialize() {
        fifteenMinuteTimeline = new Timeline(new KeyFrame(Duration.minutes(15), event -> showPopup()));
        fifteenMinuteTimeline.setCycleCount(Timeline.INDEFINITE);

        fifteenMinuteIntervalCheckbox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                fifteenMinuteTimeline.play();
            } else {
                fifteenMinuteTimeline.stop();
            }
        });
    }

    private void showPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("15 minutes have passed!");

        alert.showAndWait();
    }
}