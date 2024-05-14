package com.example.wellbeing_project.universal;

public class AppUser {
    private static int userId;
    private int reportId;
    private int timerId;
    private int usageId;
    private static String name;
    private static String email;
    private static String password;
    private String reportDate;
    private String timeName;
    private int durationMinutes;
    private int startTime;
    private int endTime;


    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getTimerId() {
        return timerId;
    }

    public void setTimerId(int timerId) {
        this.timerId = timerId;
    }

    public int getUsageId() {
        return usageId;
    }

    public void setUsageId(int usageId) {
        this.usageId = usageId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

}
