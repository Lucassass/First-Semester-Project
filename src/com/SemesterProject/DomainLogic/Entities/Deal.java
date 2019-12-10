/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.DomainLogic.Entities;

import com.SemesterProject.DomainLogic.Enum.DealCategory;

import java.util.UUID;

/**
 * @author tes_7
 */
public class Deal {

    private String name; // Name of deal
    private DealCategory category; //Catogery of deals
    private int sustainabilityPoints; // effects on sustainability
    private int energyPoints; //effect on energy
    private int environmentPoints; // effect on environment
    private String description; //Information about deal
    private int price;// price on deal
    private UUID uuid;

    //Hoved Deal objektet der gerne skulle bruges
    public Deal(String name, DealCategory category, int sustainabilityPoints,
                int energyPoints, int environmentPoints, int price, String info)
    {
        this.name = name ;
        this.category = category;
        this.sustainabilityPoints = sustainabilityPoints;
        this.energyPoints = energyPoints;
        this.environmentPoints = environmentPoints;
        this.description = info;
        this.price = price;
        uuid = UUID.randomUUID();
    }


    public int getPrice()
    {
        return price;
    }

    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return information about the deal
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return Name
     */
    public String getName()
    {
        return name;
    }
    /**
     * @return category
     */
    public DealCategory getCategory()
    {
        return category;
    }
    /**
     * @return points - for end points
     */
    public int getSustainabilityPoints()
    {
        return sustainabilityPoints;
    }
    /**
     * @return energyPoints - for end points
     */
    public int getEnergyPoints()
    {
        return energyPoints;
    }
    /**
     * @return environmentPoints - for end points
     */
    public int getEnvironmentPoints()
    {
        return environmentPoints;
    }

}
