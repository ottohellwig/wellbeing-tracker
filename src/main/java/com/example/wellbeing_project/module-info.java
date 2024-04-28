module com.example.wellbeing_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.wellbeing_project to javafx.fxml;
    exports com.example.wellbeing_project.signup;
    exports com.example.wellbeing_project.universal
}
