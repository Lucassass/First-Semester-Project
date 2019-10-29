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
        Country usa, china, russia, japan, india, germany;
        china = new Country("China","Airport", "Train",
                "outside", "government", "culture", null);
        usa = new Country("USA","Airport", "Train",
                "outside", "government", "culture", null);
        russia = new Country("Russia", "Airport", "Train",
                "outside", "government", "culture", null);
        japan = new Country("Japan","Airport", "Train",
                "outside", "government", "culture", null);
        india = new Country("India","Airport", "Train",
                "outside", "government", "culture", null);
        germany = new Country("Germany", "Airport", "Train",
                "outside", "government", "culture", null);

        china.setFlyExit("USA", usa);
        usa.setFlyExit("China", china);
        china.setTrainExit("USA", usa);
        china.setTrainExit("Test", usa);
        usa.setTrainExit("China", china);
        usa.setTrainExit("Test", china);

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
    }

     /** 
      * Prints the opening message.
      */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**  
     * Given a command, process and executes the command.
     * If this command ends the game, true is returned, otherwise false is returned.
    */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.FLY)
        {
            fly();
        }
        else if (commandWord == CommandWord.TRAIN){
            goTrainStation(command);
        } else if (commandWord == CommandWord.GLOBALMAP){
            printGlobalMap();
        }
        else if (commandWord == CommandWord.LOCALMAP){
            printLocalMap();
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    /** 
     * Prints out the help information. 
     * Here we print some stupid, cryptic message and a list of the command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
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
            System.out.println("There is no direct line to " + country);
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
        if (!command.hasSecondWord()  && command.getCommandWord() != CommandWord.TRAIN ){
            System.out.println("Take the train to where?");
            return;
        }
        String country = command.getSecondWord();

        Country nextCountry = currentCountry.getTrainStationExit(country);

        if (nextCountry == null || currentRoom != currentCountry.getTrainStation()) {
            System.out.println("There is no station here!");
        }
        else {
            currentCountry = nextCountry;
            currentRoom = currentCountry.getStartRoom();
            System.out.println(currentRoom.getLongDescription());
        }
    }


    private void fly(){

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
            flyWithPublicPlane();
        }
    }

    private void flyWithPrivatePlane(String country)
    {
        goCountry(country);
    }

    private void flyWithPublicPlane(){


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
       System.out.println("You are currently in: "+ currentRoom);

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
