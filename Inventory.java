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
    private ArrayList<Item> inventoryItem = new ArrayList<Item>(); //ArrayList that contains Items
    private ArrayList<ArrayList<Deal>> inventoryDeals = new ArrayList<ArrayList<Deal>>(); // ArrayList that contains deals
    private ArrayList<Deal> food = new ArrayList<Deal>(); //Deals with category food (will be placed in inventoryDeals
    private ArrayList<Deal> energy = new ArrayList<Deal>(); //Deals with category energy (will be placed in inventoryDeals
    private ArrayList<Deal> knowledge = new ArrayList<Deal>(); //Deals with category knowledge(will be placed in inventoryDeals
    
    
    private final int maxItem = 3;
    private final int maxFood = 1;
    private final int maxEnergy = 2;
    private final int maxKnowledge = 1;
    
    /**
     * 
     * @return 
     */
    public ArrayList<Item> getInventoryItem(){return inventoryItem;}
    /**
     * 
     * @return 
     */
    public ArrayList<ArrayList<Deal>> getInventoryDeals(){return inventoryDeals;}
    
    /**
     * prints inventoryItem
     * @param list
     */
    public void printInventoryItem(ArrayList<Item> list)
    {
        System.out.println("The items you are currently carrying are:");
        for(int i = 0; i < list.size(); i++)
        {
            if(i< list.size()-1)
            {System.out.print(getInventoryItem().get(i).getName() + ", ");}
            else 
            {System.out.println(getInventoryItem().get(i).getName());}
        }  
    }
      


    //TODO Ingen grund til at lave constructor der er empty. fylde kode.
   

    //TODO Når du laver getter/setter method skal classen attribute være private. Ellers ingen grund
    //TODO getter og sætter skal meget gerne "stå" sammen


    /**
     * --Intern Use
     * Player can max have 3 items on them, if more player chooses witch one to 
     * replace or if not add 'item'  to inventory
     * @param item
     */
    public void inventoryUpdateItem(Item item) // laver en metode der kan tilføjes til pickup item
    {
        
       
        // If inventory is not filled, max 3, add to inventory and print text
        //TODO ALDRIG hardcode tal i din kode. Hellere lave en global variable til at styre inventorySpace
        if(inventoryItem.size() != maxItem && inventoryItem.size() < maxItem ) 
        {
            inventoryItem.add(item);
            System.out.println(item.getName() + " Has been added to your inventory ");
            
        }
        //If inventory is filled, player chooses what to do
        //TODO: Igen InventorySpace
        else if (inventoryItem.size() == maxItem) {
            System.out.println("You are already carrying 3 Items and do not have "
                    + "the strength to carry any more, too bad");

            Scanner scan = new Scanner(System.in); //adding scanner
            int k = 0;
            while (k == 0){
            System.out.println("Would you like to replace an Item you are carrying?");
            System.out.println("Type 'yes', 'no' or 'see items'");
            String scanIn = scan.nextLine(); //TODO camelcasing //player chooses action from above //TODO hvorfor printe ud hvad bruger har skrevet?

            //if yes, choose which to replace
            //TODO hvorfor gør du det den vej?
            if (scanIn.equalsIgnoreCase("yes")) {
                int n = 0; //used for while loop
                while (n == 0) {
                    printInventoryItem(inventoryItem);
                    System.out.println("which item would you like to replace");
                    String scanYes = scan.nextLine(); //TODO camelcasing
                    //Goes though inventory to find and replace the choosen item
                    for (int i = 0; i < inventoryItem.size(); i++) {

                        int m = 0; //Used too keep track of count
                       //TODO int default value er 0. Derfor er dette lige gyldigt
                        if (scanYes.equals(inventoryItem.get(i).getName())) //goes through inventory
                        {
                            //If the written word is in inventory , the item gets
                            //replaced by the new one
                            inventoryItem.set(i, item);
                            System.out.println("The item " + inventoryItem.get(i).getName() +
                                    " has been replaced with the item " + item.getName());
                            n++; // while loop out
                            //TODO break i stedet for det der
                        }
                        else //adds one to m for each item that does not mach the choosen
                        {
                            m ++;
                        }
                        if (m == maxItem) //TODO inventory space - global
                        //when m equals (max items) then written wrong word since 
                        //player can only have 3 items
                        {
                            System.out.println("The written item is not in your possession");

                        }
                    }
                } //end of while loop
                k++;
            } //If no continue game


            if ("no".equals(scanIn) || "No".equals(scanIn)) 
            {
                System.out.println("No new items for you i guess.");
                k++;
            }// breaks out of outerloop. //TODO HVilket loop?            
            if ("see items".equals(scanIn) || "See items".equals(scanIn)) {
                printInventoryItem(inventoryItem);                
            }
            }
        }
    }
    

    /**
     *  ------> SHOULD BE USED IN ANOTHER CLASS OR COMMANDWORD <--------
     * goes through Items in kultural room, player chooses if wanna take or not
     * @param list --> List of Items in Country
     */
    public void SearchForItems(ArrayList<Item> list)
    {
        System.out.println("Your search was fruitfull");
        for(int s = 0; s < list.size(); s++)
        {
            int f = 0; while (f == 0){
            System.out.println("You stumblede upon the item " + list.get(s).getName());            
            System.out.println("wanna take it??");
            Scanner sc = new Scanner(System.in);
            String scan = sc.nextLine();
            if(scan.equalsIgnoreCase("yes"))
            {
                inventoryUpdateItem(list.get(s));
                System.out.println("Lets move on");f++;
            }
            else if(scan.equalsIgnoreCase("no"))
            {
                System.out.println("Not your soul item i guess, moving on");f++;  
            } else{}}
        }
    }
    
    
    /**
     * Print inventory
     * har egentlig lavet en anden, så brug den her hvis ikke virker
     */
   /* public static void printInventory() {
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

    }*/

//Deal part
    /**
     * Prints inventory for choosen category List
     * @param list 
     */
    public void printInventoryCategory(ArrayList<Deal> list)
    {
        System.out.println("The Deals you are currently carrying are:");
        for(int i = 0; i < list.size(); i++)
        { 
            if (i < list.size() - 1)
            {System.out.print(list.get(i).getName() + ",");}
            else 
            {System.out.println(list.get(i).getName());}   
        }
    }
    /**
     * prints deals in an ArrayList
     * @param list
     */
     public void printInventoryDeals(ArrayList<Deal> list)
    {
        for(int i = 0; i < list.size(); i++)
        { 
            if (i < list.size() - 1)
            {System.out.print(list.get(i).getName() + ",");}
            else 
            {System.out.println(list.get(i).getName());}   
        }
    }
    
    /**
     *Adds deal with category food into ArrayList food if there is space (Max 1 deal)
     * or replaces a chosen deal
     * @param deal
     */
    //Noget galt her, ved ikke hvad {} sat forkert i printInventory.
    public void addFood(Deal deal){
        //TODO global food max storage variable
        //Adds deal to ArrayList if food has no items (Max 1)
        if (food.size() < maxFood) {
            food.add(deal);
            System.out.println("congratulations, you have now entered a new deal ");
            System.out.println("The deal " +  deal.getName() + " is now in your inventory");
        }
        // If food is filled, player chooses if and what deal to replace
        else {
            //TODO bad practise at gøre det på denne måde
            //TODO break istedet for tal
            int m = 0; //for while loop
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'food'");
                printInventoryCategory(food);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine(); // Player chooses if replace
                if(scan.equals("yes")){
                    food.set(0, deal);
                    System.out.println("congratulations, you have now entered a "
                            + "new deal");
                    System.out.println("The deal" + deal.getName() + "is now "
                            + "in your inventory");
                    m = 1; // out of loop
                }
                else if ( scan.equalsIgnoreCase("No")) {
                    System.out.println("No new deals for you :(");
                    m = 1; // out of loop
                } else{
                    System.out.println("I do not understand the command");
                    // continue loop cause player wrote wrong word
                }
            }
        }
    }

    


    /**
     *Adds deal with category food into ArrayList energy if there is space (Max 2 deal)
     * or replaces a chosen deal
     * @param deal
     */
    public void addEnergy(Deal deal) {
        int n = 0;
        if (energy.size() < maxEnergy) {
            energy.add(deal);
            System.out.println("congratulations, you have now entered a new deal");
            System.out.println("The deal " + deal.getName() + " is now in your inventory");
        } else {
            int m = 0; //for loop 1
            while (m == 0) {
                System.out.println("You can only have 2 deal in the category 'Energy'");
                printInventoryCategory(energy);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine();//player chooses action
            if (scan.equalsIgnoreCase("yes")) {
                    int k = 0; //used to keep count
                    
                    while (n == 0) {
                        System.out.println("Which deal whould you like to replace");
                        printInventoryCategory(energy);
                        scan = sc.nextLine();
                        for (int i = 0; i < energy.size(); i++) {
                            if (energy.get(i).getName().equalsIgnoreCase(scan)) {
                                energy.set(i, deal);
                                System.out.println("congratulations, you have now "
                                        + "entered a new deal");
                                System.out.println("The deal " + deal.getName() +
                                        " is now in your inventory");
                                m++;
                                n++;                                
                                //out of loop 2
                                //TODO BREAK
                            }
                        }
                        
                    }
                    
                    //out loop 1
                    //TODO break...
                } 
                else if (scan.equalsIgnoreCase("no")) {
                    System.out.println("No new deals for you :(");
                    m++; // out loop 1
                    //TODO BREAK
                } else {
                    System.out.println("I do not understand the command");
                    //keep loop 1
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
    public void addKnowledge(Deal deal) {
         if (knowledge.size() < maxKnowledge) {
            knowledge.add(deal);
            System.out.println("congratulations, you have now entered a new deal ");
            System.out.println("The deal " +  deal.getName() + " is now in your inventory");
        }
        // If food is filled, player chooses if and what deal to replace
        else {
            //TODO bad practise at gøre det på denne måde
            //TODO break istedet for tal
            int m = 0; //for while loop
            while (m == 0) {
                System.out.println("You can only have 1 deal in the category 'knowledge'");
                printInventoryCategory(knowledge);
                System.out.println("Would you like to replace your current deal??");
                Scanner sc = new Scanner(System.in);
                String scan = sc.nextLine(); // Player chooses if replace
                if(scan.equals("yes")){
                    knowledge.set(0, deal);
                    System.out.println("congratulations, you have now entered a "
                            + "new deal");
                    System.out.println("The deal" + deal.getName() + "is now "
                            + "in your inventory");
                    m = 1; // out of loop
                }
                else if ( scan.equalsIgnoreCase("No")) {
                    System.out.println("No new deals for you :(");
                    m = 1; // out of loop
                } else{
                    System.out.println("I do not understand the command");
                    // continue loop cause player wrote wrong word
                }
            }
        }
    }
    
    //venter til senere
    /*
    public void printInventoryDeals()
    {
        for (int i = 0 ; i < inventoryDeals.size() - 1; i++)
        {
            if( i < inventoryDeals.size() )
            {
                for(int m = 0; m < inventoryDeals.get(i).size() ; m++)
                {
                    System.out.print("1 ");
                    System.out.print(inventoryDeals.get(i).get(m).getName() + ", ");
                }
            } else{
                for(int m = 0; m < inventoryDeals.get(i).size(); m++)
                {
                    System.out.print("2 ");
                    if (m < inventoryDeals.get(i).size())
                    {System.out.print(inventoryDeals.get(i).get(m).getName() + ",");}
                    else
                        System.out.print("3 ");
                    {System.out.print(inventoryDeals.get(i).get(m).getName() + "");}
                }
            }
        }
    }*/


    public void inventoryUpdateDeals(Deal deal){
        inventoryDeals.add(food);
        inventoryDeals.add(energy);
        inventoryDeals.add(knowledge);


        if (deal.getCategory().equals("food")) {
            addFood(deal);
        } else if (deal.getCategory().equals("energy")) {
            addEnergy(deal);
        } else if (deal.getCategory().equals("knowledge")) {
            addKnowledge(deal);
        }

    }
}
        



