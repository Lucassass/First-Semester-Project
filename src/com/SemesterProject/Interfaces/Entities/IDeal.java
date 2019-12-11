package com.SemesterProject.Interfaces.Entities;

import com.SemesterProject.DomainLogic.Enum.DealCategory;
import javafx.scene.image.Image;

import java.util.UUID;

public interface IDeal
{
    int getPrice();
    UUID getUuid();
    String getDescription();
    String getName();
    DealCategory getCategory();
    int getSustainabilityPoints();
    int getEnergyPoints();
    int getEnvironmentPoints();
    Image getImage();
}
