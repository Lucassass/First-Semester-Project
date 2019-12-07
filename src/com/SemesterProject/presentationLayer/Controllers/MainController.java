package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.DomainLogic.GameStage;
import com.SemesterProject.Interfaces.IGameStage;
import com.SemesterProject.presentationLayer.Controllers.Card.CardRowController;
import com.SemesterProject.presentationLayer.Controllers.Room.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Application implements Initializable {

    public AnchorPane cardRow;

    public Label money;

    public TextArea dialog;

    @FXML
    private CardRowController cardRowController;


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

    private static Stage stage;

    public static IGameStage getGameStage() {
        return gameStage;
    }

    private void setStageName(){
        stage.setTitle(gameStage.getRoomName() + " | " + gameStage.getCountryName());
    }

    public CardRowController getCardRowController() {
        return cardRowController;
    }

    public static void main(String[] args)
    {
        gameStage = new GameStage();
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
    }

    public void onQuitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void updateMoney()
    {
        money.setText("Money: " + gameStage.getConfig().getMoney());
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

        }

    }

    public void goToOutsideFrom(String direction)
    {
        if (gameStage.goRoom(direction))
        {
            setupOutsideRoom();
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
        }

    }

    public void  goToCulture()
    {
        if (gameStage.goRoom("down"))
        {
            outside.setVisible(false);
            culture.setVisible(true);
            setStageName();
            appendDialog(getGameStage().getRoomDescription());

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
