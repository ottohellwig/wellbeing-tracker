package com.example.wellbeing_project.universal;

/**
 * Represents a user in the application.
 */
public class AppUser {
    // Variables linked to database, possibly not needed
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

    /** Contracts a new AppUser with the specified name,email and password.
     *
     * @param name - user's name
     * @param email - user's email
     * @param password - user's password
     */
    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    /**
     * Gets the user's ID
     * @return The user's ID.
     */
    public static int getUserId() {
        return userId;
    }
    /** Sets the user's ID.
     * 
     * @param userId The unique user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's name.
     *
     * @return the user's name
     */
    public static String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's email.
     *
     * @return the user's email
     */
    public static String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return the user's password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the report ID.
     *
     * @return the report ID
     */
    public int getReportId() {
        return reportId;
    }

    /**
     * Sets the report ID.
     *
     * @param reportId the report ID
     */
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    /**
     * Gets the timer ID.
     *
     * @return the timer ID
     */
    public int getTimerId() {
        return timerId;
    }

    /**
     * Sets the timer ID.
     *
     * @param timerId the timer ID
     */
    public void setTimerId(int timerId) {
        this.timerId = timerId;
    }

    /**
     * Gets the usage ID.
     *
     * @return the usage ID
     */
    public int getUsageId() {
        return usageId;
    }

    /**
     * Sets the usage ID.
     *
     * @param usageId the usage ID
     */
    public void setUsageId(int usageId) {
        this.usageId = usageId;
    }

    /**
     * Gets the report date.
     *
     * @return the report date
     */
    public String getReportDate() {
        return reportDate;
    }

    /**
     * Sets the report date.
     *
     * @param reportDate the report date
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * Gets the time name.
     *
     * @return the time name
     */
    public String getTimeName() {
        return timeName;
    }

    /**
     * Sets the time name.
     *
     * @param timeName the time name
     */
    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    /**
     * Gets the duration in minutes.
     *
     * @return the duration in minutes
     */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * Sets the duration in minutes.
     *
     * @param durationMinutes the duration in minutes
     */
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    /**
     * Gets the start time.
     *
     * @return the start time
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the end time.
     *
     * @return the end time
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

}
