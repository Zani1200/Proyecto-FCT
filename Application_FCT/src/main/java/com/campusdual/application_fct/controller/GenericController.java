package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.util.SceneHandler;

public class GenericController {
    protected SceneHandler sceneHandler;
    public GenericController prevMenu;

    public void setPrevMenu(GenericController prevMenu){
        this.prevMenu =  prevMenu;
    }
    public void setSceneHandler(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
    }
}
