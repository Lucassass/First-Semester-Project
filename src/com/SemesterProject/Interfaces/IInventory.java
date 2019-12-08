package com.SemesterProject.Interfaces;

import com.SemesterProject.DomainLogic.Entities.Deal;
import com.SemesterProject.DomainLogic.Entities.Item;
import com.SemesterProject.DomainLogic.Enum.DealCategory;

import java.util.List;
import java.util.UUID;

public interface IInventory
{
    void addDeal(Deal deal);
    List<Deal> getDeals();
    List<Item> getItems();
    void addItem(Item item);
    void removeDeal(Deal deal);
    boolean isFull(Deal deal);
}
