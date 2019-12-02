package com.SemesterProject.presentationLayer;

import com.SemesterProject.presentationLayer.Controllers.MainController;

public interface IInjection<T>{

     void injectController(T controller);
     T getController();

}
