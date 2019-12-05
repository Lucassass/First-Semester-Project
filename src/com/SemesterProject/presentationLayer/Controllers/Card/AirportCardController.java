package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class AirportCardController extends Injection<MainController>
{
    public Label cardName;
    public AnchorPane card;
    public Label cardPrice;
    public Button chooseButton;


    public void onCommercialClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goRandomCountry())
        {
            getController().goToNewCountry();
        }
    }

    public void onPrivateClick(ActionEvent actionEvent)
    {
        getController().getCardRowController().loadAirportCountries();
    }

    public void onChinaClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goCountry("china"))
        {
            getController().goToNewCountry();
        }
    }

    public void onUSAClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goCountry("usa"))
        {
            getController().goToNewCountry();
        }
    }

    public void onIndiaClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goCountry("india"))
        {
            getController().goToNewCountry();
        }
    }

    public void onRussiaClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goCountry("russia"))
        {
            getController().goToNewCountry();
        }
    }

    public void onJapanClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goCountry("japan"))
        {
            getController().goToNewCountry();
        }
    }

    public void onGermanyClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goCountry("germany"))
        {
            getController().goToNewCountry();
        }
    }

    public void populateCard(String name, int price)
    {
        cardName.setText(name);
        cardPrice.setText("Price: " + price);
    }



}
