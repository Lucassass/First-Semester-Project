package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

class ItemImage
{
    private ImageView image;
    private Item item;

    public ItemImage(ImageView image, Item item) {
        this.image = image;
        this.item = item;
        image.setImage(item.getImage());
    }

    ImageView getImage() {
        return image;
    }

    Item getItem() {
        return item;
    }

    void setItem(Item item) {
        this.item = item;
        image.setImage(item.getImage());
    }

    void pickUp()
    {
        item = null;
        image.setImage(null);
    }
}

public class CultureController extends Injection<MainController>
{


    public ImageView backgroundImage;

    public ImageView firstItemImage;
    public ImageView secondItemImage;
    public ImageView thirdItemImage;

    private ArrayList<ItemImage> items;

    private Item firstItem;
    private Item secondItem;
    private Item thirdItem;


    @Override
    public void postInjection() {
        firstItemImage.setImage(null);
        secondItemImage.setImage(null);
        thirdItemImage.setImage(null);
    }

    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage.setImage(backgroundImage);
    }

    public void setItem(ArrayList<Item> items)
    {
        this.items = new ArrayList<>();

        for (var item: items) {
            switch (this.items.size())
            {
                case 0:
                    this.items.add(new ItemImage(firstItemImage, item));
                    break;
                case 1:
                    this.items.add(new ItemImage(secondItemImage, item));
                    break;
                case 2:
                    this.items.add(new ItemImage(thirdItemImage, item));
                    break;
            }
        }
    }

    public Item replaceItem(Item item)
    {
        for (var itemImage: items)
        {
            if (itemImage.getItem() == null)
            {
                itemImage.setItem(item);
                return null;
            }
        }

        if (items.size() > 0)
        {
            var currentItem = items.get(0).getItem();
            items.get(0).setItem(item);
            return currentItem;
        }

        return null;
    }

/*
    public void setFirstItem(Item item)
    {
        if (item == null) {
            firstItemImage.setImage(null);
        } else {
            firstItemImage.setImage(item.getImage());
        }
        this.firstItem = item;
    }

    public void setSecondItem(Item item)
    {
        if (item == null) {
            secondItemImage.setImage(null);
        } else {
            secondItemImage.setImage(item.getImage());
        }
        this.secondItem = item;
    }

    public void setThirdItem(Item item)
    {
        if (item == null) {
            thirdItemImage.setImage(null);
        } else {
            thirdItemImage.setImage(item.getImage());
        }
        this.thirdItem = item;
    }

 */


    public void onClickOutside(MouseEvent mouseEvent)
    {
        getController().goToOutsideFrom("up");
    }


    public void onItemClicked(MouseEvent mouseEvent)
    {

    }

    public void onThirdItemClicked(MouseEvent mouseEvent)
    {
        if (items.size() < 2) return;

        var thirdItem = items.get(2);

        if (thirdItem != null)
        {
            getController().addItem(thirdItem.getItem());
            thirdItem.pickUp();
        }
        //setFirstItem(getController().getGameStage().getItemFromRoom());
    }

    public void onFirstItemClicked(MouseEvent mouseEvent)
    {
        if (items.isEmpty()) return;

        var firstItem = items.get(0);
        if (firstItem != null)
        {
            getController().addItem(firstItem.getItem());
            firstItem.pickUp();
        }
    }

    public void onSecondItemClicked(MouseEvent mouseEvent)
    {
        if (items.size() < 1) return;

        var secondItem = items.get(1);

        if (secondItem != null)
        {
            getController().addItem(secondItem.getItem());
            secondItem.pickUp();
        }
    }
}
