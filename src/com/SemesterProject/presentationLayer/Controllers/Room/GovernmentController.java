package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.Interfaces.Entities.IItem;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GovernmentController extends Injection<MainController> {


    private IItem itemUsed;

    public void setItemUsed(IItem itemUsed) {
        this.itemUsed = itemUsed;
    }

    public IItem getItemUsed()
    {
        var itemToReturn = itemUsed;
        itemUsed = null;
        return itemToReturn;
    }

    public void onClickOutside(MouseEvent mouseEvent) {
        getController().goToOutsideFrom("right");
    }

    public void onManClicked(MouseEvent mouseEvent)
    {
        getController().getCardRow().setVisible(true);
        getController().getCardRowController().loadDeals();
    }
}
