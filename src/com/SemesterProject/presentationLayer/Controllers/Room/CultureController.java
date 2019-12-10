package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;



public class CultureController extends Injection<MainController>
{


    @FXML private ImageView backgroundImage;

    @FXML private ImageView firstItemImage;
    @FXML private ImageView secondItemImage;
    @FXML private ImageView thirdItemImage;

    private ArrayList<ImageItem> imageItems = new ArrayList<>();


    /**
     *
     */
    @Override
    public void postInjection() {
        firstItemImage.setImage(null);
        secondItemImage.setImage(null);
        thirdItemImage.setImage(null);
    }

    /**
     *
     * @param backgroundImage background image
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage.setImage(backgroundImage);
    }

    /**
     * 
     */
    private void clearItems()
    {
        if (imageItems != null)
        {
            for (var item: imageItems) {
                item.setItem(null);
            }
        }

        imageItems = new ArrayList<>();
        imageItems.add(new ImageItem(firstItemImage, null));
        imageItems.add(new ImageItem(secondItemImage, null));
        imageItems.add(new ImageItem(thirdItemImage, null));
    }

    public void setItem(ArrayList<Item> items)
    {
        clearItems();


        for (var item : items)
        {
            for (int i = 0; i < imageItems.size(); i++) {
                ImageItem imageItem = imageItems.get(i);

                if (item.getIndex() == i)
                {
                    imageItem.setItem(item);
                }

            }
        }

    }

    public Item replaceItem(Item item)
    {
        for (var itemImage: imageItems)
        {
            if (itemImage.getItem() == null)
            {
                itemImage.setItem(item);
                return null;
            }
        }

        if (imageItems.size() > 0)
        {
            var currentItem = imageItems.get(0).getItem();
            imageItems.get(0).setItem(item);
            return currentItem;
        }

        return null;
    }


    public void onClickOutside(MouseEvent mouseEvent)
    {
        getController().goToOutsideFrom("up");
    }


    public void onThirdItemClicked(MouseEvent mouseEvent)
    {
        if (imageItems.size() < 2) return;

        clickItem(imageItems.get(2));

    }

    public void onFirstItemClicked(MouseEvent mouseEvent)
    {
        if (imageItems.isEmpty()) return;

        clickItem(imageItems.get(0));
    }

    public void onSecondItemClicked(MouseEvent mouseEvent)
    {
        if (imageItems.size() < 1) return;

        clickItem(imageItems.get(1));
    }

    private void clickItem(ImageItem item)
    {
        if (item != null && item.getImage() != null)
        {
            getController().addItem(item.getItem());
            updateItem();
        }
    }

    private void updateItem()
    {
        var itemsInRoom = getController().getGameStage().getItemFromRoom();

        for (var item : imageItems)
        {
            boolean found = false;
            for (var itemInRoom: itemsInRoom)
            {
                if (item.getItem() == null) continue;

                if (item.getItem().getUuid() == itemInRoom.getUuid())
                {
                    found = true;
                    break;
                }

            }

            if (!found && item != null)
            {
                item.setItem(null);
            }
        }
    }
}

class ImageItem
{
    private ImageView image;
    private Item item;

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

    Item getItem() {
        return item;
    }

    void setItem(Item item) {
        this.item = item;
        if (item == null){
            image.setImage(null);
        }
        else
        {
            image.setImage(item.getImage());
        }
    }

    public Image getImage() {
        return image.getImage();
    }
}
