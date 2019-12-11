package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.Interfaces.Entities.IItem;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.scene.input.MouseEvent;

public class GovernmentController extends Injection<MainController> {


    private IItem itemUsed;

    /**
     * sets the item used
     * @param itemUsed the item that have been used
     */
    public void setItemUsed(IItem itemUsed) {
        this.itemUsed = itemUsed;
    }

    /**
     * @return item used in the government room
     */
    public IItem getItemUsed()
    {
        var itemToReturn = itemUsed;
        itemUsed = null;
        return itemToReturn;
    }

    /**
     * go to outside
     */
    public void onClickOutside(MouseEvent mouseEvent) {
        getController().goToOutsideFrom("right");
    }

    /**
     * loads deal gridpane
     */
    public void onManClicked(MouseEvent mouseEvent)
    {
        getController().getCardRow().setVisible(true);
        getController().getCardRowController().loadDeals();
    }
}
