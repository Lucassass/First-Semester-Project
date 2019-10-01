package com.SemesterProject.ASCII.Color;

public enum  Attribute
{
    Normal("0"),
    Bold("1"),
    Underline("4");

    private final String opcode;

    Attribute(String opcode) {
        this.opcode = opcode;
    }

    public String getOpcode() {
        return opcode;
    }
}
