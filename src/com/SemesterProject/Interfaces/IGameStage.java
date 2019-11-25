package com.SemesterProject.Interfaces;

import com.SemesterProject.DomainLogic.Country;
import com.SemesterProject.DomainLogic.Room;

public interface IGameStage
{

    String getRoomName();
    String getCountryName();
    String getRoomDescription();
    boolean goRoom(String direction);


}
