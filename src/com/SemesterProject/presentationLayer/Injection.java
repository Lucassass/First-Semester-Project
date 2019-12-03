package com.SemesterProject.presentationLayer;

import com.SemesterProject.DomainLogic.Room;
import com.SemesterProject.presentationLayer.Controllers.MainController;
import com.SemesterProject.presentationLayer.Exceptions.MainControllerNotInjected;

public abstract class Injection implements IInjection
{
    private MainController mainController;

    public void  injectMainController(MainController mainController)
    {

        this.mainController = mainController;
    }

    public MainController getMainController()
    {
        if (mainController == null)
        {
            try {
                throw new MainControllerNotInjected();
            } catch (MainControllerNotInjected mainControllerNotInjected) {
                mainControllerNotInjected.printStackTrace();
            }
        }
        return mainController;
    }

}


