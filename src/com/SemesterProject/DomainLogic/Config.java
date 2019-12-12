/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.DomainLogic;

import com.SemesterProject.Interfaces.IConfig;

public class Config implements IConfig
{
    private int money = 3000;
    private int commercialFlyingCost = 100;
    private int privateFlyingCost = 250;
    private int trainCost = 50;

    /**
     *
     * @param amount amount to check
     * @return true if got enought damage
     */
    public boolean gotEnoughMoney(int amount){
        return money > amount;
    }

    /**
     *
     * @param amount amount to subtract
     */
    public void subtractMoney(int amount){
        money -= amount;
    }

    /**
     *
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     *
     * @return commercial flight cost
     */
    public int getCommercialFlyingCost() {
        return commercialFlyingCost;
    }

    /**
     *
     * @return private flight cost
     */
    public int getPrivateFlyingCost() {
        return privateFlyingCost;
    }

    /**
     *
     * @return train cost
     */
    public int getTrainCost() {
        return trainCost;
    }

    /**
     *
     * @return the lowest cost out of commercial flight, private flight and train
     */
    @Override
    public int getLowestCost()
    {
        if (commercialFlyingCost < privateFlyingCost && commercialFlyingCost < trainCost) return commercialFlyingCost;
        if (privateFlyingCost < commercialFlyingCost && privateFlyingCost < trainCost) return privateFlyingCost;
        if (trainCost < commercialFlyingCost && trainCost < privateFlyingCost) return trainCost;

        return 0;
    }



}

 