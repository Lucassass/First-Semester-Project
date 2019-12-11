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

    @FXML private GridPane gridPanel;
    @FXML private AnchorPane anchorForClose;
    @FXML private SplitPane splitPane;
    @FXML private AnchorPane anchorPane;

    /**
     * loads the deal to the grid pane
     */
    public void loadDeals() {

        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 4);


        gridPanel = cardRowCreator.generateDeals(getController().getGameStage().getDealsFromCurrentRoom());

        injectDealController(cardRowCreator.getDealCardControllers());

        splitPane.lookupAll(".split-pane-divider")
                .forEach(div ->  div.setMouseTransparent(true) );
    }

    /**
     * loads the airport choices to the grid pane
     */
    public void loadAirportChoice()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 2);

        gridPanel = cardRowCreator.generateAirportChoice();

        injectCountryController(cardRowCreator.getCountryCardControllers());
    }

    /**
     * loads train countries to the grid pane
     */
    public void loadTrainCountries()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 6);

        gridPanel = cardRowCreator.generateCountries(MainController.getGameStage().getTrainExistFromCurrentRoom(),
                MainController.getGameStage().getConfig().getTrainCost());

        injectCountryController(cardRowCreator.getCountryCardControllers());
    }

    /**
     * load airport countries to the grid pane
     */
    public void loadAirportPrivateCountries()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 6);

        gridPanel = cardRowCreator.generateCountries(MainController.getGameStage().getFlyExistFromCurrentRoom(),
                MainController.getGameStage().getConfig().getPrivateFlyingCost());

        injectCountryController(cardRowCreator.getCountryCardControllers());
    }


    /**
     * closes the cards
     */
    public void onClose(ActionEvent actionEvent)
    {
        anchorPane.setVisible(false);
    }


    /**
     * injects CountryController with MainController
     * @param controllers to be injected
     */
    private void injectCountryController(List<CountryCardController> controllers)
    {
        for (var controller: controllers) {
            controller.injectController(getController());
        }
    }

    /**
     * injects DealController with MainController
     * @param controllers to be injected
     */
    private void injectDealController(List<DealCardController> controllers)
    {
        for (var controller: controllers) {
            controller.injectController(getController());
        }
    }

}
