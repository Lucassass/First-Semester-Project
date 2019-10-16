/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

import java.util.ArrayList;
import java.util.Random;


public class Config extends Inventory
{
    private static String dansk = "Danmark"; //slettes når opdatere rollDice
    private static int money;
    public static int sustainability = 0;
    public static int energy = 0;
    public static int environment = 0;
    private static int diceAdvantage = 0;
    private static int diceResult = 0;
    
    Inventory inventory = new Inventory();

    /**
     * Needs to replace the dansk part with objekt country.
     * when player rolls dice go through inventory and chek the advantages and
     * disadvantages player gets from items depending on the country. 
     * Then roll dice, if between 4 and 7 return true else return false.
     */
   public boolean rollDice()
   {
       for(int i = 0; i < inventory.getInventory().size(); i++)
       {
         if(inventory.getInventory().get(i).getCountryGood(this).equals(dansk) )
         {
             diceAdvantage = inventory.getInventory().get(i).getPointsGood(this);
         }
         else if(inventory.getInventory().get(i).getcountryBad(this).equals(dansk) )
         {
             diceAdvantage = inventory.getInventory().get(i).getPointsBad(this);
         }else if(inventory.getInventory().get(i).getCountryGood(this).equals(dansk) 
                 && inventory.getInventory().get(i).getcountryBad(this).equals(dansk))
         {
             diceAdvantage = inventory.getInventory().get(i).getPointsGood(this) 
                     + inventory.getInventory().get(i).getPointsBad(this);
         }
         
         diceResult = new Random().nextInt(7) + 1 + diceAdvantage;
             
       }
       
       if (3 < diceResult < 7)
       {
           return true;
       } else {
           return false;
       }
       
   }
    
    public static int getMoney() {
        return money;
    }

    public static void setMoney(int newMoney) {
        money = newMoney;
    }
}