package com.SemesterProject.DomainLogic;

import com.SemesterProject.DomainLogic.Entities.Country;
import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.Entities.Room;
import com.SemesterProject.DomainLogic.Enum.CountryList;
import com.SemesterProject.DomainLogic.Enum.DealCategory;
import com.SemesterProject.Interfaces.IGameStage;
import com.SemesterProject.WorldOfZuul.Item;

import java.util.ArrayList;
import java.util.List;

public class GameStage implements IGameStage
{

    private Country currentCountry;
    private Room currentRoom;

    public GameStage()
    {
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
    public List<Deal> getDealsForRoom() {
        return currentRoom.getDeals();
    }


    private void createRooms()
    {

        var usaItems = new ArrayList<Item>();
        usaItems.add(new Item("Vodka", CountryList.Russia, 2,CountryList.India, -2));
        var usaDeals = new ArrayList<Deal>();
        usaDeals.add(new Deal("Friendship", DealCategory.Energy,1,1,1,290 ,"Friendship is magic"));
        usaDeals.add(new Deal("Huuu", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        usaDeals.add(new Deal("Huuu2", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        //usaDeals.add(new Deal("Huuu3", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));

        var chinaItems = new ArrayList<Item>();
        chinaItems.add(new Item("Frankfurter",CountryList.Germany,2,CountryList.Japan,-2));
        var chinaDeals = new ArrayList<Deal>();
        chinaDeals.add(new Deal("Coal", DealCategory.Energy,1,1,1,400, "Trololololo"));
        chinaDeals.add(new Deal("Huawei spyware", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var germanyItems = new ArrayList<Item>();
        germanyItems.add(new Item("Sushi",CountryList.Japan,2,CountryList.USA,-2));
        var germanyDeals = new ArrayList<Deal>();
        germanyDeals.add(new Deal("waterfacility", DealCategory.Energy,1,1,1,400, "Trololololo"));
        germanyDeals.add(new Deal("German car manufacturering secrets", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var russiaItems = new ArrayList<Item>();
        russiaItems.add(new Item("Curry",CountryList.India,2,CountryList.Russia,-2));
        var russiaDeals = new ArrayList<Deal>();
        russiaDeals.add(new Deal("garbage collection system", DealCategory.Energy,1,1,1,400, "Trololololo"));
        russiaDeals.add(new Deal("FSB", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var indiaItems = new ArrayList<Item>();
        indiaItems.add(new Item("Not Curry",CountryList.India,2,CountryList.Russia,-2));
        var indiaDeals = new ArrayList<Deal>();
        indiaDeals.add(new Deal("Organic farming", DealCategory.Energy,1,1,1,400, "Trololololo"));
        indiaDeals.add(new Deal("Mumbai", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));

        var japanItems = new ArrayList<Item>();
        japanItems.add(new Item("Sushi",CountryList.Japan,2,CountryList.USA,-2));
        var japanDeals = new ArrayList<Deal>();
        japanDeals.add(new Deal("nucler reactor", DealCategory.Energy,1,1,1,400, "Trololololo"));
        japanDeals.add(new Deal("Sushi secret", DealCategory.Knowledge,1,1,1,150, "Huhuhuhuhuh"));


        Country usa, china, russia, japan, india, germany;
        china = new Country("China", "Airport", "Train",
                "outside", "government", "culture", chinaItems, chinaDeals);
        usa = new Country("USA", "Airport Description", "Train ff",
                "outside gg", "government Jo", "culture qq", usaItems, usaDeals);
        russia = new Country("Russia", "Airport", "Train",
                "outside", "government", "culture", russiaItems, russiaDeals);
        japan = new Country("Japan", "Airport", "Train",
                "outside", "government", "culture", japanItems, japanDeals);
        india = new Country("India", "Airport", "Train",
                "outside", "government", "culture", indiaItems, indiaDeals);
        germany = new Country("Germany", "Airport", "Train",
                "outside", "government", "culture", germanyItems, germanyDeals);

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
        usa.setTrainExit("Japan", japan);
        usa.setTrainExit("Germany", germany);
        usa.setTrainExit("India", india);
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
        currentRoom = currentCountry.getStartRoomObsolete();
    }
}
