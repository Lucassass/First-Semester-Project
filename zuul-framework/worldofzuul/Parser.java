package worldofzuul;

import java.util.Scanner;
import java.util.StringTokenizer;

/*
 This parser reads user input and tries to interpret it as an "Adventure"
 command. Every time it is called it reads a line from the terminal and
 tries to interpret the line as a two word command. It returns the command
 as an object of class Command.
 The parser has a set of known command words. It checks user input against
 the known commands, and if the input is not one of the known commands, it
 returns a command object that is marked as an unknown command.
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

    public Command getCommand() 
    {
        String inputLine; 
        String word1 = null;
        String word2 = null;

        System.out.print("> "); // print prompt

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next(); // get word 1
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();  // get word 2
            }
        }

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        return new Command(commands.getCommandWord(word1), word2);
    }

    public void showCommands()
    {
        commands.showAll();
    }
}
