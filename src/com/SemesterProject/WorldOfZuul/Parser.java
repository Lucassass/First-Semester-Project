package com.SemesterProject.WorldOfZuul;

import java.util.Scanner;
import java.util.StringTokenizer;

/** 
 * The parser reads user input and tries to interpret it as a command. 
 * Every time it is called, it reads the line from the terminal and 
 * tries to interpret the line as a two word command. 
 * It then returns the command as an object of class command.
 * The parser has a set of known commands that it checks the user input against,
 * and if the input is not one of the known commands,
 * it will return a comman object that is marked as an unkown command.
 */
public class Parser 
{
    private CommandWords commands; // holds all valid command words
    private Scanner reader;

    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /** getCommand is the function that is used to */
    public Command getCommand() 
    {
        String inputLine; 
        String word1 = null;
        String word2 = null;

        System.out.print("> "); // print prompt

        /** Makes inputLine equall to the next typed command */
        inputLine = reader.nextLine();

        /** 
         * We make a Scanner that is a way of reading input.
         * The tokenizer breakes a String into several parts.
         * The if statement makes it possible to use the hasNext
         * function that makes us able to look for multiple words.
         */
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next(); /** Gets word 1 */
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();  /** Gets word 2 */
            }
        }

        /** Now we check whether this word is known. 
         * If so, create a command with it. 
         * If not, create a "null" command (unknown command). 
        */
        return new Command(commands.getCommandWord(word1), word2);
    }

    public void showCommands()
    {
        commands.showAll();
    }
}
