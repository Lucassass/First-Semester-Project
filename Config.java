/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

import java.util.Random;
import com.SemesterProject.WorldOfZuul.Inventory;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//todo igen, hvorfor extends med inventory?
public class Config 
{
    private String dansk = "Danmark"; //slettes når opdatere rollDice
    private int diceAdvantage;
      
     /**
      * Kan ikke få metoden til at virke, skulle gerne lægges til roll senere
      * Puts the advantages and disadvantages together, depending on the dice roll
      * @return 
      */   
    /*public void advantage()
    {   
        int advantage;      
        Inventory in = new Inventory();
        for(int i = 0; i < in.getInventoryItem().size(); i++) //goes through inventory
       {
          
         //If in good good get good points
         if(in.getInventoryItem().get(i).getCountryGood().equalsIgnoreCase(dansk) )
         {
            advantage = in.getInventoryItem().get(i).getPointsGood();
             System.out.println("Ad" + advantage);
             
         }
         // If bad country get bad points
         else if(in.getInventoryItem().get(i).getCountryBad().equals(dansk) )
         {
            advantage = in.getInventoryItem().get(i).getPointsBad();
         // If in good and bad country get both points
         }else if(in.getInventoryItem().get(i).getCountryGood().equals(dansk) 
                 && in.getInventoryItem().get(i).getCountryBad().equals(dansk))
         {
             advantage = in.getInventoryItem().get(i).getPointsGood() 
                     + in.getInventoryItem().get(i).getPointsBad();
         } else {advantage = 0;}
         //random gives number between 0 and 6 (beneath 7), so +1 to make it
         //1 and 6 and then add diceAdvantage
           
       }      
           System.out.println(advantage);    
    }*/
    
    /**
     * returns number between 0 and 7
     * @return 
     */
    public int random()
    {
        int roll = 0;
        while (1 > roll || roll > 6){
        
            roll = (int)(Math.random()*10);
        }
        
        return roll;
    }

    /**
     * Needs to replace the dansk part with objekt country.when player rolls dice
     * go through inventory and chek the advantages and disadvantages player gets
     * from items depending on the country.Then roll dice, if between 4 and 7 return true else return false  
     * @return 
     */    
    public boolean rollDice()
   {
       Inventory in = new Inventory();
       
       int diceResult;
          
       
        
        int roll = random();
        diceResult = roll;
           
        return 3 < diceResult && diceResult < 7; //returns true or false
       
   }
    
  
   public void takeDeal(Deal deal)
   {
      Inventory in = new Inventory();
       
       boolean win = rollDice();
       if(win == true) //todo ?
       {
           System.out.println("Congratulations, the deal was a succsses!!");  
           in.inventoryUpdateDeals(deal); //Kan ikke finde ud af hvad der er galt //TODO {}
       } else{
            System.out.println("The deal did not go through, not you lucky day i guess..");
       }
          
   }
   /**
    * ----> SHOULD BE USED IN ANOTHER CLASS OR COMMANDWORD <----
    * Asks which deal player want's to take, shows deal from country, player
    * chooses deal, shows info about deal, chooses if yes or no, if yes roll and 
    * get deal or nor.
    * @param list --> The list of Deals in the country
    */
   public  void startDeal(ArrayList<Deal> list)
   {
       Inventory in = new Inventory(); 
       System.out.print("Hello there");
       int m = 0; while (m == 0){
       System.out.println("which deal would you like to negotiate?");
       
       in.printInventoryDeals(list);
       Scanner sc = new Scanner (System.in);
       String scan = sc.nextLine();
      
        for(int i = 0; i < list.size(); i++)
        {
           int k = 0;
            if(list.get(i).getName().equalsIgnoreCase(scan))
           {
               System.out.println(list.get(i).getInfo());
               int n = 0; while (n == 0){
                   
               System.out.println("Would you like to negotiate the deal "
               + list.get(i).getName() + " ?");
               String scann = sc.nextLine();
               
               if(scann.equalsIgnoreCase("yes"))
               {
                   takeDeal(list.get(i)); n++;
               } 
               else if (scann.equalsIgnoreCase("no")){n++;}
               else{}}
               m++;
           } else{ k++;}
            if (k == list.size()){}
            
       } }
               
     
    }
       
   }

 