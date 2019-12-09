package com.SemesterProject.DomainLogic.Entities;

public class EndGameResult
{
    private int environmentPoint;
    private int sustainabilityPoint;
    private int energyPoint;

    public EndGameResult(int environmentPoint, int sustainabilityPoint, int energyPoint) {
        this.environmentPoint = environmentPoint;
        this.sustainabilityPoint = sustainabilityPoint;
        this.energyPoint = energyPoint;
    }

    public int getEnergyPoint() {
        return energyPoint;
    }

    public int getEnvironmentPoint() {
        return environmentPoint;
    }

    public int getSustainabilityPoint() {
        return sustainabilityPoint;
    }

    public int getSum()
    {
        return sustainabilityPoint + energyPoint + environmentPoint;
    }
}
