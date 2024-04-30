module com.example.wellbeing_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.wellbeing_project to javafx.fxml;
    exports com.example.wellbeing_project;
}