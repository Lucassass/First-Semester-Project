package com.SemesterProject.presentationLayer.Controllers;

import com.SemesterProject.DomainLogic.GameStage;
import com.SemesterProject.Interfaces.IGameStage;
import com.SemesterProject.presentationLayer.Controllers.Card.CardRowController;
import com.SemesterProject.presentationLayer.Controllers.Room.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Application implements Initializable {

    public AnchorPane cardRow;
    @FXML
    private CardRowController cardRowController;

    @FXML private ImageView gameWindowImage;

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

    public IGameStage getGameStage() {
        return gameStage;
    }

    private void setStageName(){
        stage.setTitle(gameStage.getRoomName() + " | " + gameStage.getCountryName());
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
        //cardRowController = new CardRowController(gameStage.getDealsForRoom());
        //addWindowSizeListener();

        setupOutsideRoom();
    }

    void onMouseEnter()
    {
        mainWindow.setCursor(Cursor.HAND);
    }

    void onMouseExit()
    {
        mainWindow.setCursor(Cursor.DEFAULT);
    }


    public void addWindowSizeListener()
    {
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            var height = mainWindow.getCellBounds(0,0).getHeight();
            var width = mainWindow.getCellBounds(0,0).getWidth();

            if (height != 0)
            {
                gameWindowImage.setFitHeight(height);
            }
            if (width != 0 )
            {
                gameWindowImage.setFitWidth(width);
            }



        };
        mainWindow.widthProperty().addListener(stageSizeListener);
        mainWindow.heightProperty().addListener(stageSizeListener);
    }


    public void goToAirport()
    {
        if (gameStage.goRoom("up"))
        {
            outside.setVisible(false);
            airport.setVisible(true);
            setStageName();
            airportController.setRoomDescription(getGameStage().getRoomDescription());
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
            governmentController.setRoomDescription(gameStage.getRoomDescription());
        }

    }

    public void goToTrainStation()
    {
        if (gameStage.goRoom("right"))
        {
            outside.setVisible(false);
            train.setVisible(true);
            setStageName();
            trainController.setRoomDescription(gameStage.getRoomDescription());

        }

    }

    public void  goToCulture()
    {
        if (gameStage.goRoom("down"))
        {
            outside.setVisible(false);
            culture.setVisible(true);
            setStageName();
            cultureController.setRoomDescription(gameStage.getRoomDescription());
        }
    }

    private void setupOutsideRoom()
    {
        airport.setVisible(false);
        culture.setVisible(false);
        government.setVisible(false);
        train.setVisible(false);
        outside.setVisible(true);
        outsideController.setRoomDescription(getGameStage().getRoomDescription());
    }





}
