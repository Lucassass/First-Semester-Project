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
    private int dealMaxTries = 1;
    private int diceAdvantage;

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

    public boolean ranOutOfMoney(){
        return money < commercialFlyingCost || money < privateFlyingCost || money < trainCost;
    }
    /**
     *
     * @return true if you have enough money for train
     */
    public boolean gotMoneyForTrain()
    {
        return money >= trainCost;
    }

    /**
     *
     * @return true if you have enough money for commercial flying
     */
    public boolean gotMoneyForCommercialFlying()
    {
        return money >= commercialFlyingCost;
    }

    /**
     *
     * @return true if you have enough money for private flying
     */
    public boolean gotMoneyForPrivateFlying()
    {
        return money >= privateFlyingCost;
    }

    /**
     * subtract commercial flying cost from your money
     */
    public void buyFlyCommercial()
    {
        money -= commercialFlyingCost;
    }

    /**
     * subtract private flying cost from your money
     */
    public void buyFlyPrivate() {
        money -= privateFlyingCost;
    }

    /**
     * subtract train cost from your money
     */
    public void buyTrainTicket()
    {
        money -= trainCost;
    }


}

 