package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TrainController  extends Injection<MainController> {


    @FXML private Label roomText;

    public void setRoomDescription(String description)
    {
        roomText.setText(description);
    }

    public void onClickOutside(MouseEvent mouseEvent) {
        getController().goToOutsideFrom("left");
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        getController().onMouseEnter();
    }

    public void onMouseExited(MouseEvent mouseEvent) { getController().onMouseExit();
    }

    public void onTrainManClick(MouseEvent mouseEvent)
    {

        getController().cardRow.setVisible(true);
        getController().getCardRowController().loadTrainCountries();
    }

    public void onTrainManEnter(MouseEvent mouseEvent) {
        getController().onMouseEnter();
    }

    public void onTrainManExit(MouseEvent mouseEvent) {
        getController().onMouseExit();
    }
}
