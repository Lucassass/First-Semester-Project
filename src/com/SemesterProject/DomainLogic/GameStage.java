package com.SemesterProject.DomainLogic;

import com.SemesterProject.DomainLogic.Entities.*;
import com.SemesterProject.DomainLogic.Enum.Countries;
import com.SemesterProject.DomainLogic.Enum.DealCategory;
import com.SemesterProject.Interfaces.Entities.IDeal;
import com.SemesterProject.Interfaces.Entities.IItem;
import com.SemesterProject.Interfaces.IConfig;
import com.SemesterProject.Interfaces.IGameStage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GameStage implements IGameStage
{

    private Country currentCountry;
    private Room currentRoom;
    private IConfig config;
    private Random random = new Random();
    private Inventory inventory = Inventory.getInstance();

    public GameStage()
    {
        config = new Config();
        createRooms();

    }


    /**
     *
     * @return current room's name
     */
    @Override
    public String getCurrentRoomName() {
        return currentRoom.getName();
    }

    /**
     *
     * @return current country name
     */
    @Override
    public String getCurrentCountryName() {
        return currentCountry.getName();
    }

    /**
     *
     * @return items from current room
     */
    @Override
    public ArrayList<IItem> getItemFromCurrentRoom() {
        return currentRoom.getItems();
    }

    /**
     *
     * @return current room's description
     */
    @Override
    public String getCurrentRoomDescription() {
        return currentRoom.getDescription();
    }

    /**
     * Go to room
     * @param direction the direction is room is in
     * @return true if room existed and successfully moved to that room
     */
    @Override
    public boolean goToRoom(String direction)
    {
        var nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null)
        {
            currentRoom = nextRoom;
            return true;
        }

        return false;
    }

    /**
     *
     * @return deal from current room
     */
    @Override
    public List<IDeal> getDealsFromCurrentRoom() {
        return currentRoom.getDeals();
    }


    /**
     * trying to take the deal
     * @param deal
     * @param itemUsed item that have been used
     * @return return true if you got the deal
     */
    @Override
    public boolean takeDeal(IDeal deal, IItem itemUsed) {
        return successfullyNegotiateDeal(itemUsed);

    }

    /**
     * Calculate if you have enough money to do anything
     * @return true if you got enough money to keep playing
     */
    @Override
    public boolean gotEnoughMoneyToKeepPlaying()
    {
        var cheapestDealInCountry = getCheapestDealInCountry();

        return (config.getMoney() > cheapestDealInCountry && cheapestDealInCountry != 0) ||
                config.getMoney() > config.getLowestCost();
    }


    /**
     *
     * @return config class
     */
    @Override
    public IConfig getConfig() {
        return config;
    }


    /**
     * get the correct quote from item
     * @param item item to get the quote from
     * @return quote from item used
     */
    @Override
    public String getQuoteFromItemUsed(IItem item)
    {
        if (currentCountry.getName().equalsIgnoreCase(item.getCountryGood().getName()))
        {
            return item.getTextGood() + "\n it gave " + item.getPointsGood();
        }
        else if (currentCountry.getName().equalsIgnoreCase(item.getCountryBad().getName()))
        {
            return item.getTextBad() + "\n it gave " + item.getPointsBad();
        }
        return "It did nothing";
    }

    /**
     * Generate EndGameResult object with the correct information
     * @return the endgame result
     */
    @Override
    public EndGameResult getEndGameResult()
    {
        int energyPoint = 0;
        int sustainabilityPoint = 0;
        int environmentPoint = 0;

        var dealCategories = inventory.getDeals();
        for (var dealCategory: dealCategories) {
            for (var deal : dealCategory)
            {
                energyPoint += deal.getEnergyPoints();
                sustainabilityPoint += deal.getSustainabilityPoints();
                environmentPoint += deal.getEnvironmentPoints();
            }
        }
        return new EndGameResult(environmentPoint, sustainabilityPoint, energyPoint);
    }



    /**
     * go to country
     * @param country The name of the country
     * @param price the price of the county
     * @return true if successfully got to the specified country
     */
    @Override
    public boolean goToCountry(String country, int price)
    {
        if (!config.gotEnoughMoney(price)) return false;

        config.subtractMoney(price);


        var nextCountry = currentRoom.getFlyExit(country);
        if (nextCountry == null) {
            nextCountry = currentRoom.getTrainExit(country);
        }

        if (nextCountry != null)
        {
            currentCountry = nextCountry;
            currentRoom = currentCountry.getStartRoom();
            return true;
        }


        return false;
    }

    /**
     * go to random country
     * @param price of going to random country
     * @return true if successfully got to the specified country
     */
    @Override
    public boolean goToRandomCountry(int price) {
        if (currentRoom.getFlyExits().isEmpty()) return false;

        var random = new Random().nextInt(currentRoom.getFlyExits().size());
        return goToCountry(currentRoom.getFlyExits().get(random).getName(), price);

    }

    /**
     *
     * @return list of countries to fly to from current room
     */
    @Override
    public List<String> getFlyExistFromCurrentRoom() {
        ArrayList<String> list = new ArrayList<>();
        for (var country : currentRoom.getFlyExits())
        {
            list.add(country.getName());
        }

        return list;
    }

    /**
     *
     * @return list of countries to take the train to from current room
     */
    @Override
    public List<String> getTrainExistFromCurrentRoom() {
        ArrayList<String> list = new ArrayList<>();
        for (var country : currentRoom.getTrainExits())
        {
            list.add(country.getName());
        }

        return list;
    }

    /**
     * remove deal from the current room
     * @param id the deal ID
     */
    @Override
    public void removeDealFromCurrentRoom(UUID id) {
        for (var deal : currentRoom.getDeals())
        {
            if (deal.getUuid() == id)
            {
                currentRoom.getDeals().remove(deal);
                break;
            }
        }
    }

    /**
     *  remove item from current room
     * @param uuid the item id
     */
    @Override
    public void removeItemFromCurrentRoom(UUID uuid)
    {
        for (var item : currentRoom.getItems())
        {
            if (item.getUuid() == uuid)
            {
                currentRoom.getItems().remove(item);
                break;
            }
        }

    }

    /**
     * add item to current room
     * @param item to be added to the current room
     */
    public void addItemToCurrentRoom(IItem item)
    {
        if (currentRoom.getName().equalsIgnoreCase("culture"))
        {
            currentRoom.addItem(item);
        }
    }


    /**
     * Finds the cheapest deal in the current country
     * @return the price of the cheapest deal in country
     */
    private int getCheapestDealInCountry()
    {
        int lowestCost = 0;

        for (var deal : currentCountry.getGovernmentRoom().getDeals())
        {
            if (deal.getPrice() > lowestCost)
            {
                lowestCost = deal.getPrice();
            }
        }

        return lowestCost;
    }


    /**
     * rolls a dice, and add advantages if the item that was used, was correctly used
     * @param itemUsed item being used under negotiation
     * @return true if you successfully negotiated the deal
     */
    private boolean successfullyNegotiateDeal(IItem itemUsed) {
        int diceResult;
        int roll = random.nextInt(6) + 1;

        diceResult = roll + advantages(itemUsed);
        return diceResult > 3; //returns true or false
    }


    /**
     * calculate the advantages an item gives you
     * @param item item to check
     * @return return the advantages the item gave (can give minus)
     */
    private int advantages(IItem item)
    {
        if (item == null) return 0;
        if (currentCountry.getName().equalsIgnoreCase(item.getCountryGood().getName()))
        {
            return item.getPointsGood();
        }
        else if (currentCountry.getName().equalsIgnoreCase(item.getCountryBad().getName()))
        {
            return item.getPointsBad();
        }

        return 0;
    }

    /**
     * Creates the game
     */
    private void createRooms()
    {

        var usaItem = new Item("jadeDragon", Countries.Russia, 2, Countries.India, -2, "jadeDragon.png");
        var usaItem2 = new Item("jadeDragon", Countries.Russia, 2, Countries.India, -2, "jadeDragon.png");
        var usaItem3 = new Item("jadeDragon", Countries.Russia, 2, Countries.India, -2, "jadeDragon.png");
        var usaDeals = new ArrayList<IDeal>();
        usaDeals.add(new Deal("Friendship", DealCategory.Energy,1,1,1,290 ,"Friendship is magic"));
        usaDeals.add(new Deal("Huuu", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        usaDeals.add(new Deal("Huuu2", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        usaDeals.add(new Deal("Huuu3", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));

        var chinaItem = new Item("Vodka", Countries.Germany,2, Countries.Japan,-2, "vodka.png");
        var chinaDeals = new ArrayList<IDeal>();
        chinaDeals.add(new Deal("Coal", DealCategory.Energy,1,1,1,400, "Trololololo"));
        chinaDeals.add(new Deal("Huawei spyware", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var germanyItem = new Item("Flute", Countries.Japan,2, Countries.USA,-2, "flute.png");
        var germanyDeals = new ArrayList<IDeal>();
        germanyDeals.add(new Deal("waterfacility", DealCategory.Energy,1,1,1,400, "Trololololo"));
        germanyDeals.add(new Deal("German car manufacturering secrets", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var russiaItem = new Item("Katana", Countries.India,2, Countries.Russia,-2, "katana.png");
        var russiaDeals = new ArrayList<IDeal>();
        russiaDeals.add(new Deal("garbage collection system", DealCategory.Energy,1,1,1,400, "Trololololo"));
        russiaDeals.add(new Deal("FSB", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var indiaItem = new Item("Sausage", Countries.India,2, Countries.Russia,-2, "sausage.png");
        var indiaDeals = new ArrayList<IDeal>();
        indiaDeals.add(new Deal("Organic farming", DealCategory.Energy,1,1,1,400, "Trololololo"));
        indiaDeals.add(new Deal("Mumbai", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var japanItem = new Item("Gun", Countries.Japan,2, Countries.USA,-2, "gun.png");
        var japanDeals = new ArrayList<IDeal>();
        japanDeals.add(new Deal("nucler reactor", DealCategory.Energy,1,1,1,400, "Trololololo"));
        japanDeals.add(new Deal("Sushi secret", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));


        Country usa, china, russia, japan, india, germany;
        china = new Country("China", "Airport", "Train",
                "outside", "government", "culture", chinaDeals, chinaItem );
        usa = new Country("USA", "Airport Description", "Train ff",
                "outside gg", "government Jo", "culture qq",
                usaDeals, usaItem, usaItem2, usaItem3);
        russia = new Country("Russia", "Airport", "Train",
                "outside", "government", "culture",  russiaDeals,russiaItem);
        japan = new Country("Japan", "Airport", "Train",
                "outside", "government", "culture" ,japanDeals, japanItem);
        india = new Country("India", "Airport", "Train",
                "outside", "government", "culture", indiaDeals, indiaItem);
        germany = new Country("Germany", "Airport", "Train",
                "outside", "government", "culture", germanyDeals, germanyItem);

        //Fly exist
        china.setFlyExit("USA", usa);
        china.setFlyExit("Russia", russia);
        china.setFlyExit("Japan", japan);
        china.setFlyExit("India", india);
        china.setFlyExit("Germany", germany);

        usa.setFlyExit("China", china);
        usa.setFlyExit("Russia", russia);
        usa.setFlyExit("Japan", japan);
        usa.setFlyExit("India", india);
        usa.setFlyExit("Germany", germany);

        russia.setFlyExit("Japan",japan);
        russia.setFlyExit("Russia", russia);
        russia.setFlyExit("USA", usa);
        russia.setFlyExit("India", india);
        russia.setFlyExit("Germany", germany);

        india.setFlyExit("Germany", germany);
        india.setFlyExit("Russia", russia);
        india.setFlyExit("Japan", japan);
        india.setFlyExit("India", india);
        india.setFlyExit("USA", usa);

        germany.setFlyExit("USA", usa);
        germany.setFlyExit("China", china);
        germany.setFlyExit("Japan", japan);
        germany.setFlyExit("Russia", russia);
        germany.setFlyExit("India", india);

        japan.setFlyExit("USA", usa);
        japan.setFlyExit("China", china);
        japan.setFlyExit("Germany", germany);
        japan.setFlyExit("Russia", russia);
        japan.setFlyExit("India", india);

        //Train exit
        china.setTrainExit("USA", usa);
        china.setTrainExit("Germany", germany);
        china.setTrainExit("Japan", japan);
        china.setTrainExit("Russia", russia);
        china.setTrainExit("India", india);

        usa.setTrainExit("China", china);
        usa.setTrainExit("Russia", russia);

        germany.setTrainExit("Russia",russia);
        germany.setTrainExit("India", india);
        germany.setTrainExit("China", china);
        germany.setTrainExit("Japan", japan);
        germany.setTrainExit("Usa", usa);

        japan.setTrainExit("Russia",russia);
        japan.setTrainExit("India", india);
        japan.setTrainExit("China", china);
        japan.setTrainExit("Germany", japan);
        japan.setTrainExit("Usa", usa);

        russia.setTrainExit("India", india);
        russia.setTrainExit("Germany", germany);
        russia.setTrainExit("China", china);
        russia.setTrainExit("Japan", japan);
        russia.setTrainExit("Usa", usa);

        india.setTrainExit("Japan", japan);
        india.setTrainExit("Germany", germany);
        india.setTrainExit("China", china);
        india.setTrainExit("Japan", japan);
        india.setTrainExit("Usa", usa);

        currentCountry = usa;
        currentRoom = currentCountry.getStartRoom();
    }
}
