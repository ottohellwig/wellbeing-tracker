module com.example.signup01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.signup01 to javafx.fxml;
    exports com.example.signup01;
}