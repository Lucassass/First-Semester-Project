package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.presentationLayer.CardRowCreator;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class CardRowController extends Injection<MainController>
{
    @FXML
    public GridPane gridPanel;
    public AnchorPane anchorForClose;
    public SplitPane splitPane;
    public AnchorPane anchorPane;

    public void loadDeals() {

        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 4);


        gridPanel = cardRowCreator.generateDeals(getController().getGameStage().getDealsForRoom());


        splitPane.lookupAll(".split-pane-divider")
                .forEach(div ->  div.setMouseTransparent(true) );
    }

    public void loadAirportChoice()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 2);

        gridPanel = cardRowCreator.generateAirportChoice();

        injectController(cardRowCreator);
    }

    public void loadTrainCountries()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 6);

        gridPanel = cardRowCreator.generateCountries(MainController.getGameStage().getFlyExist());

        injectController(cardRowCreator);
    }

    public void loadAirportCountries()
    {
        CardRowCreator cardRowCreator = new CardRowCreator(gridPanel, 6);

        gridPanel = cardRowCreator.generateCountries(MainController.getGameStage().getFlyExist());

        injectController(cardRowCreator);
    }


    public void onClose(ActionEvent actionEvent)
    {
        anchorPane.setVisible(false);
    }


    private void injectController(CardRowCreator cardRowCreator)
    {
        for (var controller: cardRowCreator.getAirportCardControllers()) {
            controller.injectController(getController());
        }
    }

}
