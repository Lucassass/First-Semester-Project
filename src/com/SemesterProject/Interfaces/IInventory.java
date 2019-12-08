package com.SemesterProject.Interfaces;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.DomainLogic.Enum.DealCategory;

import java.util.List;

public interface IInventory
{
    void addDeal(Deal deal);
    List<Deal> getDeals();
    List<Item> getItems();
    void addItem(Item item);
}
