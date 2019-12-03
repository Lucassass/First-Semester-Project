package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CultureController extends Injection<MainController>
{


    public Label roomText;

    public void onClickOutside(MouseEvent mouseEvent)
    {
        getController().goToOutsideFrom("up");
    }

    public void onMouseEntered(MouseEvent mouseEvent)
    {
        getController().onMouseEnter();
    }

    public void onMouseExited(MouseEvent mouseEvent) {
        getController().onMouseExit();
    }

    public void setRoomDescription(String description)
    {
        roomText.setText(description);
    }
}
