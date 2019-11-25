package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class OutsideController extends Injection
{


    @FXML private Label roomText;


    public void setRoomDescription(String description)
    {
        roomText.setText(description);
    }
    public void onMouseEntered(MouseEvent mouseEvent)
    {

        getMainController().onMouseEnter();
    }

    public void onMouseExited(MouseEvent mouseEvent)
    {
        getMainController().onMouseExit();
    }

    public void onClickAirport(MouseEvent mouseEvent) {

        getMainController().goToAirport();
    }

    public void onClickCulture(MouseEvent mouseEvent) {
        getMainController().goToCulture();
    }

    public void onClickGovernment(MouseEvent mouseEvent) {
        getMainController().goToGovernment();
    }

    public void onClickTrainStation(MouseEvent mouseEvent) {
        getMainController().goToTrainStation();
    }
}
