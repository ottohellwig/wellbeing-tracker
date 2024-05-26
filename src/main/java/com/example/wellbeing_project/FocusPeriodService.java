package com.example.wellbeing_project;

import java.time.LocalTime;

public class FocusPeriodService {
    private String taskName;
    private LocalTime focusStartTime;
    private LocalTime focusEndTime;

    public void setFocusPeriod(String taskName, LocalTime startTime, LocalTime endTime) {
        this.taskName = taskName;
        this.focusStartTime = startTime;
        this.focusEndTime = endTime;
        enforceFocusPeriod();
    }

    private void enforceFocusPeriod() {
        // Implement logic to enforce the focus period
        // For example, restrict access to certain features or display notifications
        System.out.println("Enforcing focus period for \"" + taskName + "\" from " + focusStartTime + " to " + focusEndTime);
    }

    public boolean isWithinFocusPeriod() {
        LocalTime now = LocalTime.now();
        return (now.isAfter(focusStartTime) || now.equals(focusStartTime)) && now.isBefore(focusEndTime);
    }
}
