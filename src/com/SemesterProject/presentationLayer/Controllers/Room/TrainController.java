package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class TrainController  extends Injection<MainController> {

    public void onClickOutside(MouseEvent mouseEvent) {
        getController().goToOutsideFrom("left");
    }

    public void onTrainManClick(MouseEvent mouseEvent)
    {
        getController().getCardRow().setVisible(true);
        getController().getCardRowController().loadTrainCountries();
    }


}
