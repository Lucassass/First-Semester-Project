package com.SemesterProject.presentationLayer;

import com.SemesterProject.presentationLayer.Exceptions.ControllerNotInjected;


public abstract class Injection<T>
{
    private T controller;

    /**
     * Injects the controller
     * @param controller the controller type to inject
     */
    public void injectController(T controller)
    {

        this.controller = controller;
        postInjection();
    }


    /**
     * Return the controller, if controller wasn't injected it throws an exception
     * @return a controller of your type
     */
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

    /**
     * Method gets called after controller injection
     */
    public void postInjection(){


    }

}


