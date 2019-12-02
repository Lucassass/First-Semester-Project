package com.SemesterProject.DomainLogic.Enum;

public enum CountryList
{
    USA("USA"),
    Russia("Russia"),
    China("China"),
    India("India"),
    Japan("Japan"),
    Germany("Germany");

    private String name;

    CountryList(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
