package com.SemesterProject.presentationLayer;

import com.SemesterProject.presentationLayer.Exceptions.ControllerNotInjected;


public abstract class Injection<T> implements IInjection<T>
{
    private T controller;

    public void injectController(T controller)
    {

        this.controller = controller;
        afterInjected();
    }


    public T getController()
    {
        if (controller == null) {
            try
            {
                throw new ControllerNotInjected();
            }
            catch (ControllerNotInjected controllerNotInjected)
            {
                controllerNotInjected.printStackTrace();
            }
        }
        return controller;
    }

    public void afterInjected(){


    }

}


