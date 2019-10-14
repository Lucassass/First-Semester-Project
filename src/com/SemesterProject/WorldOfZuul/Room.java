package com.SemesterProject.WorldOfZuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/*
*                               Map of rooms 
* 
*                               Computer Lab ------------- Computer admin Office 
*                                  |                        
*                                  |                         
*       Campus Pub ------------ Main entrence ------------- Lecture hall \
*/

public class Room 
{
    private String description; /** Stores the description of rooms */
    private HashMap<String, Room> exits; /** Stores exits of the room. */

    /**
     * Creates a room described called "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Defines an exit from the room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

     /**
     * Returns the description of the room (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    /**
     * Returns a long description of the room, in the following form:
     * "You are in the kitchen."
     * "Exits: north west"
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    /**
     * Returns a String describing the room's exits, for example:
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached, if we go from this room in direction x
     * "direction" will be printed. If there is no room in that direction, it returns null.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

