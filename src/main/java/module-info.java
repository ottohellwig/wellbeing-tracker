module com.example.wellbeing_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.wellbeing_project.signup to javafx.fxml;
    opens com.example.wellbeing_project.universal to javafx.fxml;
    opens com.example.wellbeing_project to javafx.fxml;
    opens com.example.wellbeing_project.login to javafx.fxml;
    opens com.example.wellbeing_project.resources_page to javafx.fxml;
    opens com.example.wellbeing_project.logout to javafx.fxml;

    exports com.example.wellbeing_project;
    exports com.example.wellbeing_project.signup;
    exports com.example.wellbeing_project.universal;
    exports com.example.wellbeing_project.login;
    exports com.example.wellbeing_project.resources_page;
    exports com.example.wellbeing_project.logout;
}