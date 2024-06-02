package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.util.HibernateUtil;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UnirseChatController extends GenericController{

    @FXML
    private TextField txt_idChat;
    @FXML
    private void unirseChatOnClick(ActionEvent actionEvent) throws IOException {
        Chat unirseChat = HibernateUtil.buscarChat(Integer.parseInt(txt_idChat.getText()));
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
        ((MenuController) prevMenu).addList_chats(unirseChat);
    }
    @FXML
    private void onSalirButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
    }
}
