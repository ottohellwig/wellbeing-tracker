package com.example.wellbeing_project.universal;

import java.sql.Timestamp;

public class AppUser {
    // Variables linked to database
    private static int userId;
    private static String name;
    private static String email;
    private static String password;

    private static String timerName;
    private static int durationMinutes;
    private static Timestamp startTime;
    private static Timestamp endTime;


    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    // Getter and Setter methods
    public static int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static String getTimerName() {
        return timerName;
    }

    public static void setTimerName(String timerName) {
        AppUser.timerName = timerName;
    }

    public static int getDurationMinutes() {
        return durationMinutes;
    }

    public static void setDurationMinutes(int durationMinutes) {
        AppUser.durationMinutes = durationMinutes;
    }

    public static Timestamp getStartTime() {
        return startTime;
    }

    public static void setStartTime(Timestamp startTime) {
        AppUser.startTime = startTime;
    }

    public static Timestamp getEndTime() {
        return endTime;
    }

    public static void setEndTime(Timestamp endTime) {
        AppUser.endTime = endTime;
    }

}
