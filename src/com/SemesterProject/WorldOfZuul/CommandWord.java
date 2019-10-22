package com.SemesterProject.WorldOfZuul;

// enum makes CommandWord Final/Constant
public enum CommandWord {
    GO("go"),
    FLY("fly"),
    TRAIN("train"),
    LOCALMAP("localmap"),
    GLOBALMAP("globalmap"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?");
    
    private String commandString;
    
    /** 
     * Sets the commands GO("go"), QUIT("quit"), HELP("help"), 
     * and UNKNOWN("?") to be equal to the String appove (commanString)
    */
    CommandWord(String commandString){
        this.commandString = commandString;
    }
    
    // Used when we need a string representation of an object. 
    public String toString(){
        return commandString;
    }
}
