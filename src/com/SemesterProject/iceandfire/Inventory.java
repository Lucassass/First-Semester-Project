/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.iceandfire;

/**
 * @author tes_7
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    private static Inventory instance = new Inventory();
    private ArrayList<ItemObsolete> inventoryItemObsolete = new ArrayList<ItemObsolete>(); //ArrayList that contains Items
    private ArrayList<ArrayList<DealObsolete>> inventoryDeals = new ArrayList<ArrayList<DealObsolete>>(); // ArrayList that contains deals
    private ArrayList<DealObsolete> food = new ArrayList<DealObsolete>(); //Deals with category food (will be placed in inventoryDeals
    private ArrayList<DealObsolete> energy = new ArrayList<DealObsolete>(); //Deals with category energy (will be placed in inventoryDeals
    private ArrayList<DealObsolete> knowledge = new ArrayList<DealObsolete>(); //Deals with category knowledge(will be placed in inventoryDeals

    private final int maxItem = 3;
    private final int maxFood = 1;
    private final int maxEnergy = 2;
    private final int maxKnowledge = 1;

    private Inventory(){
    }

    /**
     * ---> TO BE USED EVERY TIME YOU WANT TO CALL A METHOD FROM ITEM <---
     * @return
     */
    public static Inventory getInstance(){return instance;}

    /**
     * @return Inventory Items
     */
    public ArrayList<ItemObsolete> getInventoryItemObsolete(){return inventoryItemObsolete;}
    /**
     * @return inventory Deals
     */
    public ArrayList<ArrayList<DealObsolete>> getInventoryDeals(){return inventoryDeals;}

    /**
     *  ------> SHOULD BE USED IN ANOTHER CLASS OR COMMANDWORD <--------
     * goes through Items in cultural room, player chooses if wanna take or not
     * @param list --> List of Items in Country
     */
    public void searchForItems(ArrayList<ItemObsolete> list)
    {   if(list.isEmpty())
        {
            System.out.println("Sorry, no items in this room :(");
            return;
        }

        System.out.println("Your search was fruitful");
        for(int s = 0; s < list.size(); s++)
        {
            while (true)
            {
                System.out.println("You stumblede upon the item " + list.get(s).getName());
                System.out.println("wanna take it??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine();

                if(scan.equalsIgnoreCase("quit"))
                {
                    return;
                }
                else if(scan.equalsIgnoreCase("yes"))
                {
                    inventoryUpdateItem(list.get(s),list);
                    System.out.println("Lets move on");
                    break;
                }
                else if(scan.equalsIgnoreCase("no"))
                {
                    System.out.println("Not your soul item i guess, moving on");
                    break;
                }
            }
        }
    }

    /**
     * --Intern Use
     * Player can max have 3 items on them, if more player chooses witch one to
     * replace or if not add 'item'  to inventory
     * @param itemObsolete that should be added
     * @param country - item inventory of country currently in
     */
    public void inventoryUpdateItem(ItemObsolete itemObsolete, ArrayList<ItemObsolete> country)
    {   // If inventory is not filled, max 3, add to inventory, remove from old and print text
        if(inventoryItemObsolete.size() != maxItem && inventoryItemObsolete.size() < maxItem )
        {
            inventoryItemObsolete.add(itemObsolete);
            country.remove(itemObsolete);
            System.out.println(itemObsolete.getName() + " Has been added to your inventory ");
        }
        //If inventory is filled, player chooses what to do
        else if (inventoryItemObsolete.size() == maxItem)
        {
            System.out.println("You are already carrying 3 Items and do not have "
                    + "the strength to carry any more, too bad");
            Scanner scan = new Scanner(System.in); //adding scanner
            int k = 0;
            while (k == 0)
            {
                System.out.println("Would you like to replace an Item you are carrying?");
                System.out.println("Type 'yes', 'no' or 'see items'");
                String scanIn = scan.nextLine();

                if(scanIn.equalsIgnoreCase("Quit"))
                {
                    return;
                }
                if (scanIn.equalsIgnoreCase("yes"))
                {
                    int n = 0;
                    while (n == 0)
                    {
                    printInventoryItem(inventoryItemObsolete);
                    System.out.println("which item would you like to replace");

                    String scanYes = scan.nextLine();
                    //Goes though inventory to find and replace the choosen item
                    for (int i = 0; i < inventoryItemObsolete.size(); i++)
                    {
                        int m = 0; //Used too keep track of count

                        if(scanYes.equalsIgnoreCase("quit"))
                        {
                            return;
                        }
                        if (scanYes.equals(inventoryItemObsolete.get(i).getName())) //goes through inventory
                        {
                            //If the written word is in inventory , the item gets //replaced by the new one

                            System.out.println("The item " + inventoryItemObsolete.get(i).getName() +
                                    " has been replaced with the item " + itemObsolete.getName());
                            inventoryItemObsolete.set(i, itemObsolete);
                            country.remove(itemObsolete);
                            n++;
                        }
                        else
                        {
                            m++; //adds one to m for each item that does not mach the choosen
                        }
                        if (m == maxItem) //when m equals (max items) then written wrong word since, player can only have 3 items
                        {
                            System.out.println("The written item is not in your possession");
                        }
                    }
                    }k++;
                } //If no continue game
                if ("no".equals(scanIn) || "No".equals(scanIn))
                {
                    System.out.println("No new items for you i guess.");
                    k++;
                }
                if ("see items".equals(scanIn) || "See items".equals(scanIn))
                {
                    printInventoryItem(inventoryItemObsolete);
                }
            }
        }
    }

    /**
     * prints inventory Items
     * @param list
     */
    void printInventoryItem(ArrayList<ItemObsolete> list)
    {
        System.out.println("The items you are currently carrying are:");
        for(int i = 0; i < list.size(); i++)
        {   if(i< list.size()-1)
        {
            System.out.print(getInventoryItemObsolete().get(i).getName() + ", ");
        }
        else
        {
            System.out.println(getInventoryItemObsolete().get(i).getName());
        }
        }
    }



//Deal part
    /**
     * prints deals in an ArrayList without any extra text
     * @param list
     */
    String createDealsStringFor(ArrayList<DealObsolete> list)
    {
        StringBuilder format = new StringBuilder();
        for(int i = 0; i < list.size(); i++)
        {
            if (i < list.size() - 1)
            {
                format.append(list.get(i).getName()).append(",");
            }
            else
            {
                format.append(list.get(i).getName());
            }
        }

        return format.toString();
    }

    /**
     * -- for bigger
     * @param dealObsolete --> deal that player will add
     * @param category --> inventory Category(food, energy or knowledge)
     * @param country --> list og deals in current country
     */
    private void addingDeal(DealObsolete dealObsolete, ArrayList<DealObsolete> category, ArrayList<DealObsolete> country)
    {
        category.add(dealObsolete);
        country.remove(dealObsolete);
        System.out.println("congratulations, you have now entered a new deal ");
        System.out.println("The deal " +  dealObsolete.getName() + " is now in your inventory");
    }

    public void removeDealFromInventory(DealObsolete dealObsoleteToRemove)
    {
        for (var dealCategory : inventoryDeals)
        {
            if (dealCategory.size() != 0 && dealCategory.get(0).getCategory() == dealObsoleteToRemove.getCategory())
            {
                for(var deal : dealCategory)
                {
                    if (!deal.getName().equals(dealObsoleteToRemove.getName()))
                    {
                        dealCategory.remove(deal);
                        break;
                    }
                }
                break;
            }
        }
    }

    /** --used for bigger
     * Adds deal to ArrayListDeals if category has not reached max items
     * @param dealObsolete --> deal that will be added
     * @param category --> the under category in inventoryDeals
     * @param max --> max Deals in country
     * @param country --> list og deals in current country
     */
    private void addCategory(DealObsolete dealObsolete, ArrayList<DealObsolete> category, int max, ArrayList<DealObsolete> country){
        if (category.size() < max)
        {
            addingDeal(dealObsolete,category, country);
        }
        // If food is filled, player chooses if and what deal to replace
        else
        {
            //askingUserToReplaceDeal();
            int m = 0;
            while (m == 0)
            {

                System.out.println("You can only have " + max + " deal in the category " + dealObsolete.getCategory());
                printInventoryCategory(category);
                System.out.println("Would you like to replace your current deal??");

                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine(); // Player chooses if replace

                if(scan.equals("quit"))
                {
                    return;
                }
                if(scan.equals("yes"))
                {
                    if(max == 1)
                    {
                        //
                        addingDeal(dealObsolete,category, country);
                        removeDealFromInventory(dealObsolete);
                        m++;
                    }
                    else
                    {
                        int k = 0;
                        while (k == 0)
                        {
                            System.out.println("Which deal would you like to replace");
                            printInventoryCategory(category);
                            scan = sc.nextLine();
                            for (int i = 0; i < category.size(); i++)
                            {
                                if (category.get(i).getName().equalsIgnoreCase(scan))
                                {
                                    country.remove(dealObsolete);category.set(i, dealObsolete);
                                    System.out.println("congratulations, you have now entered a new deal");
                                    System.out.println("The deal " + dealObsolete.getName() + " is now in your inventory");
                                    m++;
                                    k++;
                                }
                            }
                        }
                    }
                }
                else if ( scan.equalsIgnoreCase("No"))
                {
                    System.out.println("No new deals for you :(");
                    m++;
                }
                else{
                    System.out.println("I do not understand the command");
                }
            }
        }
    }


    /** -- used for bigger
     * from taking a deal to adding to inventoryDeals
     * @param dealObsolete
     * @param country
     */
    void inventoryUpdateDeals(DealObsolete dealObsolete, ArrayList<DealObsolete> country)
    {

            Config.subtractMoney(dealObsolete.getPrice());
            inventoryDeals.add(food);
            inventoryDeals.add(energy);
            inventoryDeals.add(knowledge);
            if (dealObsolete.getCategory() == DealCategory.Food)
            {
                addCategory(dealObsolete,food,maxFood,country);
            }
            else if (dealObsolete.getCategory() == DealCategory.Energy)
            {
                addCategory(dealObsolete, energy, maxEnergy, country);
            }
            else if (dealObsolete.getCategory() == DealCategory.Knowledge)
            {
                addCategory(dealObsolete, knowledge, maxKnowledge, country);
            }
    }

    /**
     * Prints inventory for chosen category List
     * @param list
     */
    private void printInventoryCategory(ArrayList<DealObsolete> list)
    {
        System.out.println("The Deals you are currently carrying are: " + createDealsStringFor(list));
    }

    void printInventoryDeals()
    {
        System.out.println("The Deals that you currently have, are:");
        System.out.println("Food: " + createDealsStringFor(food));
        System.out.println("Energy: " + createDealsStringFor(energy));
        System.out.println("Knowledge: " + createDealsStringFor(knowledge));
    }

}
