package com.SemesterProject.presentationLayer;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.presentationLayer.Controllers.Card.CountryCardController;
import com.SemesterProject.presentationLayer.Controllers.Card.DealCardController;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Enum.CardAirportType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardRowCreator
{
    private  GridPane gridPane;
    private int maxRow;
    private ArrayList<CountryCardController> countryCardControllers;
    private ArrayList<DealCardController> dealCardControllers;

    public CardRowCreator( GridPane gridPane, int maxRow)
    {
        this.gridPane = gridPane;
        this.maxRow = maxRow;
        countryCardControllers = new ArrayList<>();
        dealCardControllers = new ArrayList<>();
    }

    public ArrayList<CountryCardController> getCountryCardControllers() {
        return countryCardControllers;
    }

    public ArrayList<DealCardController> getDealCardControllers() {
        return dealCardControllers;
    }

    public GridPane generateAirportChoice()
    {
        addAirportChoice();
        return gridPane;
    }

    public GridPane generateDeals(List<Deal> deals)
    {
        addDeals(deals);
        return gridPane;
    }

    public GridPane generateCountries(List<String> countries, int price)
    {
        addCountries(countries, price);
        return gridPane;
    }

    private void addAirportChoice()
    {
        clearGridPane();

        CreateGridPane(3);


        addAirportChoiceCard(3, "Private", MainController.getGameStage().getConfig().getPrivateFlyingCost());
        addAirportChoiceCard(3, "Commercial", MainController.getGameStage().getConfig().getCommercialFlyingCost());
    }

    private void addCountries(List<String> countries, int price)
    {
        clearGridPane();

        CreateGridPane(countries.size());

        int iteration = 0;
        for (int i = 3; iteration < countries.size(); i += 2)
        {
            var country = countries.get(iteration);
            if (i > countries.size() * 2 - 2)
            {
                i = 3;
            }

            addAirportChoiceCard(i, country, price);

            iteration++;
        }

    }

    private void addAirportChoiceCard(int index, String name, int price)
    {
        try {
            var fxmlLoader = new FXMLLoader();

            Parent root = fxmlLoader.load(CardRowCreator.class.getResource("/fxml/Cards/AirportCard.fxml").openStream());

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().add(root);
            CountryCardController controller = fxmlLoader.getController();
            countryCardControllers.add(controller);
            setAirportButtonAction(controller, name);
            controller.populateCard(name, price);

            GridPane.setMargin(anchorPane, new Insets(50,0,0,0));

            gridPane.addColumn(index + 1, anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAirportButtonAction(CountryCardController cardController, String name)
    {
        if (CardAirportType.PRIVATE.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onPrivateClick);
        }
        else if (CardAirportType.CHINA.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onChinaClick);

        }
        else if (CardAirportType.COMMERCIAL.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onCommercialClick);

        }
        else if (CardAirportType.GERMANY.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onGermanyClick);

        }
        else if (CardAirportType.INDIA.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onIndiaClick);

        }
        else if (CardAirportType.JAPAN.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onJapanClick);

        }
        else if (CardAirportType.USA.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onUSAClick);

        }
        else if (CardAirportType.RUSSIA.getName().equalsIgnoreCase(name))
        {
            cardController.chooseButton.setOnAction(cardController::onRussiaClick);

        }
    }

    private void addDeals(List<Deal> deals)
    {
        if (deals == null) return;
        if (deals.size() > maxRow)
        {
            deals.remove(deals.size() -1);
        }

        clearGridPane();

        CreateGridPane(deals.size());
        fillDealGridPane(deals);

    }

    private void fillDealGridPane(List<Deal> deals)
    {
        int row = 0;

        for (int i = 1; i < deals.size()*2; i++)
        {
            if (numberIsOdd(i))
            {
                Deal deal = deals.get(row);
                addDealCardColumn(deal, i);
                row++;
            }
        }
    }

    private void CreateGridPane(int size)
    {
        double percentPerEmptyRow = 1;
        double cardPercent = getCardPercent(size, percentPerEmptyRow);

        addEmptyColumn(percentPerEmptyRow);

        for (int i = 1; i < size * 2; i++)
        {
            if (numberIsOdd(i)) {
                addColumnConstraints(cardPercent);
            } else {
                addEmptyColumn(percentPerEmptyRow);
            }
        }

        addEmptyColumn(percentPerEmptyRow);
    }

    private void addColumnConstraints(double widthPercent)
    {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(widthPercent);
        gridPane.getColumnConstraints().add(column);
    }

    private  boolean numberIsOdd(int number)
    {
        return number % 2 != 0;
    }

    private void addDealCardColumn(Deal deal, int index)
    {
        try {
            var fxmlLoader = new FXMLLoader();

            Parent root = fxmlLoader.load(CardRowCreator.class.getResource("/fxml/Cards/DealCard.fxml").openStream());

            AnchorPane anchorPane = new AnchorPane();
            anchorPane.getChildren().add(root);

            var controller = (DealCardController)fxmlLoader.getController();
            controller.populateCard(deal);
            dealCardControllers.add(controller);
            GridPane.setMargin(anchorPane, new Insets(50,0,0,0));

            gridPane.addColumn(index + 1, anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private  void addEmptyColumn(double widthPercent)
    {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(widthPercent);
        column.setMinWidth(0);
        gridPane.getColumnConstraints().add(column);
    }

    private void clearGridPane()
    {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        var test = new ColumnConstraints();
        test.setPercentWidth(-1);
        test.setMinWidth(-1);
        test.setPrefWidth(-1);
        test.setMaxWidth(-1);
        test.setHgrow(null);
        test.setFillWidth(true);
        test.setHalignment(null);
        gridPane.getColumnConstraints().add(test);
    }

    private  double getCardPercent(int size, double percentPerEmptyRow)
    {
        var emptyRows = 2 + size - 1;
        return (100 - (emptyRows*percentPerEmptyRow)) / size;
    }


}
