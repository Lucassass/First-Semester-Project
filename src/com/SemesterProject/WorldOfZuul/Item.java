/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

/**
 * @author tes_7
 */
public class Item {

    private String name; //Name of item
    private String countryGood; // Name of the country the item has a good effect on
    private int pointsGood; // The + points you get from being in the good country
    private String countryBad; //Name of the bad country the item has a bad effect on
    private int pointsBad; // The - points you get from being in the bad country
    private String textBad; // Text that prints during dice roll
    private String textGood; // Text that prints during dice roll

    public Item(String name, String countryGood, int pointsGood,
                String countryBad, int pointsBad){
        this.name = name;
        this.countryGood = countryGood;
        this.pointsGood = pointsGood;
        this.countryBad = countryBad;
        this.pointsBad = pointsBad;

        String textBad0 = null; textBad = textBad0;
        String textGood0 = null; textGood = textGood0;
    }
    /**
     * @return TextBad
     */
    public String getTextBad(){return textBad;}
    /**
     * @param textBad
     */
    public void setTextBad(String textBad){this.textBad = textBad;}
    /**
     * @return TextGood
     */
    public String getTextGood(){return textGood;}
    /**
     * @param textGood
     */
    public void setTextGood(String textGood){this.textGood = textGood;}
    /**
     * @return countryGood
     */
    public String getCountryGood(){return countryGood;}
    /**
     * @return contryBad
     */
    public String getCountryBad(){return countryBad;}
    /**
     * @return pointsGood
     */
    public int getPointsGood(){return pointsGood;}
    /**
     * @return pointsBad
     */
    public int getPointsBad(){return pointsBad;}
    /**
     * @return name
     */
    public String getName() {return name;}
}

    