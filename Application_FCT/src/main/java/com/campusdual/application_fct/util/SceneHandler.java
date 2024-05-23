package com.campusdual.application_fct.util;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class SceneHandler {
    public static final String LOGIN_SCENE = "loginScene";
    public static final String REGISTRO_USUARIO_SCENE = "registroUsuarioScene";
    public static final String MENU_SCENE = "menuScene";
    public static final String REGISTRO_CHAT_SCENE = "registroChatScene";
    public static final String UNIRSE_CHAT_SCENE = "unirseChatScene";
    private HashMap<String, Scene> sceneHashMap;
    private Stage stage;

    public SceneHandler(Stage stage) {
        this.sceneHashMap = new HashMap<>();
        this.stage = stage;
    }

    public void addScene (String sceneName, Scene scene){
        this.sceneHashMap.put(sceneName, scene);
    }

    public void changeToScene(String sceneName){
        this.stage.setScene(this.sceneHashMap.get(sceneName));
    }
}
