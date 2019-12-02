package com.SemesterProject.presentationLayer;

public abstract class Injection<T> implements IInjection<T>
{
    private T controller;

    public void injectController(T controller)
    {
        this.controller = controller;
        afterInjected();
    }

    public T getController() {
        return controller;
    }

    public void afterInjected(){

    }

}


