package com.SemesterProject.WorldOfZuul;

import java.util.ArrayList;

public  class Country {

    private Room startRoom;
    private Room airPortRoom;
    private Room trainStation;

    public Country(String airportDescription, String trainStationDescription, String outsideDescription,
                   String governmentDescription, String cultureDescription, ArrayList<Item> items)
    {
        var airport = new Room(airportDescription);
        var government = new Room(governmentDescription);
        var outside = new Room(outsideDescription);
        var trainStation = new Room(trainStationDescription);
        var culture = new Room(cultureDescription);

        airport.setExit("down", outside);
        government.setExit("right", outside);
        trainStation.setExit("left", outside);
        culture.setExit("up", outside);
        outside.setExit("up", airport);
        outside.setExit("down", culture);
        outside.setExit("left", government);
        outside.setExit("right", trainStation);

        setStartRoom(outside);
        setAirPortRoom(airport);
        setTrainStation(trainStation);
    }

    public Room getStartRoom() {
        return startRoom;
    }

    public Country getAirPortExit(String country) {
        return airPortRoom.getFlyExits(country);
    }

    public Country getTrainStationExit(String country){

        return trainStation.getTrainExits(country);
    }

    private void setStartRoom(Room startRoom) {
        this.startRoom = startRoom;
    }

    private void setAirPortRoom(Room airPortRoom) {
        this.airPortRoom = airPortRoom;
    }

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
        airPortRoom.setFlight(countryName, country);
    }

    public void setTrainExit(String countryName, Country country)
    {
        trainStation.setTrainExits(countryName, country);
    }



}
