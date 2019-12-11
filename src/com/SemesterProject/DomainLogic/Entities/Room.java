package com.SemesterProject.DomainLogic.Entities;

import com.SemesterProject.Interfaces.Entities.IDeal;
import com.SemesterProject.Interfaces.Entities.IItem;

import java.util.*;


public class Room
{
    private String description; /** Stores the description of rooms */
    private HashMap<String, Room> exits; /** Stores exits of the room. */
    private HashMap<String, Country> flyExits; /** Stores countries exits of the room. */
    private HashMap<String, Country> trainExits;
    private ArrayList<IItem> items;
    private ArrayList<IDeal> Deals;
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

    /**
     *
     * @return name of room
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return deals in room
     */
    public List<IDeal> getDeals() {
        return Deals;
    }

    void setDeals(ArrayList<IDeal> Deals) {
        this.Deals = Deals;
    }

    /**
     * Defines an exit from the room.
     */
    void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     *
     * @param countryName The name of the country you wanna set as flight exits
     * @param country The country it self
     */
    void setFlight(String countryName, Country country)
    {
        flyExits.put(countryName, country);
    }

    /**
     *
     * @param countryName The name of the country you wanna set as train exits
     * @param country The country it self
     */
    void setTrainExits(String countryName, Country country) {
        trainExits.put(countryName, country);
    }

    /**
     * Set items for the room
     * @param items Arraylist of items you wanna add to the room
     */
    void addItem(IItem[] items)
    {
        for (int i = 0; i < items.length; i++) {
            var item = items[i];
            item.setIndex(i);
            this.items.add(item);
        }
    }

    /**
     * add item
     * @param item to be added
     */
    public void addItem(IItem item)
    {
        this.items.add(item);
    }

    /**
     *
     * @return items of current room
     */
    public ArrayList<IItem> getItems() {
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

