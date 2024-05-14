package com.example.wellbeing_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import java.awt.Desktop;
import java.net.URI;

public class ResourcesController {

    @FXML
    private Hyperlink Resource1;
    @FXML
    private Hyperlink Resource2;
    @FXML
    private Hyperlink Resource3;

    @FXML
    public void initialize() {
        Resource1.setOnAction(event -> openLink("https://www.betterhealth.vic.gov.au/health/healthyliving/wellbeing"));
        Resource2.setOnAction(event -> openLink("https://www.beyondblue.org.au/mental-health/wellbeing-action-tool"));
        Resource3.setOnAction(event -> openLink("https://www.mentalwellbeing.initiatives.qld.gov.au/resources"));
    }

    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}