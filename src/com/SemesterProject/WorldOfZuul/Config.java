package com.SemesterProject.WorldOfZuul;

public class Config
{
    private static int money = 500;
    private static int CommercialFlyingCost = 100;
    private static int PrivateFlyingCost = 250;
    private static int TrainCost = 50;


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
    public static void buyFlyPrivate()
    {
        money -= PrivateFlyingCost;
    }

    /**
     * subtract train cost from your money
     */
    public static void buyTrainTicket()
    {
        money -= TrainCost;
    }




}
