package dao;

public class ReportDAO {

    public ReportDAO() {

    }

    public void getDailyReport() {

        String query = "SELECT " +
                "CASE " +
                "WHEN strftime('%H', StartTime) BETWEEN '00' AND '00' THEN '12am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '01' AND '01' THEN '1am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '02' AND '02' THEN '2am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '03' AND '03' THEN '3am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '04' AND '04' THEN '4am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '05' AND '05' THEN '5am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '06' AND '06' THEN '6am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '07' AND '07' THEN '7am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '08' AND '08' THEN '8am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '09' AND '09' THEN '9am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '10' AND '10' THEN '10am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '11' AND '11' THEN '11am' " +
                "WHEN strftime('%H', StartTime) BETWEEN '12' AND '12' THEN '12pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '13' AND '13' THEN '1pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '14' AND '14' THEN '2pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '15' AND '15' THEN '3pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '16' AND '16' THEN '4pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '17' AND '17' THEN '5pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '18' AND '18' THEN '6pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '19' AND '19' THEN '7pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '20' AND '20' THEN '8pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '21' AND '21' THEN '9pm' " +
                "WHEN strftime('%H', StartTime) BETWEEN '22' AND '22' THEN '10pm' " +
                "ELSE '11pm' " +
                "END AS Hour, " +
                "SUM(strftime('%s', EndTime) - strftime('%s', StartTime)) / 60 AS Duration " +
                "FROM " +
                "Usage " +
                "WHERE " +
                "UserId = ? " +
                "AND DATE(StartTime) = DATE('now') " +
                "GROUP BY " +
                "Hour " +
                "ORDER BY " +
                "strftime('%H', StartTime)";

    }

    public void getDailyReportDetail() {
        String query = "SELECT " +
                "Application, " +
                "SUM((strftime('%s', EndTime) - strftime('%s', StartTime)) / 3600) || ' hours ' || SUM(((strftime('%s', EndTime) - strftime('%s', StartTime)) % 3600) / 60) || ' minutes' AS TimeSpent " +
                "FROM " +
                "Usage " +
                "WHERE " +
                "UserId = ? " +
                "AND DATE(StartTime) = DATE('now') " +
                "GROUP BY " +
                "Application";

    }

    public void getWeeklyReport() {
        String query = "SELECT " +
                "CASE " +
                "WHEN strftime('%w', StartTime) = '0' THEN 'Sunday' " +
                "WHEN strftime('%w', StartTime) = '1' THEN 'Monday' " +
                "WHEN strftime('%w', StartTime) = '2' THEN 'Tuesday' " +
                "WHEN strftime('%w', StartTime) = '3' THEN 'Wednesday' " +
                "WHEN strftime('%w', StartTime) = '4' THEN 'Thursday' " +
                "WHEN strftime('%w', StartTime) = '5' THEN 'Friday' " +
                "WHEN strftime('%w', StartTime) = '6' THEN 'Saturday' " +
                "END AS Day, " +
                "SUM(strftime('%s', EndTime) - strftime('%s', StartTime)) / 60 AS Duration " +
                "FROM Usage " +
                "WHERE UserId = ? " +
                "AND DATE(StartTime) BETWEEN DATE('now', '-7 days') AND DATE('now') " +
                "GROUP BY strftime('%w', StartTime)";

    }

    public void getWeeklyReportDetail() {

        String query = "SELECT " +
                "Application, " +
                "SUM((strftime('%s', EndTime) - strftime('%s', StartTime)) / 3600) || ' hours ' || SUM(((strftime('%s', EndTime) - strftime('%s', StartTime)) % 3600) / 60) || ' minutes' AS TimeSpent " +
                "FROM " +
                "Usage " +
                "WHERE " +
                "UserId = ? " +
                "AND DATE(StartTime) BETWEEN DATE('now', '-7 days') AND DATE('now')  " +
                "GROUP BY " +
                "Application";

    }
}
