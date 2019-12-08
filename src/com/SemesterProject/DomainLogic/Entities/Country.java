package com.SemesterProject.DomainLogic.Entities;

import java.util.ArrayList;

public class Country {

    private Room startRoom;
    private Room airPortRoom;
    private Room trainStation;
    private Room governmentRoom;
    private String name;


    public Country(String name, String airportDescription, String trainStationDescription, String outsideDescription,
                   String governmentDescription, String cultureDescription, Item item, ArrayList<Deal> dealObsoletes)
    {
        this.name = name;
        var airport = new Room("Airport",airportDescription);
        var government = new Room("Government",governmentDescription);
        var outside = new Room("Outside",outsideDescription);
        var trainStation = new Room("Train station",trainStationDescription);
        var culture = new Room("Culture",cultureDescription);


        airport.setExit("down", outside);
        government.setExit("right", outside);
        trainStation.setExit("left", outside);
        culture.setExit("up", outside);
        outside.setExit("up", airport);
        outside.setExit("down", culture);
        outside.setExit("left", government);
        outside.setExit("right", trainStation);
        culture.setItem(item);
        government.setDeals(dealObsoletes);

        governmentRoom = government;
        setStartRoom(outside);
        setAirPortRoom(airport);
        setTrainStation(trainStation);
    }

    public Room getGovernmentRoom() {
        return governmentRoom;
    }

    /**
     *
     * @return start room
     */
    public Room getStartRoom() {
        return startRoom;
    }

    /**
     *
     * @return return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Get country you wanna fly to, if exist else returns null
     * @param countryName The name of the country you want to fly to
     * @return a country or null
     */
    public Country getAirPortExit(String countryName) {
        return airPortRoom.getFlyExit(countryName);
    }

    /**
     * Get country you wanna take the train to, if exist else returns null
     * @param country The name of the country you want to go to
     * @return a country or null
     */
    public Country getTrainStationExit(String country){

        return trainStation.getTrainExit(country);
    }

    /**
     *
     * @return return all countries you can get to from the airport
     */
    public ArrayList<Country> getAirportExits(){
        return airPortRoom.getFlyExits();
    }

    /**
     *
     * @return returns train station room
     */
    public Room getTrainStation() {
        return trainStation;
    }

    /**
     *
     * @return returns airport room
     */
    public Room getAirPortRoom() {
        return airPortRoom;
    }

    /**
     *
     * @param startRoom The room you want to be the start room
     */
    private void setStartRoom(Room startRoom) {
        this.startRoom = startRoom;
    }

    /**
     *
     * @param airPortRoom The room you want to be the airport room
     */
    private void setAirPortRoom(Room airPortRoom) {
        this.airPortRoom = airPortRoom;
    }

    /***
     *
     * @param trainStation The room you want to be the train station room
     */
    private void setTrainStation(Room trainStation) {
        this.trainStation = trainStation;
    }

    /**
     * Set fly exit for country
     * @param countryName Name of country
     * @param country reference to an country
     */
    public void setFlyExit(String countryName, Country country)
    {
        countryName = countryName.toLowerCase();
        airPortRoom.setFlight(countryName, country);
    }

    /**
     * Set train exit for country
     * @param countryName Name of country
     * @param country reference to an country
     */
    public void setTrainExit(String countryName, Country country)
    {
        countryName = countryName.toLowerCase();
        trainStation.setTrainExits(countryName, country);
    }



}
