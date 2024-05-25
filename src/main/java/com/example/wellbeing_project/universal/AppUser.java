package com.example.wellbeing_project.universal;

import java.sql.Timestamp;

public class AppUser {
    // Variables linked to database, possibly not needed
    private static int userId;
    private static String name;
    private static String email;
    private static String password;
    private String reportDate;
    private static String timerName;
    private static int durationMinutes;
    private static Timestamp startTime;
    private static Timestamp endTime;

    /** User Constructor
     *
     * @param name - user name
     * @param email - user email
     * @param password - user password
     */
    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    // Getter and Setter methods
    public static int getUserId() {
        return userId;
    }
    /** User ID setter
     * 
     * @param userId - unique user ID
     */
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

    public void setEmail(String email) {
        this.email = email;
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
