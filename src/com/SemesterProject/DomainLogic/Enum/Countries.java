package com.SemesterProject.DomainLogic.Enum;

public enum Countries
{
    USA("USA"),
    Russia("Russia"),
    China("China"),
    India("India"),
    Japan("Japan"),
    Germany("Germany");

    private String name;

    Countries(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
