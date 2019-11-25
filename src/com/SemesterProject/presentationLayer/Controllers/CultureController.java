package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.presentationLayer.Injection;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CultureController extends Injection
{


    public Label roomText;

    public void onClickOutside(MouseEvent mouseEvent)
    {
        getMainController().goToOutsideFrom("up");
    }

    public void onMouseEntered(MouseEvent mouseEvent)
    {
        getMainController().onMouseEnter();
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        getMainController().onMouseExit();
    }

    public void setRoomDescription(String description)
    {
        roomText.setText(description);
    }
}
