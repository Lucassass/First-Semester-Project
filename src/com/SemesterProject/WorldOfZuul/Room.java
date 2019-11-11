package com.SemesterProject.WorldOfZuul;

import java.util.ArrayList;
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
    private ArrayList<Item> items;
    private ArrayList<Deal> deals;
    private String name;

    /**
     * Creates a room described called "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court yard".
     */
    public Room(String description, String name)
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        flyExits = new HashMap<>();
        trainExits = new HashMap<>();
        items = new ArrayList<>();
        deals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Deal> getDeals() {
        return deals;
    }

    public void setDeals(ArrayList<Deal> deals) {
        this.deals = deals;
    }

    /**
     * Defines an exit from the room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     *
     * @param countryName The name of the country you wanna set as flight exits
     * @param country The country it self
     */
    public void setFlight(String countryName, Country country)
    {
        flyExits.put(countryName, country);
    }

    /**
     *
     * @param countryName The name of the country you wanna set as train exits
     * @param country The country it self
     */
    public void setTrainExits(String countryName, Country country) {
        trainExits.put(countryName, country);
    }

    /**
     * Set items for the room
     * @param items Arraylist of items you wanna add to the room
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Check whether or not room got fly points
     * @return true if the room got fly exits
     */
    public boolean gotFlyPoint()
    {
        return flyExits.size() != 0;
    }

    /**
     * Check whether or not room got train points
     * @return true if the room got train exits
     */
    public boolean gotTrainPoint(){return trainExits.size() != 0;}

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


    /**
     *
     * @return long description with flight exits
     */
    String getLongDescriptionWithFlights()
    {
        return "You are " + description + ".\nrooms " + getExitString() + "\nflights " + getFlightString();
    }


    /**
     *
     * @return long description with train exits
     */
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

    /**
     *
     * @return formatted string with fly exits
     */
    private String getFlightString()
    {
        return getFormattedString(flyExits.keySet());
    }

    /**
     *
     * @return formatted string with train exits
     */
    private String getTrainString()
    {
        return getFormattedString(trainExits.keySet());
    }

    /**
     * Added a space between all elements in a set
     * @param set The set of strings to be formatted
     * @return return a formatted string
     */
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

    /**
     *
     * @param countryName Name of the country you wanna get flypoints for
     * @return return a country or null
     */
    public Country getFlyExit(String countryName) {
        return flyExits.get(countryName);
    }

    /**
     *
     * @return all fly points
     */
    public ArrayList<Country> getFlyExits(){
        return new ArrayList<>(flyExits.values());
    }

    /**
     *
     * @param country Name of the country you wanna get train exists for
     * @return a country or null
     */
    public Country getTrainExits(String country) {
        return trainExits.get(country);
    }
}

