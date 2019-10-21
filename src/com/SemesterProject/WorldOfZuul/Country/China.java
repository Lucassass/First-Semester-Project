package com.SemesterProject.WorldOfZuul.Country;

import com.SemesterProject.WorldOfZuul.Room;


public class China extends BaseCountry
{

    public China() {
        createRooms();
    }

    @Override
    protected void createRooms()
    {
        var notAirport = new Room("China: Not airport");
        var airport = new Room("China: Airport");

        notAirport.setExit("left", airport);
        airport.setExit("right", notAirport);

        setStartRoom(notAirport);
        setAirPortRoom(airport);
    }

}

