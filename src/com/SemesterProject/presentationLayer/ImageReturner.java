package com.SemesterProject.presentationLayer;

import javafx.scene.image.Image;

public class ImageReturner
{
    /**
     *
     * @param countryName name of the country you want to get culture room image from
     * @return a image of the culture room from the specified country
     */
    public static Image cultureRoom(String countryName)
    {
        switch (countryName.toLowerCase())
        {
            case "usa":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/cultureRoomUSA.png"));
            case "russia":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/cultureRoomRussia.png"));
            case "japan":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/cultureRoomJapan.png"));
            case "germany":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/cultureRoomGermany.png"));
            case "india":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/cultureRoomIndia.png"));
            case "china":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/cultureRoomChina.png"));
        }

        return null;
    }

    /**
     *
     * @param countryName name of the country you want to get outside room image from
     * @return a image of the outside room from the specified country
     */
    public static Image outsideRoom(String countryName)
    {
        switch (countryName.toLowerCase())
        {
            case "usa":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/outsideUSA.png"));
            case "russia":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/outsideRussia.png"));
            case "japan":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/outsideJapan.png"));
            case "germany":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/outsideGermany.png"));
            case "india":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/outsideIndia.png"));
            case "china":
                return new Image(ImageReturner.class.getResourceAsStream("/images/rooms/outsideChina.png"));
        }

        return null;
    }

    /**
     *
     * @param countryName name of the country you want to get global map image from
     * @return a image of the global map room from the specified country
     */
    public static Image globalMap(String countryName)
    {
        switch (countryName.toLowerCase())
        {
            case "usa":
                return new Image(ImageReturner.class.getResourceAsStream("/images/USA.png"));
            case "russia":
                return new Image(ImageReturner.class.getResourceAsStream("/images/Russia.png"));
            case "japan":
                return new Image(ImageReturner.class.getResourceAsStream("/images/Japan.png"));
            case "germany":
                return new Image(ImageReturner.class.getResourceAsStream("/images/Germany.png"));
            case "india":
                return new Image(ImageReturner.class.getResourceAsStream("/images/India.png"));
            case "china":
                return new Image(ImageReturner.class.getResourceAsStream("/images/China.png"));
        }
        return null;
    }
}
