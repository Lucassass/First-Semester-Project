package com.SemesterProject.iceandfire.dto;

import com.SemesterProject.iceandfire.data.tables.Item;

import java.util.ArrayList;

public class ItemMapper
{
    public static ArrayList<ItemDTO> map(ArrayList<Item> items)
    {
        ArrayList<ItemDTO> newList = new ArrayList<>();
        for (var item: items) {
            newList.add(map(item));
        }
        return newList;
    }

    public static ItemDTO map(Item item)
    {
        if (item == null) return null;

        return new ItemDTO(item.getName(), item.getCountryGood(), item.getPointsGood(), item.getCountryBad(), item.getPointsBad());

    }
}
