package com.SemesterProject.iceandfire.interfaces;

import com.SemesterProject.iceandfire.data.tables.Item;
import com.SemesterProject.iceandfire.data.tables.Deal;
import com.SemesterProject.iceandfire.dto.ItemDTO;

import java.util.ArrayList;

public interface IInventoryData
{
    ArrayList<ArrayList<Deal>> getInventoryDeals();
    ArrayList<Deal> getEnergy();
    ArrayList<Deal> getFood();
    ArrayList<Deal> getKnowledge();
    ArrayList<ItemDTO> getInventoryItem();
    int getItemSize();
}
