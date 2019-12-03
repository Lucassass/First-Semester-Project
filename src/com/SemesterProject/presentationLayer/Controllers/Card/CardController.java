package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CardController
{
    @FXML
    public TextArea cardDescription;
    @FXML
    public ImageView cardImage;
    @FXML
    public Label cardName;

    @FXML
    public Label cardPrice;

    private Deal deal;

    public void onCardClicked(ActionEvent actionEvent)
    {
            cardName.setText("I clicked");
    }

    void populateCard(Deal deal)
    {
        this.deal = deal;
        cardDescription.setText(deal.getDescription());
        cardName.setText(deal.getName());
        cardPrice.setText("Price: " + deal.getPrice());
    }
}
