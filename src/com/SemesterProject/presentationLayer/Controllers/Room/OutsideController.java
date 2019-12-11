package com.SemesterProject.presentationLayer.Controllers.Room;

import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OutsideController extends Injection<MainController>
{


    @FXML
    private ImageView backgroundImage;

    /**
     * sets the background image
     * @param backgroundImage the image you want to be displayed in the background
     */
    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage.setImage(backgroundImage);
    }

    /**
     * go to airport room
     */
    public void onClickAirport(MouseEvent mouseEvent) {

        getController().goToAirport();
    }

    /**
     * go to culture room
     */
    public void onClickCulture(MouseEvent mouseEvent) {
        getController().goToCulture();
    }

    /**
     * go to government room
     */
    public void onClickGovernment(MouseEvent mouseEvent) {
        getController().goToGovernment();
    }

    /**
     * go to train station
     */
    public void onClickTrainStation(MouseEvent mouseEvent) {
        getController().goToTrainStation();
    }
}
