package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class AirportController extends Injection<MainController>
{


    /**
     * go to outside room
     */
    public void onClickOutside(MouseEvent mouseEvent)
    {
        getController().goToOutsideFrom("down");
    }


    /**
     * loads airport choice gridpane
     */
    public void onMouseClickGirl(MouseEvent mouseEvent)
    {
        getController().getCardRow().setVisible(true);
        getController().getCardRowController().loadAirportChoice();

    }
}
