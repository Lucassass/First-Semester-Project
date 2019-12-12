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
        var usaJadeDragon = new Item("Jade Dragon", Countries.China, 2, Countries.Japan, -2, "jadeDragon.png");
        var usaDeals = new ArrayList<IDeal>();
        usaDeals.add(new Deal("Nuclear Energy", DealCategory.Energy,100,200,-100,250 ,"USA produces more nuclear-generated electricity that any other country, nearly ⅓ of the world’s total. Which is good, considering they also use 18% of the world’s energy"));
        usaDeals.add(new Deal("Pool party", DealCategory.Energy,-100,-200,-50,150, "Living in sunny weather means, you need to cool down. And what better way to do that besides, getting your own pool. This way, you save energy on transportation and use a lot of water, which should help with the whole greenhouse effect"));
        usaDeals.add(new Deal("Ivy league Schools", DealCategory.Knowledge,200,200,200,300, "High prices colleges equals better education. With self payed education, the state cannot cut the curriculum or budget. This system is what makes the future"));

        var chinaVodka = new Item("Vodka", Countries.Russia,2, Countries.India,-2, "vodka.png");
        var chinaDeals = new ArrayList<IDeal>();
        chinaDeals.add(new Deal("The three Gorges Dam", DealCategory.Energy,300,300,250,600, "The most powerful hydroelectric project in the world is China’s Three Gorges Dam. The controversial and enormous power plant brings power to millions of Chinese villagers and will generate more than 22,000 megawatts from six generators"));
        chinaDeals.add(new Deal("Solar cell and Manufacturing", DealCategory.Energy,250,300,350,450, "China is one of the largest Solar manufacturing and installation, even surpassing Germany with 174 GW of solar energy"));
        chinaDeals.add(new Deal("Fossil Fuel", DealCategory.Energy,-100,300,-200,250, "God old burning of dinosaurs"));

        var germanyFlute = new Item("Flute", Countries.India,2, Countries.USA,-2, "flute.png");
        var germanyMatrojska = new Item("Matrojska", Countries.Russia,3, Countries.India,-2, "matrjosjka.png");
        var germanyDeals = new ArrayList<IDeal>();
        germanyDeals.add(new Deal("Solar Energy", DealCategory.Energy,250,250,150,550, "Germany wants to invest in clean energy, the best way for their country to do so are by solar energy are the best option. By investing in solar Energy in Germany the usage of fossil fuel will drop by 15%"));
        germanyDeals.add(new Deal("Coal reserves", DealCategory.Energy,-200,250,100,550, "Germany is willing to sell you some of their coal reserves, and even for a good price, by the amount of coal they’re selling  you can make enough energy to fuel your country for the next ten years"));
        germanyDeals.add(new Deal("New Universities", DealCategory.Knowledge,150,200,-200,350, "Germany wants to invest in their universities where they’re researching in the environment   "));

        var russiaKatana = new Item("Katana", Countries.Japan,2, Countries.China,-2, "katana.png");
        var russiaPanda = new Item("Panda", Countries.China,2, Countries.Germany,-1, "panda.png");
        var russiaDeals = new ArrayList<IDeal>();
        russiaDeals.add(new Deal("Hydropower", DealCategory.Food,100,200,100,550, "Russia wants to invest in clean energy, the best way for their country to do so are by Hydro power. By investing in hydro power in Russia the usage of fossil fuel will drop by 25%"));
        russiaDeals.add(new Deal("Oil", DealCategory.Energy,-250,400,-150,400, "Russia is willing to sell you some of their oil, and even for a good price, by the amount of coal they’re selling  you can make enough energy to fuel your country for the next fifteen years. "));
        russiaDeals.add(new Deal("Research", DealCategory.Knowledge,300,150,300,150, "Russia wants to invest in research for clean energy  the money will go to the universities so they can study  more sustainable clean energy sources"));

        var indiaSausage = new Item("Sausage", Countries.Germany,3, Countries.Russia,-1, "sausage.png");
        var indiaDeals = new ArrayList<IDeal>();
        indiaDeals.add(new Deal("Wind Energy", DealCategory.Energy,100,200,100,600, "India wants to invest in clean energy, the best way for their country to do so windmills are the best option. By investing in Wind Energy in India the usage of fossil fuel will drop by 20%"));
        indiaDeals.add(new Deal("Coal reserves", DealCategory.Energy,-200,300,-200,350, "India is willing to sell you some of their coal reserves, and even for a good price, by the amount of coal they’re selling  you can make enough energy to fuel your country for the next ten years"));
        indiaDeals.add(new Deal("Greater fundings for education and research", DealCategory.Food,200,100,150,400, "India wants to invest in education the money will go to the elementary schools and research about  options for more sustainable clean energy"));

        var japanGun = new Item("Gun", Countries.USA,2, Countries.Japan,-2, "gun.png");
        var japanCheeseBurger = new Item("Cheeseburger", Countries.USA,1, Countries.China,-1, "cheeseburger.png");
        var japanPretzel = new Item("Pretzel", Countries.Germany,2, Countries.USA,-2, "pretzel.png");
        var japanDeals = new ArrayList<IDeal>();
        japanDeals.add(new Deal("Hydropower", DealCategory.Energy,100,200,100,550, "Japan wants to invest in clean energy, the best way for their country to do so are by Hydro power. By investing in hydro power in japan the usage of fossil fuel will drop by 25%"));
        japanDeals.add(new Deal("Oil", DealCategory.Energy,-250,400,-150,400, "Japan is willing to sell you some of their oil, and even for a good price, by the amount of coal they’re selling  you can make enough energy to fuel your country for the next fifteen years"));
        japanDeals.add(new Deal("Research", DealCategory.Food,300,150,150,350, "Japan wants to invest in research for clean energy  the money will go to the universities so they can study  more sustainable clean energy sources."));


        Country usa, china, russia, japan, india, germany;
        china = new Country("China", "Airport", "Train",
                "outside", "government", "The Great Wall of China is" +
                " the collective name of a series of fortification systems generally built across the historical northern borders" +
                " of China to protect and consolidate territories of Chinese states and empires against various nomadic groups of the" +
                " steppe and their polities", chinaDeals, chinaVodka );
        usa = new Country("USA", "Airport Description", "Train",
                "outside", "government", "The White House is the official residence of the President" +
                " of the United State. This historic structure has been the home of every president except George Washington.", usaDeals, usaJadeDragon);
        russia = new Country("Russia", "Airport", "Train",
                "outside", "government", "The rich history of Red Square is reflected in paintings" +
                " by Vasily Surikov, Konstantin Yuon and others. The square was meant to serve as Moscow's main marketplace. It was also the site of" +
                " various public ceremonies and proclamations, and occasionally a coronation for Russia's Tsars would take place",  russiaDeals,russiaKatana, russiaPanda);
        japan = new Country("Japan", "Airport", "Train",
                "outside", "government", "Mount Fuji is one of Japan's Three Holy Mountains along" +
                " with Mount Tate and Mount Haku. It is also a Special Place of Scenic Beauty and one of Japan's Historic Sites" ,japanDeals, japanCheeseBurger, japanGun,
                japanPretzel);
        india = new Country("India", "Airport", "Train",
                "outside", "government", "Taj Mahal was built by Shah Jahan  for his wife Arjumans Bano Begum," +
                " after she died in childbirth. The construction of Taj Mahal began in 1632 and was finished in 1653. The Taj Mahal was designated as a UNESCO World" +
                " Heritage Site in 1983 for being the jewel of Muslim art in India and one of the universally admired masterpieces of the world's" +
                " heritage", indiaDeals, indiaSausage);
        germany = new Country("Germany", "Airport", "Train",
                "outside", "government", "The town of Quedlinburg is known to have existed since at least the early" +
                " 9th century, when there was a settlement known as Gross Orden on the eastern bank of the River Bode.According to legend, Henry had been offered the German" +
                " crown at Quedlinburg in 919 by Franconian nobles, giving rise to the town being called the cradle of the German Reich", germanyDeals, germanyFlute,
                germanyMatrojska);


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

        japan.setFlyExit("China", china);
        japan.setFlyExit("India", india);

        //Train exit
        china.setTrainExit("USA", usa);
        china.setTrainExit("Japan", japan);

        usa.setTrainExit("China", china);
        usa.setTrainExit("Russia", russia);

        germany.setTrainExit("Russia",russia);
        germany.setTrainExit("India", india);

        japan.setTrainExit("India", india);
        japan.setTrainExit("China", china);

        russia.setTrainExit("Germany", germany);
        russia.setTrainExit("Usa", usa);

        india.setTrainExit("Japan", japan);
        india.setTrainExit("Germany", germany);

        currentCountry = usa;
        currentRoom = currentCountry.getStartRoom();
    }
}
