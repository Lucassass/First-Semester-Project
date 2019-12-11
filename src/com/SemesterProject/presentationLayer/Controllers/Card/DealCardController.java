package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.Interfaces.Entities.IDeal;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class DealCardController extends Injection<MainController>
{
    @FXML private TextArea cardDescription;
    @FXML private ImageView cardImage;
    @FXML private Label cardName;

    @FXML private Label cardPrice;
    @FXML private Label cardCategory;

    private IDeal deal;

    /**
     * tries to take the deal.
     */
    public void onCardClicked(ActionEvent actionEvent)
    {
        getController().takeDeal(deal);
        getController().getCardRowController().getAnchorPane().setVisible(false);
    }

    /**
     * sets up the card data
     * @param deal the deal to populate the card with
     */
    public void populateCard(IDeal deal)
    {
        this.deal = deal;
        cardDescription.setText(deal.getDescription());
        cardName.setText(deal.getName());
        cardPrice.setText("Price: " + deal.getPrice());
        cardImage.setImage(deal.getImage());
        cardCategory.setText("Category:" + deal.getCategory());
    }



}
