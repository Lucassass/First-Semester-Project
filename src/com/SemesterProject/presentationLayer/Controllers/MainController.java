package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.DomainLogic.GameStage;
import com.SemesterProject.DomainLogic.Inventory;
import com.SemesterProject.Interfaces.IGameStage;
import com.SemesterProject.Interfaces.IInventory;
import com.SemesterProject.presentationLayer.Controllers.Card.CardRowController;
import com.SemesterProject.presentationLayer.Controllers.Room.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Application implements Initializable {

    public AnchorPane cardRow;

    public Label money;

    public TextArea dialog;
    //public ListView inventoryListView;
    public ListView<Item> inventoryItems;
    public ListView<Deal> inventoryDeals;

    @FXML
    private CardRowController cardRowController;

    @FXML private ImageView Local, Global;

    @FXML private GridPane mainWindow;
    @FXML private Label labelTest;

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

    private static IGameStage gameStage;
    private static IInventory inventory;

    private static Stage stage;

    public static IGameStage getGameStage() {
        return gameStage;
    }
    public static IInventory getInventory() { return inventory; }


    private void setStageName(){
        stage.setTitle(gameStage.getRoomName() + " | " + gameStage.getCountryName());
    }

    public CardRowController getCardRowController() {
        return cardRowController;
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
        updateMoney();
        setupOutsideRoom();


        inventoryItems.setCellFactory(param -> new ListCell<>()
        {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem( item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }

            }
        });

        inventoryDeals.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Deal item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " | " + item.getCategory());
                }
            }
        });
    }

    public void onQuitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void updateMoney()
    {
        money.setText("Money: " + gameStage.getConfig().getMoney());
        if (!getGameStage().gotEnoughMoneyToKeepPlaying())
        {
            //GAME SHOULD END HERE
            appendDialog("You dont have enough money to do anything");
        }
    }

    public void addDeal(Deal deal)
    {
        if (!getInventory().isFullOfDeals(deal))
        {
            if (getGameStage().getConfig().gotEnoughMoney(deal.getPrice()))
            {
                getGameStage().getConfig().subtractMoney(deal.getPrice());
                if (gameStage.takeDeal(deal, null))
                {
                    inventory.addDeal(deal);
                    appendDialog("Added deal: " + deal.getName() + " | " + deal.getCategory());
                    inventoryDeals.getItems().add(deal);
                }
                else
                {
                    appendDialog("You didn't get the deal :(");
                }
                gameStage.removeDealFromRoom(deal.getUuid());
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

    public void addItem(Item item)
    {
        if (!inventory.isFullOfItems())
        {
            inventory.addItem(item);
            appendDialog("Added item: " + item.getName());
            inventoryItems.getItems().add(item);
            gameStage.removeItemFromRoom(item.getUuid());
        }
        else
        {
            appendDialog("Inventory is full. Please remove an item from your inventory\n" +
                    "if you want to pick this one up");
        }

    }


    public void onDealRemove(ActionEvent actionEvent)
    {
        var item = inventoryDeals.getSelectionModel().getSelectedItem();

        inventory.removeDeal((Deal) item);
        inventoryDeals.getItems().remove(item);
        appendDialog("Removed deal: " + ((Deal) item).getName() + "| " + ((Deal) item).getCategory());
    }

    public void onItemSwitch(ActionEvent actionEvent)
    {
        var item = inventoryItems.getSelectionModel().getSelectedItem();

        if (gameStage.getRoomName().equalsIgnoreCase("culture"))
        {
            var itemInRoom = gameStage.getItemFromRoom();

            if (itemInRoom != null)
            {
                appendDialog("Switching " + item.getName() + " out with " + itemInRoom.getName());
                removeItem(item);
                cultureController.setItem(item);
                gameStage.addItemToRoom(item);
                addItem(itemInRoom);
            }
            else
            {
                appendDialog("Switching " + item.getName() + " out with nothing");
                removeItem(item);
                cultureController.setItem(item);
                gameStage.addItemToRoom(item);
            }
        }
        else{
            appendDialog("You can't switch items outside of the culture room");
        }

        //removeItem(item);
        //cultureController.setItem();
    }



    public void onItemUse(ActionEvent actionEvent)
    {
        var item = inventoryItems.getSelectionModel().getSelectedItem();

        if (gameStage.getRoomName().equalsIgnoreCase("government"))
        {
            appendDialog("You have used " + (item).getName());
            removeItem(item);
        }
        else {
            appendDialog("You can't use items outside of the government room");
        }
    }

    private void removeItem(Item item)
    {
        inventory.removeItem(item);
        inventoryItems.getItems().remove(item);
        appendDialog("Removed item: " + item.getName());
    }

    public void onMouseEnter()
    {
        mainWindow.setCursor(Cursor.HAND);
    }

    public void onMouseExit()
    {
        mainWindow.setCursor(Cursor.DEFAULT);
    }

    public void goToNewCountry()
    {
        updateMoney();
        outside.setVisible(true);
        airport.setVisible(false);
        government.setVisible(false);
        culture.setVisible(false);
        train.setVisible(false);
        setStageName();
        cardRowController.anchorPane.setVisible(false);
        appendDialog("Moving to: " + getGameStage().getCountryName());
    }

    public void goToAirport()
    {
        if (gameStage.goRoom("up"))
        {
            cardRowController.loadAirportChoice();
            outside.setVisible(false);
            airport.setVisible(true);

            setStageName();
            appendDialog(getGameStage().getRoomDescription());
            Local.setImage(new Image (getClass().getResourceAsStream("/images/Airport.png")));
        }

    }

    public void goToOutsideFrom(String direction)
    {
        if (gameStage.goRoom(direction))
        {
            setupOutsideRoom();
            Local.setImage(new Image (getClass().getResourceAsStream("/images/Outside.png")));
        }

    }

    public void goToGovernment()
    {
        if (gameStage.goRoom("left"))
        {
            cardRowController.loadDeals();
            outside.setVisible(false);
            government.setVisible(true);

            setStageName();
            appendDialog(getGameStage().getRoomDescription());
            Local.setImage(new Image (getClass().getResourceAsStream("/images/Goverment.png")));
        }

    }

    public void goToTrainStation()
    {
        if (gameStage.goRoom("right"))
        {
            cardRowController.loadTrainCountries();
            outside.setVisible(false);
            train.setVisible(true);

            setStageName();
            appendDialog(getGameStage().getRoomDescription());
            Local.setImage(new Image (getClass().getResourceAsStream("/images/Trainstation.png")));
        }

    }

    public void  goToCulture()
    {
        if (gameStage.goRoom("down"))
        {
            cultureController.setItem(getGameStage().getItemFromRoom());
            outside.setVisible(false);
            culture.setVisible(true);
            setStageName();
            appendDialog(getGameStage().getRoomDescription());
            Local.setImage(new Image (getClass().getResourceAsStream("/images/Culture.png")));
        }
    }

    private void setupOutsideRoom()
    {
        airport.setVisible(false);
        culture.setVisible(false);
        government.setVisible(false);
        train.setVisible(false);
        outside.setVisible(true);
        appendDialog(getGameStage().getRoomDescription());
    }

    public void appendDialog(String text)
    {
        dialog.appendText(text + "\n");
    }


}