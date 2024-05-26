package com.example.wellbeing_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import java.awt.Desktop;
import java.net.URI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller class for managing recourses view.
 */
public class ResourcesController {

    @FXML
    private ImageView image1, image2, image3;
    @FXML
    private Hyperlink Resource1;
    @FXML
    private Hyperlink Resource2;
    @FXML
    private Hyperlink Resource3;
    @FXML
    /**
     * Initializes the controller. Sets images and actions for hyperlinks.
     */
    public void initialize() {
        // Set images for the ImageView components
        image1.setImage(new Image("https://www.probusvic.com.au/wp-content/uploads/Beter-Health-1.jpg"));
        image2.setImage(new Image("https://www.ie.edu/insights/wp-content/uploads/2021/06/MacGregor-Feature.png"));
        image3.setImage(new Image("https://www.ie.edu/insights/wp-content/uploads/2021/06/MacGregor-Feature.png"));

        // Set actions for the Hyperlink components
        Resource1.setOnAction(event -> openLink("https://www.betterhealth.vic.gov.au/health/healthyliving/wellbeing"));
        Resource2.setOnAction(event -> openLink("https://www.beyondblue.org.au/mental-health/wellbeing-action-tool"));
        Resource3.setOnAction(event -> openLink("https://www.mentalwellbeing.initiatives.qld.gov.au/resources"));
    }

    /**
     * Opens the provided URL in the default system browser.
     *
     * @param url The URL to be opened.
     */
    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}