package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;

public class RegistroUsuarioController extends GenericController{

    public void onButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.LOGIN_SCENE);
    }

    public void onRegistrarButtonClick(ActionEvent actionEvent) {
    }

    public void onAceptarButtonClick(ActionEvent actionEvent) {
    }
}
