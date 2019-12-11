/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.DomainLogic;

/**
 * @author tes_7
 */

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.DomainLogic.Enum.DealCategory;
import com.SemesterProject.Interfaces.Entities.IDeal;
import com.SemesterProject.Interfaces.Entities.IItem;
import com.SemesterProject.Interfaces.IInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory implements IInventory {

    private static Inventory instance = new Inventory();
    private ArrayList<IItem> items = new ArrayList<>(); //ArrayList that contains Items
    private ArrayList<ArrayList<IDeal>> deals = new ArrayList<>(); // ArrayList that contains deals
    private ArrayList<IDeal> food = new ArrayList<>(); //Deals with category food (will be placed in inventoryDeals
    private ArrayList<IDeal> energy = new ArrayList<>(); //Deals with category energy (will be placed in inventoryDeals
    private ArrayList<IDeal> knowledge = new ArrayList<>(); //Deals with category knowledge(will be placed in inventoryDeals

    private final int maxItem = 3;
    private final int maxFood = 1;
    private final int maxEnergy = 2;
    private final int maxKnowledge = 1;

    private Inventory()
    {
        deals.add(food);
        deals.add(energy);
        deals.add(knowledge);
    }

    @Override
    public boolean isFullOfDeals(IDeal deal)
    {
        if (deal.getCategory() == DealCategory.Energy && energy.size() <= maxEnergy)
        {
            return false;
        }
        else if (deal.getCategory() == DealCategory.Food && food.size() <= maxFood)
        {
            return false;
        }
        else if (deal.getCategory() == DealCategory.Knowledge && knowledge.size() <= maxKnowledge)
        {
            return false;
        }

        return true;
    }

    @Override
    public boolean isFullOfItems() {
        return items.size() >= maxItem;
    }


    @Override
    public void addDeal(IDeal deal)
    {
        if (deal.getCategory() == DealCategory.Energy)
        {
            energy.add(deal);
        }
        else if (deal.getCategory() == DealCategory.Food)
        {
            food.add(deal);
        }
        else if (deal.getCategory() == DealCategory.Knowledge)
        {
            knowledge.add(deal);
        }
    }



    @Override
    public void addItem(IItem item)
    {
        items.add(item);
    }


    public void removeItem(IItem item)
    {
        for (var tempItems : items)
        {
            if (tempItems.getUuid() == item.getUuid())
            {
                items.remove(tempItems);
                break;
            }
        }
    }

    @Override
    public void removeDeal(IDeal deal)
    {
        outside:
        for (var categories : deals)
        {
            for (var temp : categories)
            {
                if (temp.getUuid() == deal.getUuid())
                {
                    categories.remove(temp);
                    break outside;
                }
            }
        }

    }


    /**
     * ---> TO BE USED EVERY TIME YOU WANT TO CALL A METHOD FROM ITEM <---
     * @return
     */
    public static Inventory getInstance(){return instance;}


    /**
     * @return inventory Deals
     */
    public ArrayList<ArrayList<IDeal>> getDeals(){return deals;}

}
