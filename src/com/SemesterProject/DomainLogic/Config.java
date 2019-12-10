/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.DomainLogic;

import com.SemesterProject.Interfaces.IConfig;

public class Config implements IConfig
{
    private int money = 2000;
    private int commercialFlyingCost = 100;
    private int privateFlyingCost = 250;
    private int trainCost = 50;

    public boolean gotEnoughMoney(int amount){
        return money > amount;
    }

    public void subtractMoney(int amount){
        money -= amount;
    }

    public int getMoney() {
        return money;
    }

    public int getCommercialFlyingCost() {
        return commercialFlyingCost;
    }

    public int getPrivateFlyingCost() {
        return privateFlyingCost;
    }

    public int getTrainCost() {
        return trainCost;
    }

    @Override
    public int getLowestCost()
    {
        if (commercialFlyingCost < privateFlyingCost && commercialFlyingCost < trainCost) return commercialFlyingCost;
        if (privateFlyingCost < commercialFlyingCost && privateFlyingCost < trainCost) return privateFlyingCost;
        if (trainCost < commercialFlyingCost && trainCost < privateFlyingCost) return trainCost;

        return 0;
    }



}

 