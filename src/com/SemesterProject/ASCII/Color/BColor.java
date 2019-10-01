package com.SemesterProject.ASCII.Color;

public enum  BColor
{
    Black("\033[40m"),
    Red("\033[41m"),
    Green("\033[42m"),
    Yellow("\033[43m"),
    Blue("\033[44m"),
    Purple("\033[45m"),
    Cyan("\033[46m"),
    White("\033[47m"),
    BlackBRIGHT("\033[0;100m"),
    RedBRIGHT("\033[0;101m"),
    GreenBRIGHT("\033[0;102m"),
    YellowBRIGHT("\033[0;103m"),
    BlueBRIGHT("\033[0;104m"),
    PurpleBRIGHT("\033[0;105m"),
    CyanBRIGHT("\033[0;106m"),
    WhiteBRIGHT("\033[0;107m");
    private String color;

    BColor(String bColor) {
        this.color = bColor;
    }

    String getColor() {
        return color;
    }

}
