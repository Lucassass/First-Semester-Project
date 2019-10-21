package com.SemesterProject.WorldOfZuul.Country;

import com.SemesterProject.WorldOfZuul.Item;
import com.SemesterProject.WorldOfZuul.Room;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BaseCountry {

    private Room startRoom;
    private Room airPortRoom;
    private ArrayList<Item> items;

    private HashMap<String, BaseCountry> exits;

    public Room getStartRoom() {
        return startRoom;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    BaseCountry()
    {
        exits = new HashMap<>();
    }

    void setStartRoom(Room startRoom) {
        this.startRoom = startRoom;
    }

    void setAirPortRoom(Room airPortRoom) {
        this.airPortRoom = airPortRoom;
    }

    public void setExit(String countryName, BaseCountry country)
    {
        countryName = countryName.toLowerCase();
        airPortRoom.setFlight(countryName, country);
        exits.put(countryName,country);
    }

    public BaseCountry getExit(String countryName)
    {
        countryName = countryName.toLowerCase();
        return exits.get(countryName);
    }

    protected abstract void createRooms();

}
