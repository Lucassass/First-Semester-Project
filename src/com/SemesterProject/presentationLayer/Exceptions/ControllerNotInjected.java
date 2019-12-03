package com.SemesterProject.presentationLayer.Exceptions;

public class ControllerNotInjected extends Exception
{
    @Override
    public String getMessage() {
        return "controller wasn't injected";
    }
}
