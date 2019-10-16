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

public class Inventory extends Item
{

    private ArrayList <Item> inventory = new ArrayList(); //ArrayList that contains Items

    
    public Inventory() // Create inventory constructor, so that we can use it later
    {
        
    }

    /**
     * Gets inventory to other classes
     * @return 
     */
    public ArrayList<Item> getInventory()
    {
        return inventory;
    }
    
    


    /**
     * When player picks up item, add too inventory
     */
    public void inventoryUpdate()
    {
        //#2 - when pick up an item add too inventory
    }

    /**
     * When player reaches max items, player has to choose if want to still pick
     * up and if yes, then which too drop
     */
    public void inventoryMaxItems()
    {
        if(inventory.size() > 3)
        {

        }
    }

    /**
     * Print inventory
     */
    public void printInventory()
    {
        System.out.println("The items which you are currently carrying, are:");
        for(int i = 0; i < inventory.size() ; i++)
        { if (i < inventory.size() -1){
            for (Item item : inventory)
            {
                System.out.print(inventory.get(i) +" , ");
            }
        } if (i == inventory.size()){

            for (Item item : inventory)
            {
                System.out.print(inventory.get(i));
            }
        }
            System.out.println(inventory);
        }
    }
}
