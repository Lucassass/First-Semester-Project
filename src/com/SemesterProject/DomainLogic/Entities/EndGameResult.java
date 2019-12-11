package com.SemesterProject.DomainLogic.Entities;

import com.SemesterProject.Interfaces.Entities.IEndGameResult;

public class EndGameResult implements IEndGameResult
{
    private int environmentPoint;
    private int sustainabilityPoint;
    private int energyPoint;

    public EndGameResult(int environmentPoint, int sustainabilityPoint, int energyPoint) {
        this.environmentPoint = environmentPoint;
        this.sustainabilityPoint = sustainabilityPoint;
        this.energyPoint = energyPoint;
    }

    /**
     *
     * @return energyPoint
     */
    public int getEnergyPoint() {
        return energyPoint;
    }

    /**
     *
     * @return environment points
     */
    public int getEnvironmentPoint() {
        return environmentPoint;
    }

    /**
     *
     * @return sustainability point
     */
    public int getSustainabilityPoint() {
        return sustainabilityPoint;
    }

    /**
     *
     * @return sum of all points
     */
    public int getSum()
    {
        return sustainabilityPoint + energyPoint + environmentPoint;
    }
}
