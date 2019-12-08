package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AirportController extends Injection<MainController>
{



    public void onClickOutside(MouseEvent mouseEvent)
    {
        getController().goToOutsideFrom("down");
    }

    public void onMouseEntered(MouseEvent mouseEvent) {
        getController().onMouseEnter();

    }

    public void onMouseExited(MouseEvent mouseEvent) {
        getController().onMouseExit();
    }


    public void onMouseClickGirl(MouseEvent mouseEvent)
    {
        getController().cardRow.setVisible(true);
        getController().getCardRowController().loadAirportChoice();

    }
}
