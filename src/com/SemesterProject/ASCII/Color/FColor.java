package com.SemesterProject.ASCII.Color;

import java.util.Arrays;

public enum  FColor
{
    Normal("\033[0m"),
    Black("\033[0;30m"),
    Red("\033[0;31m"),
    Green("\033[0;32m"),
    Yellow("\033[0;33m"),
    Blue("\033[0;34m"),
    Purple("\033[0;35m"),
    Cyan("\033[0;36m"),
    White("\033[0;37m"),
    BlackBright("\033[0;90m"),
    RedBright("\033[0;91m"),
    GreenBright("\033[0;92m"),
    YellowBright("\033[0;93m"),
    BlueBright("\033[0;94m"),
    PurpleBright("\033[0;95m"),
    CyanBright("\033[0;96m"),
    WhiteBright("\033[0;97m");


    private String color;

    FColor(String key) {
        this.color = key;
    }

    String getColor() {
        return color;
    }

    String getColor(Attribute attribute) {
        color = changeAttribute(attribute);
        return color;
    }

    private String changeAttribute(Attribute attribute)
    {
        var color = this.getColor().split("");
        color[2] = attribute.getOpcode();
        return buildString(color);
    }

    private String buildString(String[] arrayString)
    {
        StringBuilder newString = new StringBuilder();
        for (var character: arrayString) {
            newString.append(character);
        }
        return newString.toString();
    }


}
