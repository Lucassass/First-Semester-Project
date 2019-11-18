package com.SemesterProject.iceandfire;
import java.util.HashMap;


public class CommandWords {

    // This is an array that holds all the valid command words
    private HashMap<String, CommandWord> validCommands;

    /**
     * The constructor that initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Allows us to call commandWords and check if they exist 
     * if they do not it will return UNKOWN.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Checks whether aString is a valid command word. 
     * Return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /*
     Prints all valid commands to System.out.
     */
    public void showAll() 
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
