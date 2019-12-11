package com.SemesterProject.Interfaces;

import com.SemesterProject.Interfaces.Entities.IDeal;
import com.SemesterProject.Interfaces.Entities.IEndGameResult;
import com.SemesterProject.Interfaces.Entities.IItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IGameStage
{

    String getCurrentRoomName();
    String getCurrentCountryName();
    String getCurrentRoomDescription();
    boolean goToRoom(String direction);
    List<IDeal> getDealsFromCurrentRoom();
    boolean goToCountry(String country, int price);
    boolean goToRandomCountry(int price);
    List<String> getFlyExistFromCurrentRoom();
    List<String> getTrainExistFromCurrentRoom();
    void removeDealFromCurrentRoom(UUID id);
    boolean takeDeal(IDeal deal, IItem itemUsed);
    boolean gotEnoughMoneyToKeepPlaying();
    IConfig getConfig();
    ArrayList<IItem> getItemFromCurrentRoom();
    void removeItemFromCurrentRoom(UUID uuid);
    void addItemToCurrentRoom(IItem item);
    String getQuoteFromItemUsed(IItem item);
    IEndGameResult getEndGameResult();
}
