package com.SemesterProject.Interfaces;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.Entities.EndGameResult;
import com.SemesterProject.DomainLogic.Entities.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface IGameStage
{

    String getRoomName();
    String getCountryName();
    String getRoomDescription();
    boolean goRoom(String direction);
    List<Deal> getDealsFromRoom();
    boolean goCountry(String country, int price);
    boolean goRandomCountry(int price);
    List<String> getFlyExist();
    List<String> getTrainExist();
    void removeDealFromRoom(UUID id);
    boolean takeDeal(Deal deal, Item itemUsed);
    boolean gotEnoughMoneyToKeepPlaying();
    IConfig getConfig();
    ArrayList<Item> getItemFromRoom();
    void removeItemFromRoom(UUID uuid);
    void addItemToRoom(Item item);
    String quoteFromItemUsed(Item item);
    EndGameResult getEndGameResult();
}
