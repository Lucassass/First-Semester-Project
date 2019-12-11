package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.DomainLogic.Entities.Deal;
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
    @FXML
    public TextArea cardDescription;
    @FXML
    public ImageView cardImage;
    @FXML
    public Label cardName;

    @FXML
    public Label cardPrice;
    @FXML private Label cardCategory;

    private IDeal deal;

    public void onCardClicked(ActionEvent actionEvent)
    {
        getController().addDeal(deal);
        getController().getCardRowController().anchorPane.setVisible(false);
    }

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
