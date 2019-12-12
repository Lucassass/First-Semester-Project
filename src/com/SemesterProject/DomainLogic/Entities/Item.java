/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.DomainLogic.Entities;

import com.SemesterProject.DomainLogic.Enum.Countries;
import com.SemesterProject.Interfaces.Entities.IItem;
import javafx.scene.image.Image;

import java.util.UUID;

public class Item implements IItem {

    private String name; //Name of item
    private Countries countryGood; // Name of the country the item has a good effect on
    private int pointsGood; // The + points you get from being in the good country
    private Countries countryBad; //Name of the bad country the item has a bad effect on
    private int pointsBad; // The - points you get from being in the bad country
    private UUID uuid;
    private Image image;
    private String textBad; // Text that prints during dice roll
    private String textGood; // Text that prints during dice roll
    private int index;

    public Item(String name, Countries countryGood, int pointsGood, Countries countryBad, int pointsBad,
                String imageName)
    {
        this.name = name;
        this.countryGood = countryGood;
        this.pointsGood = pointsGood;
        this.countryBad = countryBad;
        this.pointsBad = pointsBad;
        this.uuid = UUID.randomUUID();
        this.image = new Image(getClass().getResourceAsStream("/images/items/"+ imageName));

        textBad = "Sorry, but you chose poorly. This item does not belong to this country and therefore you pissed off the government." +
                " Try again another time";
        textGood = "This is the reason why the item gives positive points in a dice roll";;
    }

    /**
     *
     * @return index at culture room
     */
    public int getIndex() {
        return index;
    }

    /**
     *
     * @param index the index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @return image of item
     */
    public Image getImage()
    {
        return image;
    }

    /**
     *
     * @return uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return TextBad
     */
    public String getTextBad()
    {
        return textBad;
    }

    /**
     * @return TextGood
     */
    public String getTextGood()
    {
        return textGood;
    }

    /**
     * @return countryGood
     */
    public Countries getCountryGood()
    {
        return countryGood;
    }
    /**
     * @return contryBad
     */
    public Countries getCountryBad()
    {
        return countryBad;
    }
    /**
     * @return pointsGood
     */
    public int getPointsGood()
    {
        return pointsGood;
    }
    /**
     * @return pointsBad
     */
    public int getPointsBad()
    {
        return pointsBad;
    }
    /**
     * @return name
     */
    public String getName()
    {
        return name;
    }
}

    