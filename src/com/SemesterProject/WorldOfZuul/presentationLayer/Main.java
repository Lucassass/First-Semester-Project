package com.SemesterProject.WorldOfZuul.presentationLayer;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application implements Initializable {

    @FXML
    public ImageView playImage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("presentation.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Ice and Fire");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playImage.setPreserveRatio(true);
        playImage.pickOnBoundsProperty();


    }
}
