/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

import java.util.Random;

//todo igen, hvorfor extends med inventory?
public class Config extends Inventory
{
    private static String dansk = "Danmark"; //slettes når opdatere rollDice
    private static int money;
           
    
    

    /**
     * Needs to replace the dansk part with objekt country.when player rolls dice
     * go through inventory and chek the advantages and disadvantages player gets
     * from items depending on the country.Then roll dice, if between 4 and 7 return true else return false  
     * @return 
     */    
   public static boolean rollDice()
   {

       /*
       int diceResult = 0;
       int diceAdvantage = 0; //In here to have new advantage every time rollDice
       for(int i = 0; i < inventory.size(); i++) //goes through inventory
       {
           //If in good good get good points
         if(inventory.get(i).getCountryGood().equals(dansk) )
         {
             diceAdvantage = inventory.get(i).getPointsGood();
         }
         // If bad country get bad points
         else if(inventory.get(i).getCountryBad().equals(dansk) )
         {
             diceAdvantage = inventory.get(i).getPointsBad();
         // If in good and bad country get both points
         }else if(inventory.get(i).getCountryGood().equals(dansk) 
                 && inventory.get(i).getCountryBad().equals(dansk))
         {
             diceAdvantage = inventory.get(i).getPointsGood() 
                     + inventory.get(i).getPointsBad();
         }
         //random gives number between 0 and 6 (beneath 7), so +1 to make it
         //1 and 6 and then add diceAdvantage
         diceResult = new Random().nextInt(7) + 1 + diceAdvantage;
             
       }
       
        return 3 < diceResult && diceResult < 7; //returns true or false
       */
       return false;
   }
    
  
   public static void takeDeal(Deal deal)
   {
      
       rollDice();
       if(true) //todo ?
       {
           System.out.println("Congratulations, the deal was a succsses!!");  
           inventoryDealsUpdate(deal); //Kan ikke finde ud af hvad der er galt //TODO {}
       } else{
            System.out.println("The deal did not go through, not you lucky day i guess..");
       }
          
   }
   //Skal lige finde ud af hvad der skal være her
   public static void startDeal()
   {
       
   }
   
    public static int getMoney() {
        return money;
    }

    public static void setMoney(int newMoney) {
        money = newMoney;
    }
}