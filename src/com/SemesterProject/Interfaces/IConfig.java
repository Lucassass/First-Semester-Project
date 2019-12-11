package com.SemesterProject.Interfaces;

public interface IConfig
{
    int getMoney();
    boolean gotEnoughMoney(int amount);
    void subtractMoney(int amount);
    int getCommercialFlyingCost();
    int getPrivateFlyingCost();
    int getTrainCost();
    int getLowestCost();
}
