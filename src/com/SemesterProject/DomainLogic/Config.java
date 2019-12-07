/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.DomainLogic;

import com.SemesterProject.DomainLogic.Entities.Country;
import com.SemesterProject.Interfaces.IConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Config implements IConfig
{
    private int money = 2000;
    private int CommercialFlyingCost = 100;
    private int PrivateFlyingCost = 250;
    private int TrainCost = 50;
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

    public boolean ranOutOfMoney(){
        return money < CommercialFlyingCost || money < PrivateFlyingCost || money < TrainCost;
    }
    /**
     *
     * @return true if you have enough money for train
     */
    public boolean gotMoneyForTrain()
    {
        return money >= TrainCost;
    }

    /**
     *
     * @return true if you have enough money for commercial flying
     */
    public boolean gotMoneyForCommercialFlying()
    {
        return money >= CommercialFlyingCost;
    }

    /**
     *
     * @return true if you have enough money for private flying
     */
    public boolean gotMoneyForPrivateFlying()
    {
        return money >= PrivateFlyingCost;
    }

    /**
     * subtract commercial flying cost from your money
     */
    public void buyFlyCommercial()
    {
        money -= CommercialFlyingCost;
    }

    /**
     * subtract private flying cost from your money
     */
    public void buyFlyPrivate() {
        money -= PrivateFlyingCost;
    }

    /**
     * subtract train cost from your money
     */
    public void buyTrainTicket()
    {
        money -= TrainCost;
    }


}

 