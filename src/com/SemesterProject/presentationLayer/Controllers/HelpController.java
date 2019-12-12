package com.SemesterProject.presentationLayer.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class HelpController {
    @FXML private javafx.scene.control.Button closeHelpId;


    @FXML
    public void closeHelp(ActionEvent event) {

        // get a handle to the stage
        Stage stage = (Stage) closeHelpId.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
