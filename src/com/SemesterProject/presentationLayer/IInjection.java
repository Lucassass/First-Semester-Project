package com.SemesterProject.presentationLayer;

import com.SemesterProject.presentationLayer.Controllers.MainController;

public interface IInjection{

     void injectMainController(MainController mainController);
     MainController getMainController();

}
