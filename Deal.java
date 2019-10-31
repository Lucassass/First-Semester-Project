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
public class Deal {

    //TODO Hvorfor er de static?  det må de ikke :(
    //TODO: lavet alle private
    private String name; // Name of deal
    private String category; //Catogery of deals
    private int sustainabilityPoints; // effects on sustainability
    private int energyPoints; //effect on energy
    private int environmentPoints; // effect on environment
    private String information; //Information about deal
    
    public Deal(){};
    
    //Hoved Deal objektet der gerne skulle bruges
    public Deal(String name, String category, int sustainabilityPoints, 
            int energyPoints, int environmentPoints)
    {
        this.name = name ;
        this.category = category;
        this.sustainabilityPoints = sustainabilityPoints;
        this.energyPoints = energyPoints;
        this.environmentPoints = environmentPoints;
        String info = null;
        this.information = info; 
    }
    /**
     * 
     * returns information
     * @return 
     */
    public String getInfo(){return information;}
    /**
     * sets information about each deal
     * @param info 
     */
    public void setInfo(String info){information = info;}
    
    /**
     *    
     * @return 
     */
    public String getName(){return name;}
    
    /**
     * category
     * @return 
     */
    public String getCategory(){return category;}
    /**
     * 
     * @return 
     */
    public int getSustainabilityPoints(){return sustainabilityPoints;}
    /**
     * 
     * @return 
     */
    public int getEnergyPoints(){return energyPoints;}
    /**
     *
     * @return 
     */
    public int getEnvironmentPoints(){return environmentPoints;}
    
    
}
