package com.SemesterProject.ASCII.Color;

public class ColorPrint
{
    private static final String RESET = "\033[0m";

    public static void print(String text, Attribute attribute, FColor fcolor, BColor bColor)
    {
        System.out.print(fcolor.getColor(attribute) + bColor.getColor() + text + RESET);
    }

    public static void print(String text, Attribute attribute, FColor fcolor)
    {
        System.out.print(fcolor.getColor(attribute) + text + RESET);
    }

    public static void print(String text, Attribute attribute)
    {
        System.out.print(FColor.Normal.getColor(attribute) + text + RESET);
    }

    public static void print(String text, FColor fColor)
    {
        System.out.print(fColor.getColor() + text + RESET);
    }

    public static void print(String text, BColor bColor)
    {
        System.out.print(bColor.getColor() + text + RESET);
    }

    public static void print(String text, FColor fColor, BColor bColor)
    {
        System.out.print(fColor.getColor() + bColor.getColor() + text + RESET);
    }

    public static void print(String text)
    {
        System.out.print(text);
    }





    public static void println(String text, Attribute attribute, FColor fcolor, BColor bColor)
    {
        System.out.println(fcolor.getColor(attribute) + bColor.getColor() + text + RESET);
    }

    public static void println(String text, Attribute attribute, FColor fcolor)
    {
        System.out.println(fcolor.getColor(attribute) + text + RESET);
    }

    public static void println(String text, Attribute attribute)
    {
        System.out.println(FColor.Normal.getColor(attribute) + text + RESET);
    }

    public static void println(String text, FColor fColor)
    {
        System.out.println(fColor.getColor() + text + RESET);
    }

    public static void println(String text, BColor bColor)
    {
        System.out.println(bColor.getColor() + text + RESET);
    }

    public static void println(String text, FColor fColor, BColor bColor)
    {
        System.out.println(fColor.getColor() + bColor.getColor() + text + RESET);
    }

    public static void println(String text)
    {
        System.out.println(text);
    }


}
