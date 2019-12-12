package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.GameStage;
import com.SemesterProject.DomainLogic.Inventory;
import com.SemesterProject.Interfaces.Entities.IDeal;
import com.SemesterProject.Interfaces.Entities.IItem;
import com.SemesterProject.Interfaces.IGameStage;
import com.SemesterProject.Interfaces.IInventory;
import com.SemesterProject.presentationLayer.Controllers.Card.CardRowController;
import com.SemesterProject.presentationLayer.Controllers.Room.*;
import com.SemesterProject.presentationLayer.ImageReturner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Application implements Initializable {

    @FXML private AnchorPane cardRow;

    @FXML private Label money;

    @FXML private TextArea dialog;

    @FXML private ListView<IItem> inventoryItems;
    @FXML private ListView<IDeal> inventoryDeals;


    @FXML private Label sustainabilityPoint;
    @FXML private Label environmentPoint;
    @FXML private Label energyPoint;

    @FXML private AnchorPane endScreen;
    @FXML private ImageView endScreenImage;


    @FXML private ImageView localMap, globalMap;


    @FXML private StackPane airport;
    @FXML private AirportController airportController;

    @FXML private StackPane outside;
    @FXML private OutsideController outsideController;

    @FXML private StackPane culture;
    @FXML private CultureController cultureController;

    @FXML private StackPane government;
    @FXML private GovernmentController governmentController;

    @FXML private StackPane train;
    @FXML private TrainController trainController;

    @FXML private CardRowController cardRowController;
    private static IGameStage gameStage;
    private static IInventory inventory;

    private static Stage stage;

    public static IGameStage getGameStage() {
        return gameStage;
    }

    private void setStageName(){
        stage.setTitle(gameStage.getCurrentRoomName() + " | " + gameStage.getCurrentCountryName());
    }

    public CardRowController getCardRowController() {
        return cardRowController;
    }

    public AnchorPane getCardRow() {
        return cardRow;
    }

    public static void main(String[] args)
    {
        gameStage = new GameStage();
        inventory = Inventory.getInstance();
        Application.launch(MainController.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        MainController.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/presentation.fxml"));
        Scene scene = new Scene(root);
        setStageName();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outsideController.injectController(this);
        airportController.injectController(this);
        cultureController.injectController(this);
        governmentController.injectController(this);
        trainController.injectController(this);
        cardRowController.injectController(this);
        appendDialog("This is a game of choice. Everything you do has an effect so choose wisely. But here's the catch you won’t know how you did until you finish the game\n" +
                "for more information, try “Help”");
        updateMoney();
        setupOutsideRoom();
        globalMap.setImage(ImageReturner.globalMap(gameStage.getCurrentCountryName()));
        configureInventory();

    }


    /**
     * Opens the endgame screen when clicked
     */
    public void onQuitButton(ActionEvent actionEvent) {
        endGame();
    }

    /**
     * Remove the selected deal
     */
    public void onDealRemove(ActionEvent actionEvent)
    {
        var item = inventoryDeals.getSelectionModel().getSelectedItem();

        inventory.removeDeal((Deal) item);
        inventoryDeals.getItems().remove(item);
        appendDialog("Removed deal: " + ((Deal) item).getName() + "| " + ((Deal) item).getCategory());
    }

    /**
     * If player is located in the culture room, the selected item will be switched, if the room is full of items.
     * The selected item will be switched with one of the item in the culture room. If there is an empty room
     * the selected item will be places there insted.
     */
    public void onItemSwitch(ActionEvent actionEvent)
    {
        var item = inventoryItems.getSelectionModel().getSelectedItem();

        if (gameStage.getCurrentRoomName().equalsIgnoreCase("culture"))
        {
            var replacedItem = cultureController.replaceItem(item);

            if (replacedItem != null)
            {
                appendDialog("Switching " + item.getName() + " out with " + replacedItem.getName());

                cultureController.replaceItem(item);

                addItem(replacedItem);
            }
            else
            {
                appendDialog("Switching " + item.getName() + " out with nothing");

            }

            removeItemFromInventory(item);
            gameStage.addItemToCurrentRoom(item);
        }
        else{
            appendDialog("You can't switch items outside of the culture room");
        }


    }

    /**
     * When using an item, if player are located in the government room, the item will be used
     */
    public void onItemUse(ActionEvent actionEvent)
    {
        var item = inventoryItems.getSelectionModel().getSelectedItem();
        if (item == null) return;

        if (gameStage.getCurrentRoomName().equalsIgnoreCase("government"))
        {
            appendDialog("You have used " + (item).getName());
            appendDialog(getGameStage().getQuoteFromItemUsed(item));
            removeItemFromInventory(item);
            governmentController.setItemUsed(item);


        }
        else {
            appendDialog("You can't use items outside of the government room");
        }
    }

    /**
     * Takes the deal, check if you have room in inventory, money and then makes the dice rool
     * @param deal the deal you want to try to take
     */
    public void takeDeal(IDeal deal)
    {
        if (!inventory.isFullOfDeals(deal.getCategory()))
        {
            if (getGameStage().getConfig().gotEnoughMoney(deal.getPrice()))
            {
                getGameStage().getConfig().subtractMoney(deal.getPrice());
                if (gameStage.takeDeal(deal, governmentController.getItemUsed()))
                {
                    inventory.addDeal(deal);
                    appendDialog("Added deal: " + deal.getName() + " | " + deal.getCategory());
                    inventoryDeals.getItems().add(deal);
                }
                else
                {
                    appendDialog("You didn't get the deal :(");
                }
                gameStage.removeDealFromCurrentRoom(deal.getUuid());
            }
            else
            {
                appendDialog("You dont have enough money for this deal :(");
            }


        }
        else {
            appendDialog("You can't hold more deal of " + deal.getCategory() + ". Please remove \na deal of the " +
                    "same category and try again.");
        }

        updateMoney();
    }

    /**
     * adds the item to the inventory. Both in domain layer and presentation layer
     * @param item the item that shall be added
     */
    public void addItem(IItem item)
    {
        if (!inventory.isFullOfItems() && item != null)
        {
            inventory.addItem(item);
            appendDialog("Added item: " + item.getName());
            inventoryItems.getItems().add(item);
            gameStage.removeItemFromCurrentRoom(item.getUuid());
        }
        else
        {
            appendDialog("Inventory is full. Please remove an item from your inventory\n" +
                    "if you want to pick this one up");
        }

    }

    /**
     * Writes to dialog area
     * @param text The text you want to display
     */
    public void appendDialog(String text)
    {
        dialog.appendText(text + "\n");
    }

    /**
     * Go to a new country, updates stage name, money and dialog
     */
    public void goToNewCountry()
    {
        updateMoney();
        outside.setVisible(true);
        airport.setVisible(false);
        government.setVisible(false);
        culture.setVisible(false);
        train.setVisible(false);
        setStageName();
        cardRowController.getAnchorPane().setVisible(false);
        appendDialog("Moving to: " + getGameStage().getCurrentCountryName());
        outsideController.setBackgroundImage(ImageReturner.outsideRoom(gameStage.getCurrentCountryName()));

        globalMap.setImage(ImageReturner.globalMap(gameStage.getCurrentCountryName()));
    }

    /**
     * Goes to airport room, sets background and display its description
     */
    public void goToAirport()
    {
        if (gameStage.goToRoom("up"))
        {
            cardRowController.loadAirportChoice();
            outside.setVisible(false);
            airport.setVisible(true);

            setStageName();
            appendDialog(getGameStage().getCurrentRoomDescription());
            localMap.setImage(new Image (getClass().getResourceAsStream("/images/Airport.png")));
        }

    }

    /**
     * Goes to outside room, sets background and display its description
     * @param direction Which direction you are coming from
     */
    public void goToOutsideFrom(String direction)
    {
        if (gameStage.goToRoom(direction))
        {
            setupOutsideRoom();
            localMap.setImage(new Image (getClass().getResourceAsStream("/images/Outside.png")));
        }

    }

    /**
     * Goes to government room, sets background and display its description
     */
    public void goToGovernment()
    {
        if (gameStage.goToRoom("left"))
        {
            cardRowController.loadDeals();
            outside.setVisible(false);
            government.setVisible(true);
            governmentController.setItemUsed(null);

            setStageName();
            appendDialog(getGameStage().getCurrentRoomDescription());
            localMap.setImage(new Image (getClass().getResourceAsStream("/images/Goverment.png")));
        }

    }

    /**
     *Goes to train station room, sets background and display its description
     */
    public void goToTrainStation()
    {
        if (gameStage.goToRoom("right"))
        {
            cardRowController.loadTrainCountries();
            outside.setVisible(false);
            train.setVisible(true);

            setStageName();
            appendDialog(getGameStage().getCurrentRoomDescription());
            localMap.setImage(new Image (getClass().getResourceAsStream("/images/Trainstation.png")));
        }

    }

    /**
     * Goes to culture room, sets background and display its description
     */
    public void  goToCulture()
    {
        if (gameStage.goToRoom("down"))
        {
            var items = getGameStage().getItemFromCurrentRoom();
            cultureController.setBackgroundImage(ImageReturner.cultureRoom(gameStage.getCurrentCountryName()));

            cultureController.setItem(items);


            outside.setVisible(false);
            culture.setVisible(true);
            setStageName();
            appendDialog(getGameStage().getCurrentRoomDescription());
            localMap.setImage(new Image (getClass().getResourceAsStream("/images/Culture.png")));
        }
    }

    /**
     * Setup the background of the outside room and display its description
     */
    private void setupOutsideRoom()
    {
        airport.setVisible(false);
        culture.setVisible(false);
        government.setVisible(false);
        train.setVisible(false);
        outside.setVisible(true);
        appendDialog(getGameStage().getCurrentRoomDescription());
        outsideController.setBackgroundImage(ImageReturner.outsideRoom(gameStage.getCurrentCountryName()));
        setStageName();
    }

    /**
     * Remove item from inventory both in backend and frontend
     * @param item Item to be removed
     */
    private void removeItemFromInventory(IItem item)
    {
        inventory.removeItem(item);
        inventoryItems.getItems().remove(item);
        appendDialog("Removed item: " + item.getName());
    }

    /**
     * Update display of your money
     */
    private void updateMoney()
    {
        money.setText("Money: " + gameStage.getConfig().getMoney());
        if (!getGameStage().gotEnoughMoneyToKeepPlaying())
        {
            //GAME SHOULD END HERE
            appendDialog("You dont have enough money to do anything");
            endGame();
        }
    }

    /**
     * return what endgame image should be displayed by points
     * @return end game image
     */
    private Image getEndGameImage()
    {
        var points = getGameStage().getEndGameResult().getSum();
        if (points > 2200)
        {
            return new Image(getClass().getResourceAsStream("/images/endGameImages/endGamePARADISE.png"));
        }
        else if (points > 1500)
        {
            return new Image(getClass().getResourceAsStream("/images/endGameImages/endGameStillBREATHING.png"));

        }
        else
        {
            return new Image(getClass().getResourceAsStream("/images/endGameImages/endGameHelloHell.png"));
        }
    }
    public void helpWindow(ActionEvent event) throws IOException {

        Stage helpWin = new Stage();
        helpWin.setTitle("Help Window");

        helpWin.initModality(Modality.APPLICATION_MODAL);

        //Making a popup window
        Parent root = FXMLLoader.load(HelpController.class.getResource("/fxml/Help.fxml"));
        Scene scene = new Scene(root);
        helpWin.setResizable(false);
        helpWin.setScene(scene);
        helpWin.showAndWait();


    }


    /**
     *  Change mainscreen to the endgame screen
     */
    private void endGame()
    {
        endScreenImage.setImage(getEndGameImage());
        endScreen.setVisible(true);
        appendDialog("Game have ended");
        var result = getGameStage().getEndGameResult();
        energyPoint.setText(String.valueOf(result.getEnergyPoint()));
        environmentPoint.setText(String.valueOf(result.getEnvironmentPoint()));
        sustainabilityPoint.setText(String.valueOf(result.getSustainabilityPoint()));
    }

    /**
     * Sets up listview for deal and item inventory
     */
    private void configureInventory() {
        inventoryDeals.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(IDeal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " | " + item.getCategory());
                }
            }
        });

        inventoryItems.setCellFactory(param -> new ListCell<>()
        {
            @Override
            protected void updateItem(IItem item, boolean empty) {
                super.updateItem( item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }

            }
        });
    }


}
