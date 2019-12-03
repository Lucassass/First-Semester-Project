package com.SemesterProject.presentationLayer.Exceptions;

public class MainControllerNotInjected extends Exception
{
    @Override
    public String getMessage() {
        return "controller wasn't injected";
    }
}
