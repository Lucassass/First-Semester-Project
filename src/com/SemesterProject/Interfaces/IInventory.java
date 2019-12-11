package com.SemesterProject.Interfaces;

import com.SemesterProject.DomainLogic.Enum.DealCategory;
import com.SemesterProject.Interfaces.Entities.IDeal;
import com.SemesterProject.Interfaces.Entities.IItem;

public interface IInventory
{
    void addDeal(IDeal deal);
    void addItem(IItem item);
    void removeItem(IItem item);
    void removeDeal(IDeal deal);
    boolean isFullOfDeals(DealCategory deal);
    boolean isFullOfItems();

}
