/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

/**
 * @author tes_7
 */
public class Deal {

    private String name; // Name of deal
    private String category; //Catogery of deals
    private int sustainabilityPoints; // effects on sustainability
    private int energyPoints; //effect on energy
    private int environmentPoints; // effect on environment
    private String information; //Information about deal
    private int price;// price on deal

    public Deal(){};

    //Hoved Deal objektet der gerne skulle bruges
    public Deal(String name, String category, int sustainabilityPoints,
                int energyPoints, int environmentPoints, int price, String info)
    {
        this.name = name ;
        this.category = category;
        this.sustainabilityPoints = sustainabilityPoints;
        this.energyPoints = energyPoints;
        this.environmentPoints = environmentPoints;
        this.information = info;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    /**
     * @return information about the deal
     */
    public String getInfo(){return information;}
    /**
     * sets information about each deal
     * @param info - about deal
     */
    public void setInfo(String info){information = info;}
    /**
     * @return Name
     */
    public String getName(){return name;}
    /**
     * @return category
     */
    public String getCategory(){return category;}
    /**
     * @return points - for end points
     */
    public int getSustainabilityPoints(){return sustainabilityPoints;}
    /**
     * @return energyPoints - for end points
     */
    public int getEnergyPoints(){return energyPoints;}
    /**
     * @return environmentPoints - for end points
     */
    public int getEnvironmentPoints(){return environmentPoints;}

}
