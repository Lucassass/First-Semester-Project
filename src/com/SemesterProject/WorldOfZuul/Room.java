package com.SemesterProject.WorldOfZuul;

import java.util.Set;
import java.util.HashMap;

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
    private HashMap<String, Country> flyExits; /** Stores countries exits of the room. */
    private HashMap<String, Country> trainExits;


    /**
     * Creates a room described called "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        flyExits = new HashMap<>();
        trainExits = new HashMap<>();
    }

    /**
     * Defines an exit from the room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public void setFlight(String countryName, Country country)
    {
        flyExits.put(countryName, country);
    }

    public void setTrainExits(String countryName, Country country) {
        trainExits.put(countryName, country);
    }

    boolean gotFlyPoint()
    {
        return flyExits.size() != 0;
    }

    boolean gotTrainPoint(){return trainExits.size() != 0;}

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
    String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }


    String getLongDescriptionWithFlights()
    {
        return "You are " + description + ".\nrooms " + getExitString() + "\nflights " + getFlightString();
    }


    String getLongDescriptionWithTrains(){
        return "You are " + description + ".\nrooms " + getExitString() + "\ntrains " + getTrainString();
    }

    /**
     * Returns a String describing the room's exits, for example:
     * "Exits: north west".
     */
    private String getExitString()
    {
        return getFormattedString(exits.keySet());
    }

    private String getFlightString()
    {
        return getFormattedString(flyExits.keySet());
    }

    private String getTrainString()
    {
        return getFormattedString(trainExits.keySet());
    }

    private String getFormattedString(Set<String> set){
        String returnString = "Exits:";
        Set<String> keys = set;
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached, if we go from this room in direction x
     * "direction" will be printed. If there is no room in that direction, it returns null.
     */
    Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public Country getFlyExits(String country) {
        return flyExits.get(country);
    }

    public Country getTrainExits(String country) {
        return trainExits.get(country);
    }
}

