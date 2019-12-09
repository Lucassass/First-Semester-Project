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
    @FXML private Label roomText;


    public void setBackgroundImage(Image backgroundImage) {
        this.backgroundImage.setImage(backgroundImage);
    }

    public void setRoomDescription(String description)
    {
        roomText.setText(description);
    }
    public void onMouseEntered(MouseEvent mouseEvent)
    {

        getController().onMouseEnter();
    }

    public void onMouseExited(MouseEvent mouseEvent)
    {
        getController().onMouseExit();
    }

    public void onClickAirport(MouseEvent mouseEvent) {

        getController().goToAirport();
    }

    public void onClickCulture(MouseEvent mouseEvent) {
        getController().goToCulture();
    }

    public void onClickGovernment(MouseEvent mouseEvent) {
        getController().goToGovernment();
    }

    public void onClickTrainStation(MouseEvent mouseEvent) {
        getController().goToTrainStation();
    }
}
