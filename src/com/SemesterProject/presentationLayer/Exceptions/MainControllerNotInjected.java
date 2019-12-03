package com.SemesterProject.presentationLayer.Exceptions;

public class MainControllerNotInjected extends Exception
{
    @Override
    public String getMessage() {
        return "Main controller wasn't injected";
    }
}
