package com.SemesterProject.WorldOfZuul.Country;

import com.SemesterProject.WorldOfZuul.Item;
import com.SemesterProject.WorldOfZuul.Room;

import java.util.ArrayList;

public class USA extends BaseCountry
{


    public USA()
    {
        createRooms();
    }

    @Override
    protected void createRooms()
    {
        var notAirport = new Room("USA: Not airport");
        var airport = new Room("USA: Airport");

        notAirport.setExit("left", airport);
        airport.setExit("right", notAirport);

        setStartRoom(notAirport);
        setAirPortRoom(airport);
    }


}


