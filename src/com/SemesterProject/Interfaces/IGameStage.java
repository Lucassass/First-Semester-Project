package com.SemesterProject.Interfaces;

import com.SemesterProject.DomainLogic.Entities.Deal;

import java.util.List;

public interface IGameStage
{

    String getRoomName();
    String getCountryName();
    String getRoomDescription();
    boolean goRoom(String direction);
    List<Deal> getDealsForRoom();
    boolean goCountry(String country);
    boolean goRandomCountry();
    List<String> getFlyExist();
    List<String> getTrainExist();

}
