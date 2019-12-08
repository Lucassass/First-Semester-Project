package com.SemesterProject.DomainLogic;

import com.SemesterProject.DomainLogic.Entities.Country;
import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.DomainLogic.Entities.Room;
import com.SemesterProject.DomainLogic.Enum.Countries;
import com.SemesterProject.DomainLogic.Enum.DealCategory;
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




    public String getRoomName() {
        return currentRoom.getName();
    }

    public String getCountryName() {
        return currentCountry.getName();
    }

    @Override
    public String getRoomDescription() {
        return currentRoom.getDescription();
    }

    public boolean goRoom(String direction)
    {
        var nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null)
        {
            currentRoom = nextRoom;
            return true;
        }

        return false;
    }

    @Override
    public List<Deal> getDealsFromRoom() {
        return currentRoom.getDeals();
    }


    @Override
    public boolean takeDeal(Deal deal, Item itemUsed) {
        return successfullyNegotiateDeal(deal, itemUsed);

    }

    @Override
    public boolean gotEnoughMoneyToKeepPlaying()
    {
        var cheapestDealInCountry = getCheapestDealInCountry();

        return config.getMoney() > cheapestDealInCountry || config.getMoney() > config.getLowestCost();
    }

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

    @Override
    public String quoteFromItemUsed(Item item)
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

    private boolean successfullyNegotiateDeal(Deal deal, Item itemUsed) {
        int diceResult;
        int roll = random.nextInt(6) + 1;

        diceResult = roll + advantages(itemUsed);
        return diceResult > 3; //returns true or false
    }


    private int advantages(Item item)
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


    @Override
    public boolean goCountry(String country, int price)
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

    @Override
    public boolean goRandomCountry(int price) {
        if (currentRoom.getFlyExits().isEmpty()) return false;

        var random = new Random().nextInt(currentRoom.getFlyExits().size());
        return goCountry(currentRoom.getFlyExits().get(random).getName(), price);

    }

    @Override
    public List<String> getFlyExist() {
        ArrayList<String> list = new ArrayList<>();
        for (var country : currentRoom.getFlyExits())
        {
            list.add(country.getName());
        }

        return list;
    }

    @Override
    public List<String> getTrainExist() {
        ArrayList<String> list = new ArrayList<>();
        for (var country : currentRoom.getTrainExits())
        {
            list.add(country.getName());
        }

        return list;
    }

    @Override
    public void removeDealFromRoom(UUID id) {
        for (var deal : currentRoom.getDeals())
        {
            if (deal.getUuid() == id)
            {
                currentRoom.getDeals().remove(deal);
                break;
            }
        }
    }

    @Override
    public void removeItemFromRoom(UUID uuid)
    {
        if (currentRoom.getItem().getUuid() == uuid)
        {
            currentRoom.setItem(null);
        }
    }

    public void addItemToRoom(Item item)
    {
        if (currentRoom.getName().equalsIgnoreCase("culture"))
        {
            currentRoom.setItem(item);
        }
    }




    @Override
    public IConfig getConfig() {
        return config;
    }

    @Override
    public Item getItemFromRoom() {
        return currentRoom.getItem();
    }




    private void createRooms()
    {

        var usaItem = new Item("Vodka", Countries.Russia, 2, Countries.India, -2);
        var usaDeals = new ArrayList<Deal>();
        usaDeals.add(new Deal("Friendship", DealCategory.Energy,1,1,1,290 ,"Friendship is magic"));
        usaDeals.add(new Deal("Huuu", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        usaDeals.add(new Deal("Huuu2", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        usaDeals.add(new Deal("Huuu3", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));

        var chinaItem = new Item("Frankfurter", Countries.Germany,2, Countries.Japan,-2);
        var chinaDeals = new ArrayList<Deal>();
        chinaDeals.add(new Deal("Coal", DealCategory.Energy,1,1,1,400, "Trololololo"));
        chinaDeals.add(new Deal("Huawei spyware", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var germanyItem = new Item("Sushi", Countries.Japan,2, Countries.USA,-2);
        var germanyDeals = new ArrayList<Deal>();
        germanyDeals.add(new Deal("waterfacility", DealCategory.Energy,1,1,1,400, "Trololololo"));
        germanyDeals.add(new Deal("German car manufacturering secrets", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var russiaItem = new Item("Curry", Countries.India,2, Countries.Russia,-2);
        var russiaDeals = new ArrayList<Deal>();
        russiaDeals.add(new Deal("garbage collection system", DealCategory.Energy,1,1,1,400, "Trololololo"));
        russiaDeals.add(new Deal("FSB", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var indiaItem = new Item("Not Curry", Countries.India,2, Countries.Russia,-2);
        var indiaDeals = new ArrayList<Deal>();
        indiaDeals.add(new Deal("Organic farming", DealCategory.Energy,1,1,1,400, "Trololololo"));
        indiaDeals.add(new Deal("Mumbai", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var japanItem = new Item("Sushi", Countries.Japan,2, Countries.USA,-2);
        var japanDeals = new ArrayList<Deal>();
        japanDeals.add(new Deal("nucler reactor", DealCategory.Energy,1,1,1,400, "Trololololo"));
        japanDeals.add(new Deal("Sushi secret", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));


        Country usa, china, russia, japan, india, germany;
        china = new Country("China", "Airport", "Train",
                "outside", "government", "culture", chinaItem, chinaDeals);
        usa = new Country("USA", "Airport Description", "Train ff",
                "outside gg", "government Jo", "culture qq", usaItem, usaDeals);
        russia = new Country("Russia", "Airport", "Train",
                "outside", "government", "culture", russiaItem, russiaDeals);
        japan = new Country("Japan", "Airport", "Train",
                "outside", "government", "culture", japanItem, japanDeals);
        india = new Country("India", "Airport", "Train",
                "outside", "government", "culture", indiaItem, indiaDeals);
        germany = new Country("Germany", "Airport", "Train",
                "outside", "government", "culture", germanyItem, germanyDeals);

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
