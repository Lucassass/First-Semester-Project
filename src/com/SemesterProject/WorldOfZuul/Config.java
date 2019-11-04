/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

import java.io.PrintStream;
import java.util.Random;

import com.SemesterProject.WorldOfZuul.Inventory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Config {
    private String dansk = "Danmark"; //slettes når opdatere rollDice
    private int diceAdvantage;

    /** - for bigger
     * @param main - result wants to return
     * @param under - ArrayList of Integer wants too add
     * @return Integer - result of adding list
     */
    public int addingList(int main, ArrayList<Integer> under)
    {
        int before = 0;
        for (int i = 0; i < under.size(); i++)
        {   main = before + under.get(i);
            before = main;}
        return main;
    }

    /**
     * Kan ikke få metoden til at virke, skulle gerne lægges til roll senere
     * Puts the advantages and disadvantages together, depending on the dice roll
     * @return
     */
    public int advantage()
    {
        int advantage = 0;
        Inventory in = Inventory.getInstance();
        ArrayList<Integer>  result = new ArrayList<>();
        for(int i = 0; i < in.getInventoryItem().size(); i++) //goes through inventory
       {
           if(in.getInventoryItem().get(i).getCountryGood().equalsIgnoreCase(dansk) )
           {   result.add(in.getInventoryItem().get(i).getPointsGood());}
           else if(in.getInventoryItem().get(i).getCountryBad().equals(dansk) )
           { result.add(in.getInventoryItem().get(i).getPointsBad());}
           else if(in.getInventoryItem().get(i).getCountryGood().equals(dansk)
                   && in.getInventoryItem().get(i).getCountryBad().equals(dansk))
           { result.add(in.getInventoryItem().get(i).getPointsGood()
                       + in.getInventoryItem().get(i).getPointsBad());}
       }return addingList(advantage, result);
    }
    /**
     * returns number between 0 and 7
     *
     * @return
     */
    public int random() {
        int roll = 0;
        while (1 > roll || roll > 6) {
            roll = (int) (Math.random() * 10);
        } return roll;
    }

    /**
     * Needs to replace the dansk part with objekt country.when player rolls dice
     * go through inventory and chek the advantages and disadvantages player gets
     * from items depending on the country.Then roll dice, if between 4 and 7 return true else return false     *
     * @return
     */
    public boolean rollDice() {
        Inventory in = Inventory.getInstance();
        int diceResult;
        int roll = random();
        diceResult = roll + advantage();
        return 3 < diceResult && diceResult < 7; //returns true or false
    }
    /**
     * roll dice to result and what happens
     * @param deal    --> Deal that player takes
     * @param country --> List of deals in current country
     */
    public void takeDeal(Deal deal, ArrayList<Deal> country) {
        Inventory in = Inventory.getInstance();
        boolean win = rollDice();
        if (win == true) {
            System.out.println("Congratulations, the deal was a succsses!!");
            in.inventoryUpdateDeals(deal, country);}
        else { System.out.println("The deal did not go through, not you lucky day i guess..");}
    }

    /**
     * ----> SHOULD BE USED IN ANOTHER CLASS OR COMMANDWORD <----
     * Asks which deal player want's to take, shows deal from country, player
     * chooses deal, shows info about deal, chooses if yes or no, if yes roll and
     * get deal or nor.
     * @param country --> The list of Deals in the country
     */
    public void startDeal(ArrayList<Deal> country) {
        Inventory in = Inventory.getInstance();
        System.out.print("Hello there");
        int m = 0;
        while (m == 0) {
            System.out.println("which deal would you like to negotiate?");

            in.printInventoryDeals(country);
            Scanner sc = new Scanner(System.in);
            String scan = sc.nextLine();

            for (int i = 0; i < country.size(); i++) {
                int k = 0;
                if (country.get(i).getName().equalsIgnoreCase(scan)) {
                    System.out.println(country.get(i).getInfo());
                    int n = 0;
                    while (n == 0) {
                        System.out.println("Would you like to negotiate the deal "
                                + country.get(i).getName() + " ?");
                        String scann = sc.nextLine();
                        if (scann.equalsIgnoreCase("yes")) {
                            takeDeal(country.get(i), country);
                            n++;}
                        else if (scann.equalsIgnoreCase("no")) {
                            n++;}
                        else {}
                    } m++;}
                else { k++;}
                if (k == country.size()) {}
            }
        }
    }
    /**
     * @return energyPoints
     */
    public int pointsEnergy() {
        int energyPoints = 0;
        Inventory in = Inventory.getInstance();
        ArrayList<Integer> energy = new ArrayList<>();
        for (int i = 0; i < in.getInventoryDeals().size(); i++) {
            for (int m = 0; m < in.getInventoryDeals().get(i).size(); m++)
            {energy.add(in.getInventoryDeals().get(i).get(m).getEnergyPoints()); }
        } return addingList(energyPoints, energy)/2;
    }

    /**
     * @return sustainabilityPoints
     */
    public int pointsSustainability() {
        int sustainabilityPoints = 0;
        Inventory in = Inventory.getInstance();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < in.getInventoryDeals().size(); i++) {
            for (int m = 0; m < in.getInventoryDeals().get(i).size(); m++)
            {list.add(in.getInventoryDeals().get(i).get(m).getSustainabilityPoints()); }
        } return addingList(sustainabilityPoints, list)/2;
    }

    /**
     * @return environmentPoints
     */
    public int pointsEnvironment() {
        int environmentPoints = 0;
        Inventory in = Inventory.getInstance();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < in.getInventoryDeals().size(); i++) {
            for (int m = 0; m < in.getInventoryDeals().get(i).size(); m++)
            {list.add(in.getInventoryDeals().get(i).get(m).getEnvironmentPoints()); }
        } return addingList(environmentPoints, list)/2;
    }

    /**
     * ---> USED TO PRINT PONT RESULTS <---
     * Prints energy, sustainability and environment points for end result
     */
    public void printPoints() {
        System.out.println("Through out the game, you have collected points to "
                + "to show just how environmental you are.\nLet's see how you did");
        System.out.println("Energy points: " + pointsEnergy());
        System.out.println("Sustainability points: " + pointsSustainability());
        System.out.println("Environment points: " + pointsEnvironment());
    }
}

 