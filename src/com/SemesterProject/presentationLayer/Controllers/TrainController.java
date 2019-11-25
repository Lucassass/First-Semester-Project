package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.presentationLayer.Injection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TrainController  extends Injection {


    @FXML private Label roomText;

    public void setRoomDescription(String description)
    {
        roomText.setText(description);
    }

    public void onClickOutside(MouseEvent mouseEvent) {
        getMainController().goToOutsideFrom("left");
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        getMainController().onMouseEnter();
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        getMainController().onMouseExit();
    }
}
