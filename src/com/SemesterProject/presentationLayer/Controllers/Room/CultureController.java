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

    private Item[] items;

    private void setItems(Item... items)
    {
        this.items = items;
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
        getController().addItem(getItem());
    }

    private Item getItem()
    {
        for (var item: items) {
            item.getName();
        }
        return null;
    }
}
