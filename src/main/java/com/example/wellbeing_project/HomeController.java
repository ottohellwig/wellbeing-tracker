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
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import java.util.concurrent.atomic.AtomicBoolean;
import dao.TimerDAO;
import java.sql.Timestamp;
import com.example.wellbeing_project.universal.AppUser;
import dao.UsageDAO;
public class HomeController {

    private AtomicBoolean stopTracking = new AtomicBoolean(false);

    private static final int MAX_TITLE_LENGTH = 1024;
    public Timeline timeline;
    public Label timeRemainingLabel;

    @FXML
    public TextField hoursField, minutesField, secondsField;
    @FXML
    public TextField TimerName;

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

        stopTracking.set(false);
        new Thread(this::trackActiveWindow).start();

        // Create Timer name variable and default option
        String timerNameText = TimerName.getText();
        if (timerNameText == null || timerNameText.trim().isEmpty()) {
            // Set default value
            timerNameText = "Random Timer";
        }
        AppUser.setTimerName(timerNameText);
        AppUser.setDurationMinutes((hours * 60) + minutes + (seconds / 60));
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        AppUser.setStartTime(startTime);

        // Calculate and set end time
        int durationInSeconds = (hours * 3600) + (minutes * 60) + seconds;
        long endTimeInMillis = System.currentTimeMillis() + (durationInSeconds * 1000);
        Timestamp endTime = new Timestamp(endTimeInMillis);
        AppUser.setEndTime(endTime);

        // Save timer to database
        TimerDAO timerDAO = new TimerDAO();
        timerDAO.addTimer();
    }

    @FXML
    private void pauseTimer() {
        if (timeline != null) {
            if (timeline.getStatus() == Animation.Status.PAUSED) {
                timeline.play();
            } else {
                timeline.pause();
                stopTracking.set(true);
            }
        }
    }

    @FXML
    private CheckBox fifteenMinuteIntervalCheckbox;

    private Timeline fifteenMinuteTimeline;

    public void initialize() {
        fifteenMinuteTimeline = new Timeline(new KeyFrame(Duration.minutes(15), event -> {
            System.out.println("15 minutes passed."); // Debugging line
            showPopup();
        }));
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
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("15 minutes have passed!");
            alert.show();
        });
    }

    private void trackActiveWindow() {
        String previousWindowTitle = "";
        long startTime = System.currentTimeMillis();
        UsageDAO usageDAO = new UsageDAO();

        while (!stopTracking.get()) {
            String activeWindowTitle = getActiveWindowTitle();

            if (!activeWindowTitle.equals(previousWindowTitle)) {
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;

                System.out.println("Window: " + previousWindowTitle + ", Duration: " + duration + " ms");

                // Add previous app used to database
                if (!previousWindowTitle.isEmpty()) {
                    Timestamp startTimestamp = new Timestamp(startTime);
                    Timestamp endTimestamp = new Timestamp(endTime);
                    usageDAO.addUsage(previousWindowTitle, startTimestamp, endTimestamp);
                }

                previousWindowTitle = activeWindowTitle;
                startTime = System.currentTimeMillis();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getActiveWindowTitle() {
        byte[] windowText = new byte[512];
        HWND hWnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowTextA(hWnd, windowText, 512);
        return Native.toString(windowText);
    }

    public interface User32 extends WinUser, com.sun.jna.Library {
        User32 INSTANCE = (User32) Native.load("user32", User32.class);

        HWND GetForegroundWindow();
        int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
    }
}