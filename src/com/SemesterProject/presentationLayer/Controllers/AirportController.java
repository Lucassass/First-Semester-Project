package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.presentationLayer.Injection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AirportController extends Injection
{


    @FXML private Label roomText;

    public void onClickOutside(MouseEvent mouseEvent)
    {
        getMainController().goToOutsideFrom("down");
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        getMainController().onMouseEnter();

    }

    public void onMouseExited(MouseEvent mouseEvent) {
        getMainController().onMouseExit();
    }

    public void setRoomDescription(String roomDescription)
    {
        roomText.setText(roomDescription);
    }


}
