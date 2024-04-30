package com.example.wellbeing_project;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class ResourcesController {
    private HostServices hostServices;

    @FXML
    private Hyperlink resource1;
    @FXML
    private Hyperlink resource2;
    @FXML
    private Hyperlink resource3;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    public void initialize() {
        resource1.setOnAction(event -> openWebpage("https://edge.sitecorecloud.io/beyondblue1-beyondblueltd-p69c-fe1e/media/Project/Sites/beyondblue/PDF/Resource-Library/Young-people/bl1810-building-resilience-in-children-aged-0-12-booklet_acc.pdf"));
        resource2.setOnAction(event -> openWebpage("https://edge.sitecorecloud.io/beyondblue1-beyondblueltd-p69c-fe1e/media/Project/Sites/beyondblue/PDF/Resource-Library/Workplace/taking-care-of-yourself-after-a-job-loss.pdf?sc_lang=en"));
        resource3.setOnAction(event -> openWebpage("https://edge.sitecorecloud.io/beyondblue1-beyondblueltd-p69c-fe1e/media/Project/Sites/beyondblue/PDF/Resource-Library/Health-professionals/bl0556-what-works-for-depression-booklet_acc.pdf?sc_lang=en"));
    }

    private void openWebpage(String url) {
        hostServices.showDocument(url);
    }
}
