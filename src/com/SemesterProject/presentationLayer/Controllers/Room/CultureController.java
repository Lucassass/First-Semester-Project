package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class CultureController extends Injection<MainController>
{

    public Label roomText;
    public Label itemText;

    private Item item;

    public void setItem(Item item)
    {
        if (item == null)
        {
            itemText.setText("");
        }
        else
        {
            itemText.setText(item.getName());
        }
        this.item = item;
    }

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


    public void onItemClick(ActionEvent actionEvent)
    {
        if (item != null)
        {
            getController().addItem(item);
        }
        setItem(getController().getGameStage().getItemFromRoom());
    }


}
