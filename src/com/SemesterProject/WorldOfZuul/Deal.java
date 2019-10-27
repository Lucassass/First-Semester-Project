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
    
    static String name; // Name of deal
    static String category; //Catogery of deals
    static int sustainabilityPoints; // effects on sustainability
    static int energyPoints; //effect on energy
    static int environmentPoints; // effect on environment
    static String information; //Information about deal
    
    
    public Deal()
    {
        name = null; category = null; sustainabilityPoints = 0; 
        energyPoints = 0; environmentPoints = 0; information = null;
    }
    //Hoved Deal objektet der gerne skulle bruges
    public Deal(String name, String category, int sustainabilityPoints, 
            int energyPoints, int environmentPoints)
    {
        name = Deal.name;
        category = Deal.category;
        sustainabilityPoints = Deal.sustainabilityPoints;
        energyPoints = Deal.energyPoints;
        environmentPoints = Deal.environmentPoints;
        String info = information; 
    }
    /**
     * 
     * returns information
     * @return 
     */
    public static String getInfo(){return information;}
    /**
     * sets information about each deal
     * @param info 
     */
    public static void setInfo(String info){info = getInfo();}
    
    /**
     *    
     * @return 
     */
    public static String getName(){return name;}
    
    /**
     * category
     * @return 
     */
    public static String getCategory(){return category;}
    /**
     * 
     * @param deal
     * @return 
     */
    public static int getSusPoints(Deal deal){return sustainabilityPoints;}
    /**
     * 
     * @param deal
     * @return 
     */
    public static int getEnergyPoints(Deal deal){return energyPoints;}
    /**
     * 
     * @param deal
     * @return 
     */
    public static int getEnvPoints(Deal deal){return environmentPoints;}
    
    
}
