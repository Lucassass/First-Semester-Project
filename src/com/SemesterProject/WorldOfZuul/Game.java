package com.SemesterProject.WorldOfZuul;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Country currentCountry;

// To Start game run main function bellow
 
    /*
    Create the game and initialise its internal map.
    */
    public Game()
    {
        parser = new Parser();
        createRooms();
    }

    /**
     * creates rooms and giv them their description.
     * Set exits.
     * Makes currentRoom = outside.
     */
    private void createRooms()
    {

        var usaItems = new ArrayList<Item>();
        usaItems.add(new Item("Vodka", CountryList.Russia, 2,CountryList.India, -2));
        var usaDeals = new ArrayList<Deal>();
        usaDeals.add(new Deal("Friendship", DealCategory.Energy,1,1,1,290 ,"Friendship is magic"));
        usaDeals.add(new Deal("Huuu", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        usaDeals.add(new Deal("Huuu2", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));
        usaDeals.add(new Deal("Huuu3", DealCategory.Food,1,1,1,290, "Huuuuuuuuuuuuu"));

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
           usa = new Country("USA", "Airport", "Train",
                    "outside", "government", "culture", usaItems, usaDeals);
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
        currentRoom = currentCountry.getStartRoom();
    }

    /**
     * play() loops until end of play.
     */
    public void play() 
    {            
        /**
         * Print the welcome command
         */
        printWelcome();

        /**
         * The command loop.  
         * Here we repeatedly read commands and execute them until the game is over.        
         */
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
        System.out.println("Result:");
        System.out.println();
        Config.printPoints();
    }

     /** 
      * Prints the opening message.
      */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Ice and Fire!");
        System.out.println("Ice and Fire is a new, incredibly awesome adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**  
     * Given a command, process and executes the command.
     * If this command ends the game, true is returned, otherwise false is returned.
    */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        } if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            goRoom(command);
        } else if (commandWord == CommandWord.FLY) {
            fly();
        } else if (commandWord == CommandWord.TRAIN) {
            goTrainStation(command);
        } else if (commandWord == CommandWord.GLOBALMAP) {
            printGlobalMap();
        } else if (commandWord == CommandWord.LOCALMAP) {
            printLocalMap();
        }
        else if (commandWord == CommandWord.STARTDEAL) {
            Config.startDeal(currentRoom.getDeals(), currentCountry);
        }
        else if (commandWord == CommandWord.INVENTORY){
            Inventory in = Inventory.getInstance();
            in.printInventoryItem(in.getInventoryItem());
        }
        else if(commandWord == CommandWord.BUDGET)
        {
            printMoney();
        }
        else if (commandWord == CommandWord.DEALS){
            Inventory in = Inventory.getInstance();
            in.printInventoryDeals();
        }
        else if (commandWord == CommandWord.SEARCH) {
            Inventory in = Inventory.getInstance();
            in.searchForItems(currentRoom.getItems());
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }


    private void printMoney(){
        System.out.println("Money: " + Config.getMoney());
    }

    /** 
     * Prints out the help information. 
     * Here we print some stupid, cryptic message and a list of the command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the " + currentRoom.getName() +" in " + currentCountry.getName());
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * goRoom allows us to move from one room to another
     * If second command is null, it will print "Go where?"
     * Otherwise the second command will be compaired with the direction
     * If direction is not possible(null) it will print "There is no door!"
     * If there is a direction you will go to the next room asociated with the direction 
     * Then it will print the Long description of the room you went into 
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            if (currentRoom.gotFlyPoint())
            {
                System.out.println(currentRoom.getLongDescriptionWithFlights());
            } else if(currentRoom.gotTrainPoint())
            {
                System.out.println(currentRoom.getLongDescriptionWithTrains());
            }
            else {
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }

    private void goCountry(String country)
    {
        Country nextCountry = currentCountry.getAirPortExit(country);

        if (nextCountry == null) {
            System.out.println("Cannot find a line from"+currentCountry.getName()+"to " + country);
        }
        else {
            System.out.println("Flying to: " + nextCountry.getName());
            currentCountry = nextCountry;
            currentRoom = currentCountry.getStartRoom();
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private void goTrainStation(Command command)
    {
        String country = command.getSecondWord();

        Country nextCountry = currentCountry.getTrainStationExit(country);

        if (!Config.gotMoneyForTrain())
        {
            System.out.println("Sorry you dont have enough money");
            return;
        }
        else {
            Config.buyTrainTicket();
        }

        if (nextCountry == null || currentRoom != currentCountry.getTrainStation()) {
            System.out.println("There is no station here!");
        }
        else {
            System.out.println("Taking the train to: " + nextCountry.getName());
            currentCountry = nextCountry;
            currentRoom = currentCountry.getStartRoom();
            System.out.println(currentRoom.getLongDescription());
        }
    }


    private void fly()
    {
        if (currentRoom != currentCountry.getAirPortRoom()){
            System.out.println("You are not at a airport");
            return;
        }

        System.out.println("Private or commercial plane?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("private")){
            System.out.println("Where?");
            input = scanner.nextLine().toLowerCase();
            flyWithPrivatePlane(input);
        } else if (input.equals("commercial")){
            flyWithCommercialPlane();
        }
    }

    private void flyWithPrivatePlane(String country)
    {
        if (!Config.gotMoneyForPrivateFlying())
        {
            System.out.println("Sorry you dont have enough money");
            return;
        }
        else
        {
            Config.buyFlyPrivate();
        }
        goCountry(country);
    }

    private void flyWithCommercialPlane(){

        if (!Config.gotMoneyForCommercialFlying())
        {
            System.out.println("Sorry you dont have enough money");
            return;
        }
        else{
            Config.buyFlyCommercial();
        }

        var countries = currentCountry.getAirportExits();

        var random = new Random().nextInt(countries.size());

        goCountry(countries.get(random).getName().toLowerCase());
    }



    private void printLocalMap()
    {
        System.out.println();
        System.out.println("                            Airport ");
        System.out.println("                               |");
        System.out.println("                               |");
        System.out.println("Goverment --------------- Main entrence ------------- Trainstation");
        System.out.println("                               |");
        System.out.println("                               |");
        System.out.println("                          Cultur room");
       System.out.println("You are currently in: "+ currentRoom.getName());

    }

    private void printGlobalMap()
    {
        System.out.println();
        System.out.println("India   Germany   Japan");
        System.out.println(" \\        |        /");
        System.out.println("  \\       |       /");
        System.out.println("   \\      |      /");
        System.out.println("    \\     |     /");
        System.out.println("     \\    |    /");
        System.out.println("      \\   |   /");
        System.out.println("       \\  |  /");
        System.out.println("        \\ | /");
        System.out.println("        / | \\ ");
        System.out.println("       /  |  \\ ");
        System.out.println("      /   |   \\ ");
        System.out.println("     /    |    \\ ");
        System.out.println("    /     |     \\ ");
        System.out.println("   /      |      \\ ");
        System.out.println("  /       |       \\ ");
        System.out.println(" /        |        \\ ");
        System.out.println("China   Russia    USA");
        System.out.println();
        System.out.println("You are currently in: "+currentCountry.getName());
    }

    /**
     * "Quit" was entered. Checks the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * really quits the game, false otherwise. 
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
