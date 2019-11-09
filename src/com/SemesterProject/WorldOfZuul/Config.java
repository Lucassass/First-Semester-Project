/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SemesterProject.WorldOfZuul;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Config
{
    private static int money = 99999999;
    private static int CommercialFlyingCost = 100;
    private static int PrivateFlyingCost = 250;
    private static int TrainCost = 50;
    private int diceAdvantage;

    public static boolean gotEnoughMoney(int amount){
        return money > amount;
    }

    public static void subtractMoney(int amount){
        money -= amount;
    }

    public static int getMoney() {
        return money;
    }

    public static boolean ranOutOfMoney(){
        return money < CommercialFlyingCost || money < PrivateFlyingCost || money < TrainCost;
    }
    /**
     *
     * @return true if you have enough money for train
     */
    public static boolean gotMoneyForTrain()
    {
        return money >= TrainCost;
    }

    /**
     *
     * @return true if you have enough money for commercial flying
     */
    public static boolean gotMoneyForCommercialFlying()
    {
        return money >= CommercialFlyingCost;
    }

    /**
     *
     * @return true if you have enough money for private flying
     */
    public static boolean gotMoneyForPrivateFlying()
    {
        return money >= PrivateFlyingCost;
    }

    /**
     * subtract commercial flying cost from your money
     */
    public static void buyFlyCommercial()
    {
        money -= CommercialFlyingCost;
    }

    /**
     * subtract private flying cost from your money
     */
    public static void buyFlyPrivate() {
        money -= PrivateFlyingCost;
    }

    /**
     * ----> SHOULD BE USED IN ANOTHER CLASS OR COMMANDWORD <----
     * Asks which deal player want's to take, shows deal from country, player
     * chooses deal, shows info about deal, chooses if yes or no, if yes roll and
     * get deal or nor.
     * @param dealInCountry --> The list of Deals in the country
     */
    public static void startDeal(ArrayList<Deal> dealInCountry, Country country) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        if (dealInCountry.isEmpty())
        {
            System.out.println("Sorry, no deals on the table");
            return;
        }

        Inventory inventory = Inventory.getInstance();
        System.out.print("Hello there");
        boolean run = true;
        while (run)
        {
            System.out.println("\nWhich deal would you like to negotiate?F");

            System.out.println(inventory.createDealsStringFor(dealInCountry));
            userInput = scanner.nextLine();

            for (int i = 0; i < dealInCountry.size(); i++) {
                if(userInput.equalsIgnoreCase("quit"))
                {
                    return;
                }

                if (dealInCountry.get(i).getName().equalsIgnoreCase(userInput)) {
                    System.out.println(dealInCountry.get(i).getInfo());
                    while (true) {
                        System.out.println("The deal will cost you " + dealInCountry.get(i).getPrice() + "\nWill you take the deal?");

                        userInput = scanner.nextLine();

                        if(userInput.equalsIgnoreCase("quit"))
                        {
                            return;
                        }
                        else if (userInput.equalsIgnoreCase("no"))
                        {
                            break;
                        }

                        var tookDeal = takeDeal(dealInCountry.get(i), dealInCountry,country, userInput);

                        if (tookDeal){
                            break;
                        }

                    }
                    run = false;
                }
            }
        }
    }


    /**
     * roll dice to result and what happens
     * @param deal    --> Deal that player takes
     * @param dealsInCountry --> List of deals in current country
     */
    public static boolean takeDeal(Deal deal, ArrayList<Deal> dealsInCountry, Country country, String input) {
        Inventory inventory = Inventory.getInstance();

        if(input.equalsIgnoreCase("quit"))
        {
            return false;
        }

        if(input.equalsIgnoreCase("yes"))
        {
            if (!Config.gotEnoughMoney(deal.getPrice()))
            {
                System.out.println("Not enough money");
                return false;
            }

            boolean win = rollDice(country);
            if (win)
            {
                System.out.println("Congratulations, the deal was a success!!");
                inventory.inventoryUpdateDeals(deal, dealsInCountry);
                dealsInCountry.remove(deal);
            }
            else {
                System.out.println("The deal did not go through, not you lucky day i guess..");
            }
            return true;
        }
        return false;
    }

    /** - for bigger
     * @param under - ArrayList of Integer wants too add
     * @return Integer - result of adding list
     */
    public static int addingList(ArrayList<Integer> under)
    {
        int before = 0;
        int main = 0;
        for (int i = 0; i < under.size(); i++)
        {
            main = before + under.get(i);
            before = main;
        }
        return main;
    }

    /**
     * Puts the advantages and disadvantages together, depending on the dice roll
     * @return
     */
    public static int advantage(Country country)
    {
        Inventory in = Inventory.getInstance();
        if(in.getInventoryItem().isEmpty())
        {
            return 0;
        }

        ArrayList<Integer>  result = new ArrayList<>();
        System.out.println("Let's impress the diplomat with your fancy items");
        in.printInventoryItem(in.getInventoryItem());
        System.out.println("Which item should you use?");

        Scanner sc = new Scanner(System.in);
        String scan = sc.nextLine();

        for(int i = 0; i < in.getInventoryItem().size(); i++) //goes through inventory
       {
           if(scan.equalsIgnoreCase("quit"))
           {
               return 0;
           }
           if(in.getInventoryItem().get(i).getName().equalsIgnoreCase(scan))
           {

               if(in.getInventoryItem().get(i).getCountryGood().getName().equalsIgnoreCase(country.getName()) )
               {
                   result.add(in.getInventoryItem().get(i).getPointsGood());
                   System.out.println(in.getInventoryItem().get(i).getTextGood());
               }
               else if(in.getInventoryItem().get(i).getCountryBad().getName().equalsIgnoreCase((country.getName())))
               {
                   result.add(in.getInventoryItem().get(i).getPointsBad());
                   System.out.println(in.getInventoryItem().get(i).getTextBad());
               }
               else if(in.getInventoryItem().get(i).getCountryGood().getName().equalsIgnoreCase(scan)
                       && in.getInventoryItem().get(i).getCountryBad().getName().equalsIgnoreCase(country.getName()))
               {
                   var combinedPoints = in.getInventoryItem().get(i).getPointsGood() + in.getInventoryItem().get(i).getPointsBad();
                   result.add(combinedPoints);
                   System.out.println(in.getInventoryItem().get(i).getTextGood());
                   System.out.println(in.getInventoryItem().get(i).getTextBad());
               }
               in.getInventoryItem().remove(in.getInventoryItem().get(i));
           }

       }
        return addingList(result);
    }
    /**
     * returns number between 0 and 7
     *
     * @return
     */
    public static int random()
    {
        return new Random().nextInt(6) + 1;
    }

    /**
     * Needs to replace the dansk part with objekt country.when player rolls dice
     * go through inventory and chek the advantages and disadvantages player gets
     * from items depending on the country.Then roll dice, if between 4 and 7 return true else return false     *
     * @return
     */
    public static boolean rollDice(Country country)
    {
        int diceResult;
        int roll = random();
        diceResult = roll + advantage(country);
        return diceResult > 3; //returns true or false
    }




    /**
     * @return energyPoints
     */
    public static int pointsEnergy() {
        Inventory in = Inventory.getInstance();
        ArrayList<Integer> energy = new ArrayList<>();
        for (int i = 0; i < in.getInventoryDeals().size(); i++) {
            for (int m = 0; m < in.getInventoryDeals().get(i).size(); m++)
            {
                energy.add(in.getInventoryDeals().get(i).get(m).getEnergyPoints());
            }
        }

        return addingList(energy)/2;
    }

    /**
     * @return sustainabilityPoints
     */
    public static int pointsSustainability() {
        Inventory in = Inventory.getInstance();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < in.getInventoryDeals().size(); i++) {
            for (int m = 0; m < in.getInventoryDeals().get(i).size(); m++)
            {
                list.add(in.getInventoryDeals().get(i).get(m).getSustainabilityPoints());
            }
        }
        return addingList(list)/2;
    }

    /**
     * @return environmentPoints
     */
    public static int pointsEnvironment() {
        Inventory in = Inventory.getInstance();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < in.getInventoryDeals().size(); i++) {
            for (int m = 0; m < in.getInventoryDeals().get(i).size(); m++)
            {
                list.add(in.getInventoryDeals().get(i).get(m).getEnvironmentPoints());
            }
        }
        return addingList(list)/2;
    }

    /**
     * ---> USED TO PRINT PONT RESULTS <---
     * Prints energy, sustainability and environment points for end result
     */
    public static void printPoints() {
        System.out.println("Through out the game, you have collected points to "
                + "to show just how environmental you are.\nLet's see how you did");
        System.out.println("Energy points: " + pointsEnergy());
        System.out.println("Sustainability points: " + pointsSustainability());
        System.out.println("Environment points: " + pointsEnvironment());

    }

    /**
     * subtract train cost from your money
     */
    public static void buyTrainTicket()
    {
        money -= TrainCost;
    }


}

 