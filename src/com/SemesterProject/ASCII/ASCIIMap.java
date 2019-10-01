package com.SemesterProject.ASCII;

public class ASCIIMap
{
    private static String[][] map;
    private static final String colorReset = "\u001B[0m";

    public static void show()
    {
        if (map == null)
        {
            try {
                throw new Exception("Map have not been setup yet. Please run \"setupMap\" before using this method");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (String[] strings : map) {
            for (int j = 0; j < map[0].length; j++) {
                String[] attributes = strings[j].split("|",3);
                String character = attributes[0];
                String color = attributes[2];
                System.out.print(setColor(color) + character + colorReset);
            }
            System.out.println();
        }
    }

    public static void setupMap(int x, int y, int startX, int startY)
    {
        map = createMap(x,y);
        setPlayerPos(startX,startY);
    }

    private static String setColor(String color)
    {
        switch (color)
        {
            case "red":
                return "\u001B[31m";
            case "green":
                return "\u001B[32m";
            default:
            {
                try {
                    throw new Exception("Color unknown");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static void setPlayerPos(int startX, int startY)
    {
        map[startX][startY] = "X|red";
    }

    private static String[][] createMap(int x, int y)
    {
        String[][] map = new String[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = "█|green";
            }
        }
        return map;
    }
}
