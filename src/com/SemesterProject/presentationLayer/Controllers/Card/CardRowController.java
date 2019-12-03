package com.SemesterProject.presentationLayer.Controllers.Card;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Injection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import java.io.IOException;
import java.util.List;


public class CardRowController extends Injection<MainController>
{
    @FXML
    public GridPane gridPanel;
    public AnchorPane anchorForClose;
    public SplitPane splitPane;
    public AnchorPane anchorPane;

    public void loadDeals() {

        addDeals(getController().getGameStage().getDealsForRoom());


        splitPane.lookupAll(".split-pane-divider")
                .forEach(div ->  div.setMouseTransparent(true) );
    }


    public void onClose(ActionEvent actionEvent)
    {
        anchorPane.setVisible(false);
    }

    private void addDeals(List<Deal> deals)
    {
        if (deals == null) return;
        if (deals.size() > 3)
        {
            deals.remove(deals.size() -1);
        }

        gridPanel.getChildren().clear();

        double percentPerEmptyRow = 1;
        int row = 0;
        double cardPercent = getCardPercent(deals.size(), percentPerEmptyRow);
        addEmptyColumn(percentPerEmptyRow);

        for (int i = 0; i < deals.size() + (deals.size() - 1); i++) {

            if (numberIsEven(i))
            {
                Deal deal = deals.get(row);
                addCardColumn(cardPercent, deal, i);
                row++;
            }
            else
            {
                addEmptyColumn(percentPerEmptyRow);
            }
        }
        addEmptyColumn(percentPerEmptyRow);
    }

    private boolean numberIsEven(int number)
    {
        return number % 2 == 0;
    }

    private void addCardColumn(double widthPercent, Deal deal, int index) {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(widthPercent);
        gridPanel.getColumnConstraints().add(column);

        try {
            var fxmlLoader = new FXMLLoader();

            Parent root = fxmlLoader.load(getClass().getResource("/fxml/DealCard.fxml").openStream());

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().add(root);

            var controller = (CardController)fxmlLoader.getController();
            controller.populateCard(deal);

            GridPane.setMargin(anchorPane, new Insets(150,0,0,0));

            gridPanel.addColumn(index + 1, anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void addEmptyColumn(double widthPercent)
    {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(widthPercent);
        column.setHgrow(Priority.SOMETIMES);
        column.setMinWidth(0);
        column.setPrefWidth(0);
        gridPanel.getColumnConstraints().add(column);
    }

    private double getCardPercent(int size, double percentPerEmptyRow)
    {
        var emptyRows = 2 + size - 1;
        return 100 - (emptyRows*percentPerEmptyRow) / size;
    }



}
