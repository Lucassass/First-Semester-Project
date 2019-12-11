package com.SemesterProject.Interfaces.Entities;

import com.SemesterProject.DomainLogic.Enum.Countries;
import javafx.scene.image.Image;

import java.util.UUID;

public interface IItem
{
    Image getImage();
    int getIndex();
    UUID getUuid();
    String getTextBad();
    String getTextGood();
    Countries getCountryGood();
    Countries getCountryBad();
    int getPointsGood();
    int getPointsBad();
    String getName();
    void setIndex(int i);
}
