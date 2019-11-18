/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 */

package com.SemesterProject.iceandfire;

public class Command
{
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Creates a command object. First and second word must be defined, but
     * either one or both words can be null. If the command is only one command word
     * then the second command word should be null to indicate that the second command 
     * is not to be recognised by this game.
     */
    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

     /**
     * Returns the first part of the command.
     * If a command is not understood, the result will be null.
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

     /**
     * Returns the second part of the command. 
     * Returns null if there was no second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * Returns true if the command was not understood.
     */
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Returns true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

