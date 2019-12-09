package com.SemesterProject.DomainLogic.Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

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
    private ArrayList<Deal> Deals;
    private String name;

    /**
     * Creates a room described called "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court yard".
     */
    public Room(String name, String description)
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<>();
        flyExits = new HashMap<>();
        trainExits = new HashMap<>();
        Deals = new ArrayList<>();
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Deal> getDeals() {
        return Deals;
    }

    public void setDeals(ArrayList<Deal> Deals) {
        this.Deals = Deals;
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
    public void addItem(Item[] items)
    {
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            item.setIndex(i);
            this.items.add(item);
        }
    }

    public void addItem(Item item)
    {
        this.items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }


    
    /**
     * Returns a long description of the room, in the following form:
     * "You are in the kitchen."
     * "Exits: north west"
     */
    public String getDescription()
    {
        return description;
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
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    /**
     *
     * @param countryName Name of the country you wanna get flypoints for
     * @return return a country or null
     */
    public Country getFlyExit(String countryName) {
        return flyExits.get(countryName.toLowerCase());
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
    public Country getTrainExit(String country) {
        return trainExits.get(country);
    }

    public ArrayList<Country> getTrainExits(){ return new ArrayList<>(trainExits.values());}


}

