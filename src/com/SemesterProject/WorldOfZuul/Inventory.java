/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

/**
 *
 * @author tes_7
 */
  import java.util.ArrayList;
import java.util.Iterator;
  import java.util.Scanner;


public class Inventory extends Item
{

    public static ArrayList <Item> inventory = new ArrayList<Item>(); //ArrayList that contains Items
    public static ArrayList<ArrayList<Deal>> inventoryDeals = new ArrayList<ArrayList<Deal>>(); // ArrayList that contains deals
    public static ArrayList<Deal> food = new ArrayList<Deal>(); //Deals with category food (will be placed in inventoryDeals
    public static ArrayList<Deal> energy = new ArrayList<Deal>(); //Deals with category energy (will be placed in inventoryDeals
    public static ArrayList<Deal> knowledge = new ArrayList<Deal>(); //Deals with category knowledge(will be placed in inventoryDeals
    
   
    
    public Inventory(){} // Create inventory constructor, so that we can use it later
   
    /**
     * Gets inventory of items in players posession
     * @return 
     */
    public static ArrayList<Item> getInventory(){return inventory;}

    
    
    /**
     * Player can max have 3 items on them, if more player chooses witch one to 
     * replace or if not add to inventory
     * @param item
     */
    public static void inventoryUpdateItem(Item item) // laver en metode der kan tilføjes til pickup item
    {
        // If inventory is not filled, max 3, add to inventory and print text
        if(inventory.size() <= 3) 
        {
            inventory.add(item);
            System.out.println(item.name + " Has been added to your inventory ");
        }
        //If inventory is filled, player chooses what to do
        outerloop: //used for break 
        if(inventory.size() == 3)
        {
            System.out.println("You are already carrying 3 Items and do not have "
                    + "the strength to carry any more, too bad");
            
            Scanner scan = new Scanner(System.in); //adding scanner
            String scanin = scan.nextLine();
            System.out.println(" Would you like to replace an Item you are carrying?");
            System.out.println("Type 'yes', 'no' or 'see items'");
            System.out.println(scanin); //player chooses action from above
            
            //if yes, choose which to replace
            if ("yes".equals(scanin) || "Yes".equals(scanin))
            {
                int n = 0; //used for while loop
                while (n == 0)
                {
                printInventory();
                System.out.println("which item would you like to replace");
                String scanyes = scan.nextLine();
                System.out.println(scanyes); //choose item to replace
                //Goes though inventory to find and replace the choosen item
                for(int i = 0;i < inventory.size(); i++) 
                {
                    int m; //Used too keep track of count
                    m =0;
                    if(inventory.get(i).name.equals(scanyes)) //goes through inventory
                    {
                        //If the written word is in inventory , the item gets 
                        //replaced by the new one
                        inventory.set(i, item); 
                        System.out.println("The item" + inventory.get(i).name + 
                                " has been replaced with the item " + item.name);
                        n = 1; // while loop out
                    }
                    else //adds one to m for each item that does not mach the choosen
                    {
                        m ++;
                    }
                    if (m == 3) 
                        //when m equals (max items) then written wrong word since 
                        //player can only have 3 items
                    {
                        System.out.println("The written item is not in your possession");
                        
                    }
                }
                } //end of while loop
            } //If no continue game
            if("no".equals(scanin) || "No".equals(scanin))
            {
                break outerloop; // breaks out of outerloop.
            }
            if("see items".equals(scanin) || "See items".equals(scanin))
            {
                printInventory();
            }                 
        }    
    }
    
    /**
     * Print inventory
     */
    public static void printInventory()
    {
        System.out.println("The items which you are currently carrying, are:");
        for(int i = 0; i < inventory.size() ; i++)
        { if (i < inventory.size() -1){ // prints comma after all but last
            for (Item item : inventory)
            {
                System.out.print(inventory.get(i).name +" , ");
            }
        } if (i == inventory.size()){ //prints last without comma

                System.out.println(inventory.get(i).name);
        }
    }
    
 //Deal part
    // arrayList for deals with category food (this array adds to inventoryDeal)

    /**
     *
     */
      
    
    /**
     *Adds deal with category food into ArrayList food if there is space (Max 1 deal)
     * or replaces a chosen deal
     * @param deal
     */
    
    public static void addFood(Deal deal) //Noget galt her, ved ikke hvad
    {
        //Adds deal to ArrayList if food has no items (Max 1)
        if(food.size() < 1)
        {
            food.add(deal);
            System.out.println("congratulations, you have now entered a new deal");
            System.out.println("The deal" + deal.name + "is now in your inventory");
        } 
        // If food is filled, player chooses if and what deal to replace
        else
        {
            int m = 0; //for while loop
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'Food'");
                System.out.println(food);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine(); // Player chooses if replace
                System.out.println(scan);
                if("yes".equals(scan))
                {
                    food.set(0, deal);
                    System.out.println("congratulations, you have now entered a "
                            + "new deal");
                    System.out.println("The deal" + deal.getName() + "is now "
                            + "in your inventory");
                    m = 1; // out of loop
                } 
                if ("no".equals(scan))
                {
                    System.out.println("You have decidet to keep" 
                            + food.get(0).name);
                    m = 1; // out of loop
                } else 
                {
                    System.out.println("I do not understand the command");
                    m = 0; // continue loop caus player wrote wrong word
                }
            }
        }
    }
    
        
    /**
     *Adds deal with category food into ArrayList energy if there is space (Max 2 deal)
     * or replaces a chosen deal
     * @param deal
     */
    public static void addEnergy(Deal deal)
    {
        if(energy.size() < 2)
        {
            energy.add(deal);
            System.out.println("congratulations, you have now entered a new deal");
            System.out.println("The deal" + deal.getName() + "is now in your inventory");
        } 
        else
        {
            int m = 0; //for loop 1
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'Energy'");
                System.out.println(energy);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine();
                System.out.println(scan); //player chooses action
                if("yes".equals(scan))
                {
                    int n = 0; //for loop 2
                    while (n ==0)
                    {
                    System.out.println("Which deal whould you like to replace");
                    System.out.println(scan);
                    for(int i = 0; i < energy.size(); i++)
                    {
                        if(energy.get(i).getName().equals(scan))
                        {
                            energy.set(i, deal);
                            System.out.println("congratulations, you have now "
                                    + "entered a new deal");
                            System.out.println("The deal" + deal.getName() + 
                                    "is now in your inventory");
                            n = 1; //out of loop 2
                        } else {
                            System.out.println("I do not understand the command");
                            n = 0; // keep loop 2
                        }
                    }
                    }                   
                    m = 1; //out loop 1
                } 
                if ("no".equals(scan))
                {
                    System.out.println("You have decidet to keep your current deals" 
                            + energy.get(0).getName() + energy.get(1).getName());
                    m = 1; // out loop 1
                } else 
                {
                    System.out.println("I do not understand the command");
                    m = 0; //keep loop 1
                }
            }
        }
    }
    
    /**
     *Adds deal with category food into ArrayList knowledge if there is space (Max 1 deal)
     * or replaces a chosen deal
     * @param deal
     */
    public static void addKnowledge(Deal deal)
    {
        if(knowledge.size() < 1)
        {
            knowledge.add(deal);
            System.out.println("congratulations, you have now entered a new deal");
            System.out.println("The deal" + deal.getName() + "is now in your inventory");
        } 
        else
        {
            int m = 0; // loop
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'Knowledge'");
                System.out.println(food);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine();
                System.out.println(scan); //pleyer chooses action
                if("yes".equals(scan))
                {
                    knowledge.set(0, deal);
                    System.out.println("congratulations, you have now entered a "
                            + "new deal");
                    System.out.println("The deal" + deal.getName() + "is now "
                            + "in your inventory");
                    m = 1; // out Loop 
                } 
                if ("no".equals(scan))
                {
                    System.out.println("You have decidet to keep" 
                            + knowledge.get(0).getName());
                    m = 1; //Out Loop
                } else 
                {
                    System.out.println("I do not understand the command");
                    m = 0; // keep loop
                }
            }
        }
    }

    
      public static void inventoryDealsUpdate(Deal deal)
   {
       inventoryDeals.add(food);
       inventoryDeals.add(energy);
       inventoryDeals.add(knowledge);      
            
       
       if(deal.getCategory().equals("food"))
       {addFood(deal);}
       else if(deal.getCategory().equals("energy"))
       {addEnergy(deal);}
       else if(deal.getCategory().equals("knowledge"))
       {addKnowledge(deal);}
                 
   }
    
     /**
     * Gets inventory of Deals in players posession
     * @return 
     */
    
    
    
    public static ArrayList<ArrayList<Deal>> getInventoryDeals(){return inventoryDeals;}
    
    
    
  
    
    
    
}
