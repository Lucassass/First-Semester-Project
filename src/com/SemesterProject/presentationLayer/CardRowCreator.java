package com.SemesterProject.presentationLayer;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.Interfaces.Entities.IDeal;
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

    /**
     * Generates airport choices on the grid pane
     * @return the grid pane
     */
    public GridPane generateAirportChoice()
    {
        addAirportChoice();
        return gridPane;
    }

    /**
     * generates deals on the grid pane
     * @param deals
     * @return the grid pane
     */
    public GridPane generateDeals(List<IDeal> deals)
    {
        addDeals(deals);
        return gridPane;
    }

    /**
     * Genereates countries on the gridpane
     * @param countries the countries
     * @param price price for flying
     * @return the grid pane
     */
    public GridPane generateCountries(List<String> countries, int price)
    {
        addCountries(countries, price);
        return gridPane;
    }

    /**
     * Setup the gridpane for airport choices
     */
    private void addAirportChoice()
    {
        clearGridPane();

        CreateGridPane(3);


        addAirportChoiceCard(3, "Private", MainController.getGameStage().getConfig().getPrivateFlyingCost());
        addAirportChoiceCard(3, "Commercial", MainController.getGameStage().getConfig().getCommercialFlyingCost());
    }

    /**
     *
     * @param countries the countries to be added
     * @param price the price
     */
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

    /**
     *
     * @param index at what index to for the card to be places
     * @param name Name of the card
     * @param price Price of the card
     */
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

    /**
     * sets airport button's onAction event handler
     * @param cardController a reference to a card controller
     * @param name the name of the card
     */
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

    /**
     * Adds deals to the grid pane
     * @param deals The deals that shall be added
     */
    private void addDeals(List<IDeal> deals)
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

    /**
     * Fills the gridPane with deals
     * @param deals the deals the gridPane shall be filled with
     */
    private void fillDealGridPane(List<IDeal> deals)
    {
        int row = 0;

        for (int i = 1; i < deals.size()*2; i++)
        {
            if (numberIsOdd(i))
            {
                var deal = deals.get(row);
                addDealCardColumn(deal, i);
                row++;
            }
        }
    }

    /**
     * creates column in the gridpane
     * @param size the number of column to create
     */
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

    /**
     * Adds column constraints
     * @param widthPercent what width percent the column should have
     */
    private void addColumnConstraints(double widthPercent)
    {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(widthPercent);
        gridPane.getColumnConstraints().add(column);
    }

    /**
     * checks if number is odd
     * @param number number to check
     * @return return true if number is odd
     */
    private  boolean numberIsOdd(int number)
    {
        return number % 2 != 0;
    }

    /**
     * adds a deal to an column based on the index
     * @param deal The deal you want to be added
     * @param index at which index of the gridpane, the card should be placed
     */
    private void addDealCardColumn(IDeal deal, int index)
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

    /**
     * Add an empty column in grid pane
     * @param widthPercent the width percent you want the empty column to be
     */
    private  void addEmptyColumn(double widthPercent)
    {
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(widthPercent);
        column.setMinWidth(0);
        gridPane.getColumnConstraints().add(column);
    }

    /**
     * Clears grid pane and set it up with default values
     */
    private void clearGridPane()
    {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        var columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(-1);
        columnConstraints.setMinWidth(-1);
        columnConstraints.setPrefWidth(-1);
        columnConstraints.setMaxWidth(-1);
        columnConstraints.setHgrow(null);
        columnConstraints.setFillWidth(true);
        columnConstraints.setHalignment(null);
        gridPane.getColumnConstraints().add(columnConstraints);
    }

    /**
     * Calculate what width percent the cards should have
     * @param size number of cards
     * @param percentPerEmptyColumn the empty columns width in percentage
     * @return return percentage value
     */
    private  double getCardPercent(int size, double percentPerEmptyColumn)
    {
        var emptyRows = 2 + size - 1;
        return (100 - (emptyRows*percentPerEmptyColumn)) / size;
    }


}
