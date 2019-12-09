package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class CultureController extends Injection<MainController>
{


    public ImageView backgroundImage;
    public ImageView itemImage;


    private Item item;

    public void setItemImage(Image itemImage)
    {
        this.itemImage.setImage(itemImage);
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage.setImage(backgroundImage);
    }

    public void setItem(Item item)
    {
        if (item == null)
        {
            itemImage.setImage(null);
        }
        else
        {
            itemImage.setImage(item.getImage());
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


    public void onItemClicked(MouseEvent mouseEvent)
    {
        if (item != null)
        {
            getController().addItem(item);
        }
        setItem(getController().getGameStage().getItemFromRoom());
    }
}
