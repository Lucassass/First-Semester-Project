/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

/**
 * @author tes_7
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


//hvorfor inheriater du items?
//edit: fjernet iheritate af item
public class Inventory {

    //TODO metoder alt for lange. Man plejer at sige max 20 linjer og at en method KUN skal gøre 1 ting.
    //TODO naming af variabler

    //TODO Altid test dit program når du programmere det, hvis det ikke kan starte er der allerede et problem der.

    //TODO alt for mange comments

    //TODO Vi afhænger for meget af commandline til når vi skal over til UI.

    //TODO Edit: ændret alle til private
    //TODO Hvorfor er de static?
    private static ArrayList<Item> inventory = new ArrayList<Item>(); //ArrayList that contains Items
    private static ArrayList<ArrayList<Deal>> inventoryDeals = new ArrayList<ArrayList<Deal>>(); // ArrayList that contains deals
    private static ArrayList<Deal> food = new ArrayList<Deal>(); //Deals with category food (will be placed in inventoryDeals
    private static ArrayList<Deal> energy = new ArrayList<Deal>(); //Deals with category energy (will be placed in inventoryDeals
    private static ArrayList<Deal> knowledge = new ArrayList<Deal>(); //Deals with category knowledge(will be placed in inventoryDeals


    //TODO Ingen grund til at lave constructor der er empty. fylde kode.
    public Inventory() {
    } // Create inventory constructor, so that we can use it later


    //TODO Når du laver getter/setter method skal classen attribute være private. Ellers ingen grund
    //TODO getter og sætter skal meget gerne "stå" sammen

    /**
     * Gets inventory of items in players possession
     * @return
     */
    public static ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Gets inventory of Deals in players possession
     * @return
     */
    public static ArrayList<ArrayList<Deal>> getInventoryDeals() {
        return inventoryDeals;
    }

    /**
     * Player can max have 3 items on them, if more player chooses witch one to 
     * replace or if not add to inventory
     * @param item
     */
    public static void inventoryUpdateItem(Item item) // laver en metode der kan tilføjes til pickup item
    {
        // If inventory is not filled, max 3, add to inventory and print text
        //TODO ALDRIG hardcode tal i din kode. Hellere lave en global variable til at styre inventorySpace
        if (inventory.size() <= 3) {
            inventory.add(item);
            System.out.println(item.getName() + " Has been added to your inventory ");
        }
        //If inventory is filled, player chooses what to do
        outerloop:
        //used for break
        //TODO: Igen InventorySpace
        if (inventory.size() == 3) {
            System.out.println("You are already carrying 3 Items and do not have "
                    + "the strength to carry any more, too bad");

            Scanner scan = new Scanner(System.in); //adding scanner
            String scanin = scan.nextLine(); //TODO camelcasing
            System.out.println(" Would you like to replace an Item you are carrying?");
            System.out.println("Type 'yes', 'no' or 'see items'");
            System.out.println(scanin); //player chooses action from above //TODO hvorfor printe ud hvad bruger har skrevet?

            //if yes, choose which to replace
            //TODO hvorfor gør du det den vej?
            if ("yes".equals(scanin) || "Yes".equals(scanin)) {
                int n = 0; //used for while loop
                while (n == 0) {
                    printInventory();
                    System.out.println("which item would you like to replace");
                    String scanyes = scan.nextLine(); //TODO camelcasing
                    System.out.println(scanyes); //choose item to replace
                    //Goes though inventory to find and replace the choosen item
                    for (int i = 0; i < inventory.size(); i++) {

                        int m; //Used too keep track of count
                        m = 0; //TODO int default value er 0. Derfor er dette lige gyldigt
                        if (inventory.get(i).getName().equals(scanyes)) //goes through inventory
                        {
                            //If the written word is in inventory , the item gets
                            //replaced by the new one
                            inventory.set(i, item);
                            System.out.println("The item" + inventory.get(i).getName() +
                                    " has been replaced with the item " + item.getName());
                            n = 1; // while loop out
                            //TODO break i stedet for det der
                        }
                        else //adds one to m for each item that does not mach the choosen
                        {
                            m++;
                        }
                        if (m == 3) //TODO inventory space - global
                        //when m equals (max items) then written wrong word since 
                        //player can only have 3 items
                        {
                            System.out.println("The written item is not in your possession");

                        }
                    }
                } //end of while loop
            } //If no continue game


            if ("no".equals(scanin) || "No".equals(scanin)) {
                break outerloop; // breaks out of outerloop. //TODO HVilket loop?
            }
            if ("see items".equals(scanin) || "See items".equals(scanin)) {
                printInventory();
            }
        }
    }

    /**
     * Print inventory
     */
    public static void printInventory() {
        //TODO Har du selv lavet denne funktion?
        //TODO EDIT: fixed {} var sat forkert
        System.out.println("The items which you are currently carrying, are:");
        for (int i = 0; i < inventory.size(); i++) {
            if (i < inventory.size() - 1) { // prints comma after all but last
                for (Item item : inventory) {
                    System.out.print(inventory.get(i).getName() + " , ");
                }

                if (i == inventory.size()) { //prints last without comma

                    System.out.println(inventory.get(i).getName());
                }
            }
        }

    }

    //Deal part
    // arrayList for deals with category food (this array adds to inventoryDeal)


    /**
     *Adds deal with category food into ArrayList food if there is space (Max 1 deal)
     * or replaces a chosen deal
     * @param deal
     */
    public static void addFood(Deal deal) //Noget galt her, ved ikke hvad {} sat forkert i printInventory.
    {
        //TODO global food max storage variable
        //Adds deal to ArrayList if food has no items (Max 1)
        if (food.size() < 1) {
            food.add(deal);
            System.out.println("congratulations, you have now entered a new deal");
            System.out.println("The deal" + Deal.getName() + "is now in your inventory");
        }
        // If food is filled, player chooses if and what deal to replace
        else {
            //TODO bad practise at gøre det på denne måde
            //TODO break istedet for tal
            int m = 0; //for while loop
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'Food'");
                System.out.println(food);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine(); // Player chooses if replace
                System.out.println(scan);
                if ("yes".equals(scan)) {
                    food.set(0, deal);
                    System.out.println("congratulations, you have now entered a "
                            + "new deal");
                    System.out.println("The deal" + Deal.getName() + "is now "
                            + "in your inventory");
                    m = 1; // out of loop
                }
                if ("no".equals(scan)) {
                    System.out.println("You have decided to keep"
                            + Deal.getName());
                    m = 1; // out of loop
                } else {
                    System.out.println("I do not understand the command");
                    m = 0; // continue loop cause player wrote wrong word
                }
            }
        }
    }


    /**
     *Adds deal with category food into ArrayList energy if there is space (Max 2 deal)
     * or replaces a chosen deal
     * @param deal
     */
    public static void addEnergy(Deal deal) {
        if (energy.size() < 2) {
            energy.add(deal);
            System.out.println("congratulations, you have now entered a new deal");
            System.out.println("The deal" + Deal.getName() + "is now in your inventory");
        } else {
            int m = 0; //for loop 1
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'Energy'");
                System.out.println(energy);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine();
                System.out.println(scan); //player chooses action
                if ("yes".equals(scan)) {
                    int n = 0; //for loop 2
                    while (n == 0) {
                        System.out.println("Which deal whould you like to replace");
                        System.out.println(scan);
                        for (int i = 0; i < energy.size(); i++) {
                            if (Deal.getName().equals(scan)) {
                                energy.set(i, deal);
                                System.out.println("congratulations, you have now "
                                        + "entered a new deal");
                                System.out.println("The deal" + Deal.getName() +
                                        "is now in your inventory");
                                n = 1; //out of loop 2
                                //TODO BREAK
                            } else {
                                System.out.println("I do not understand the command");
                                n = 0; // keep loop 2
                                //TODO ligegyldig statement
                            }
                        }
                    }
                    m = 1; //out loop 1
                    //TODO break...
                }
                if ("no".equals(scan)) {
                    System.out.println("You have decidet to keep your current deals"
                            + Deal.getName() + Deal.getName());
                    m = 1; // out loop 1
                    //TODO BREAK
                } else {
                    System.out.println("I do not understand the command");
                    m = 0; //keep loop 1
                    //TODO ligegyldig statement
                }
            }
        }
    }

    /**
     *Adds deal with category food into ArrayList knowledge if there is space (Max 1 deal)
     * or replaces a chosen deal
     * @param deal
     */
    public static void addKnowledge(Deal deal) {
        if (knowledge.size() < 1) {
            knowledge.add(deal);
            System.out.println("congratulations, you have now entered a new deal");
            System.out.println("The deal" + Deal.getName() + "is now in your inventory");
        } else {
            int m = 0; // loop
            //TODO fjern m og break istedet
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'Knowledge'");
                System.out.println(food);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine();
                System.out.println(scan); //pleyer chooses action
                if ("yes".equals(scan)) {
                    knowledge.set(0, deal);
                    System.out.println("congratulations, you have now entered a "
                            + "new deal");
                    System.out.println("The deal" + Deal.getName() + "is now "
                            + "in your inventory");
                    m = 1; // out Loop //todo break
                }
                if ("no".equals(scan)) {
                    System.out.println("You have decidet to keep"
                            + Deal.getName());
                    m = 1; //Out Loop //todo break
                } else {
                    System.out.println("I do not understand the command");
                    m = 0; // keep loop
                    //todo ligegylldig statement
                }
            }
        }
    }


    public static void inventoryDealsUpdate(Deal deal) {
        inventoryDeals.add(food);
        inventoryDeals.add(energy);
        inventoryDeals.add(knowledge);


        if (Deal.getCategory().equals("food")) {
            addFood(deal);
        } else if (Deal.getCategory().equals("energy")) {
            addEnergy(deal);
        } else if (Deal.getCategory().equals("knowledge")) {
            addKnowledge(deal);
        }

    }





}
