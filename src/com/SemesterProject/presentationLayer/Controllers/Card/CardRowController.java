package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.presentationLayer.CardRowCreator;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.List;


public class CardRowController extends Injection<MainController>
{
    @FXML
    public GridPane gridPanel;
    public AnchorPane anchorForClose;
    public SplitPane splitPane;
    public AnchorPane anchorPane;

    public void loadDeals() {

        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 4);


        gridPanel = cardRowCreator.generateDeals(getController().getGameStage().getDealsFromRoom());

        injectDealController(cardRowCreator.getDealCardControllers());

        splitPane.lookupAll(".split-pane-divider")
                .forEach(div ->  div.setMouseTransparent(true) );
    }

    public void loadAirportChoice()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 2);

        gridPanel = cardRowCreator.generateAirportChoice();

        injectCountryController(cardRowCreator.getCountryCardControllers());
    }

    public void loadTrainCountries()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 6);

        gridPanel = cardRowCreator.generateCountries(MainController.getGameStage().getTrainExist(),
                MainController.getGameStage().getConfig().getTrainCost());

        injectCountryController(cardRowCreator.getCountryCardControllers());
    }

    public void loadAirportPrivateCountries()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 6);

        gridPanel = cardRowCreator.generateCountries(MainController.getGameStage().getFlyExist(),
                MainController.getGameStage().getConfig().getPrivateFlyingCost());

        injectCountryController(cardRowCreator.getCountryCardControllers());
    }


    public void onClose(ActionEvent actionEvent)
    {
        anchorPane.setVisible(false);
    }


    private void injectCountryController(List<CountryCardController> controllers)
    {
        for (var controller: controllers) {
            controller.injectController(getController());
        }
    }
    private void injectDealController(List<DealCardController> controllers)
    {
        for (var controller: controllers) {
            controller.injectController(getController());
        }
    }

}
