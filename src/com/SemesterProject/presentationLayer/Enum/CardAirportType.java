package com.SemesterProject.presentationLayer.Enum;

public enum CardAirportType
{
    PRIVATE("private"),
    COMMERCIAL("commercial"),
    USA("usa"),
    GERMANY("germany"),
    INDIA("india"),
    JAPAN("japan"),
    CHINA("china"),
    RUSSIA("russia");
    private String name;

    CardAirportType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
