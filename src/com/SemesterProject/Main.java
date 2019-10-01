package com.SemesterProject;

import com.SemesterProject.ASCII.ASCIIDrawing;
import com.SemesterProject.ASCII.ASCIIMap;
import com.SemesterProject.ASCII.Color.Attribute;
import com.SemesterProject.ASCII.Color.BColor;
import com.SemesterProject.ASCII.Color.ColorPrint;
import com.SemesterProject.ASCII.Color.FColor;
import com.SemesterProject.WorldOfZuul.Game;

import java.awt.*;

import static com.SemesterProject.ASCII.Color.ColorPrint.*;

public class Main {

    public static void main(String[] args) {
	// write your code here



        println("Hey", Attribute.Bold, FColor.BlueBright, BColor.Green);
        println("underlineWhite", Attribute.Underline);

        println("Woow", BColor.GreenBRIGHT);
        println("What is this?");
        ASCIIDrawing.load("Dragon");
        Game game = new Game();
        game.play();
      
        // Lucas's comment
        // hello
        //Morten
        //Hey

    }
}
