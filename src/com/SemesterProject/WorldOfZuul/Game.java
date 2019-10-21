package com.SemesterProject.WorldOfZuul;

import com.SemesterProject.WorldOfZuul.Country.BaseCountry;
import com.SemesterProject.WorldOfZuul.Country.China;
import com.SemesterProject.WorldOfZuul.Country.USA;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private BaseCountry currentCountry;
    
// To Start game run main function bellow
 
    /*
    Create the game and initialise its internal map.
    */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * creates rooms and giv them their description.
     * Set exits.
     * Makes currentRoom = outside.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      /*
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        */

        BaseCountry usa, china;
        china = new China();
        usa = new USA();
        china.setExit("USA", usa);
        usa.setExit("China", china);

        currentCountry = usa;
        currentRoom = currentCountry.getStartRoom();
        //currentRoom = outside;
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
        while (! finished) {
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
        }else if (commandWord == CommandWord.FLY)
        {
            goCountry(command);
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
            }
            else {
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }

    private void goCountry(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String country = command.getSecondWord();

        BaseCountry nextRoom = currentCountry.getExit(country);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentCountry = nextRoom;
            currentRoom = currentCountry.getStartRoom();
            System.out.println(currentRoom.getLongDescription());
        }
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
