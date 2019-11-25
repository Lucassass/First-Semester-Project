package com.SemesterProject.presentationLayer;

import com.SemesterProject.DomainLogic.Room;
import com.SemesterProject.presentationLayer.Controllers.MainController;

public abstract class Injection implements IInjection
{
    private MainController mainController;

    public void  injectMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public MainController getMainController() {
        return mainController;
    }

}


