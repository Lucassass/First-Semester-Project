/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

/**
 * @author tes_7
 */
public class Item extends Deal {
    
    String name; //Name of item
    String countryGood; // Name of the country the item has a good effect on
    int pointsGood; // The + points you get from being in the good country
    String countryBad; //Name of the bad country the item has a bad effect on
    int pointsBad; // The - points you get from being in the bad country
    String textBad; // Text that prints during dice roll
    String textGood; // Text that prints during dice roll
    
    
    
    public Item(String name, String countryGood, int pointsGood,
            String countryBad, int pointsBad)
    {
        name = this.name;
        countryGood = this.countryGood;
        pointsGood = this.pointsGood;
        countryBad = this.countryBad;
        pointsBad = this.pointsBad; 
        String textBad0 = textBad;        
        String textGood0 = textGood;       
    }
    /**
     * TextBad
     * @return 
     */
    public String getTextBad(){return textBad;} 
    /**
     * 
     * @param textBad 
     */
    public void setTextBad(String textBad){textBad = getTextBad();}

    /**
     * @return countryGood
     */
    public String getCountryGood(){return countryGood;}
    /**
     * @return contryBad
     */
    public String getcountryBad(){return countryBad;}
    /**
     * @return pointsGood
     */
    public int getPointsGood(){return pointsGood;}
    /**
     * @return pointsBad
     */
    public int getPointsBad(){return pointsBad;}
}

    