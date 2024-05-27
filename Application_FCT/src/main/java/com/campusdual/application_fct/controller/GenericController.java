package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.util.SceneHandler;

public class GenericController {
    protected SceneHandler sceneHandler;
    public GenericController prevMenu;
    public GenericController prevInfoChat;
    public GenericController prevInfoPerfil;

    public void setPrevMenu(GenericController prevMenu){
        this.prevMenu =  prevMenu;
    }
    public void setPrevInfoChat(GenericController prevInfoChat) {
        this.prevInfoChat = prevInfoChat;
    }
    public void setPrevInfoPerfil(GenericController prevInfoPerfil) {
        this.prevInfoPerfil = prevInfoPerfil;
    }

    public void setSceneHandler(SceneHandler sceneHandler) {
        this.sceneHandler = sceneHandler;
    }
}
