package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.Interfaces.Entities.IItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class ImageItem
{
    private ImageView image;
    private IItem item;

    ImageItem(ImageView image, Item item) {
        this.image = image;
        this.item = item;
        if (item == null)
        {
            image.setImage(null);
        }
        else
        {
            image.setImage(item.getImage());
        }
    }

    /**
     *
     * @return item
     */
    IItem getItem() {
        return item;
    }

    /**
     * update current items placement index
     * @param index new value
     */
    void updateIndex(int index)
    {
        item.setIndex(index);
    }

    /**
     * sets the new item, and the image
     * @param item item to be sat
     */
    void setItem(IItem item) {
        this.item = item;
        if (item == null){
            image.setImage(null);
        }
        else
        {
            image.setImage(item.getImage());
        }
    }

    /**
     *
     * @return image
     */
    Image getImage() {
        return image.getImage();
    }
}
