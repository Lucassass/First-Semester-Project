package com.SemesterProject.iceandfire.presentation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreen extends Application implements Initializable {

    @FXML
    public MenuItem exit;
    @FXML
    public ListView listView;

    public static void main(String[] args) {
        // write your code here
/*

        Game game = new Game();
        game.play();
*/
        Application.launch(args);




    }


    @Override
    public void start(Stage stage) throws Exception
    {


        // Path to the FXML File
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void exitApplication(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Test1","Test2");
        listView.getItems().addAll(list);

    }
}
