package com.SemesterProject.presentationLayer;

import com.SemesterProject.presentationLayer.Exceptions.ControllerNotInjected;


public abstract class Injection<T>
{
    private T controller;

    public void injectController(T controller)
    {

        this.controller = controller;
        postInjection();
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

    public void postInjection(){


    }

}


