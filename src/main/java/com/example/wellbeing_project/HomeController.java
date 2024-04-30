package com.example.wellbeing_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.application.Platform;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeController {
    public Timeline timeline;
    public Label timeRemainingLabel;

    @FXML
    public TextField timerField;

    @FXML
    public void startTimer() {
        AtomicInteger totalTimeInSeconds = new AtomicInteger(Integer.parseInt(timerField.getText()));

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
                timeRemainingLabel.setText(String.format("%02d:%02d:%02d", remainingHours, remainingMinutes, remainingSeconds));
            });

            if (remainingTime <= 0) {
                timeline.stop();
            }
        }));
        timeline.playFromStart();
    }

    @FXML
    private void pauseTimer() {
        if (timeline != null) {
            timeline.pause();
        }
    }


}