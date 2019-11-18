package com.SemesterProject.iceandfire.data;

import com.SemesterProject.iceandfire.data.tables.Deal;
import com.SemesterProject.iceandfire.data.tables.Item;
import com.SemesterProject.iceandfire.dto.ItemDTO;
import com.SemesterProject.iceandfire.dto.ItemMapper;
import com.SemesterProject.iceandfire.interfaces.IInventoryData;

import java.util.ArrayList;

public class InventoryData implements IInventoryData
{

    private ArrayList<Item> inventoryItem = new ArrayList<>(); //ArrayList that contains Items
    private ArrayList<ArrayList<Deal>> inventoryDeals = new ArrayList<>(); // ArrayList that contains deals
    private ArrayList<Deal> food = new ArrayList<>(); //Deals with category food (will be placed in inventoryDeals
    private ArrayList<Deal> energy = new ArrayList<>(); //Deals with category energy (will be placed in inventoryDeals
    private ArrayList<Deal> knowledge = new ArrayList<>(); //Deals with category knowledge(will be placed in inventoryDeals

    public ArrayList<ArrayList<Deal>> getInventoryDeals() {
        return inventoryDeals;
    }

    public ArrayList<Deal> getEnergy() {
        return energy;
    }

    public ArrayList<Deal> getFood() {
        return food;
    }

    public ArrayList<Deal> getKnowledge() {
        return knowledge;
    }

    public ArrayList<ItemDTO> getInventoryItem() {
        return ItemMapper.map(inventoryItem);
    }

    public int getItemSize()
    {
        return inventoryItem.size();
    }

}
