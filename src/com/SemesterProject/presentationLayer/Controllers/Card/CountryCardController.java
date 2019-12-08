package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CountryCardController extends Injection<MainController>
{
    public Label cardName;
    public AnchorPane card;
    public Label cardPrice;
    public Button chooseButton;


    public void onCommercialClick(ActionEvent actionEvent)
    {
        if (MainController.getGameStage().goRandomCountry(getPrice()))
        {
            getController().goToNewCountry();
        }
    }

    public void onPrivateClick(ActionEvent actionEvent)
    {
        getController().getCardRowController().loadAirportPrivateCountries();
    }

    public void onChinaClick(ActionEvent actionEvent)
    {
        goToCountry("china");
    }

    public void onUSAClick(ActionEvent actionEvent)
    {
        goToCountry("usa");
    }

    public void onIndiaClick(ActionEvent actionEvent)
    {
        goToCountry("india");
    }

    public void onRussiaClick(ActionEvent actionEvent)
    {
        goToCountry("russia");
    }

    public void onJapanClick(ActionEvent actionEvent)
    {
       goToCountry("japan");
    }

    public void onGermanyClick(ActionEvent actionEvent)
    {
       goToCountry("germany");
    }

    private void goToCountry(String country)
    {
        if (MainController.getGameStage().goCountry(country,getPrice()))
        {
            getController().goToNewCountry();
        }
        else {
            getController().appendDialog("Not enough money");
        }
    }

    public void populateCard(String name, int price)
    {
        cardName.setText(name);
        cardPrice.setText("Price: " + price);
    }

    public int getPrice()
    {
        return Integer.parseInt(cardPrice.getText().split(" ")[1]);

    }



}
