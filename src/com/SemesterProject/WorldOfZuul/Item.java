/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;


/**
 *
 * @author tes_7
 */
public class Item extends Deal {
    
    String name; //Name of item
    String countryGood; // Name of the country the item has a good effect on
    int pointsGood; // The + points you get from being in the good country
    String countryBad; //Name of the bad country the item has a bad effect on
    int pointsBad; // The - points you get from being in the bad country
    
    
    
    
    public Item(String name, String countryGood, int pointsGood,
            Array countryBad, int pointsBad)
    {
        
    }
    
    /**
     * 
     * @param item
     * @return name
     */   
    public String getName(Item item){return name;}
    /**
     * 
     * @param item
     * @return countryGood
     */
    public String getCountryGood(Item item){return countryGood;}
    /**
     * 
     * @param item
     * @return contryBad
     */
    public String getcountryBad(Item item){return countryBad;}
    /**
     * 
     * @param item
     * @return pointsGood
     */
    public int getPointsGood(Item item){return pointsGood;}
    /**
     * 
     * @param item
     * @return pointsBad
     */
    public int getPointsBad(Item item){return pointsBad;}
}

    