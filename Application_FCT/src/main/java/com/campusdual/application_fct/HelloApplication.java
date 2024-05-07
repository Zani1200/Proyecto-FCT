package com.campusdual.application_fct;

import com.campusdual.application_fct.controller.GenericController;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.HibernateUtil;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.HashMap;


public class HelloApplication extends Application {
    public static String hola = "hola";
    @Override
    public void start(Stage stage) throws Exception {
        Session session = HibernateUtil.getSessionfactory().openSession();

        FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        FXMLLoader registroUsuarioLoader = new FXMLLoader(HelloApplication.class.getResource("registroUsuario.fxml"));
        FXMLLoader menuLoader = new FXMLLoader(HelloApplication.class.getResource("menu.fxml"));

        Parent loginPane = loginLoader.load();
        Parent registroUsuariPane = registroUsuarioLoader.load();
        Parent menuPane = menuLoader.load();

        Scene loginScene = new Scene(loginPane, 600,400);
        Scene registroUsuarioScene = new Scene(registroUsuariPane, 600,400);
        Scene menuScene = new Scene(menuPane, 600,400);

        SceneHandler sceneHandler = new SceneHandler(stage);
        sceneHandler.addScene(SceneHandler.LOGIN_SCENE,loginScene);
        sceneHandler.addScene(SceneHandler.REGISTRO_USUARIO_SCENE,registroUsuarioScene);
        sceneHandler.addScene(SceneHandler.MENU_SCENE,menuScene);

        HashMap<String, GenericController> controllerHashMap = new HashMap<>();
        GenericController loginController = loginLoader.getController();
        controllerHashMap.put(SceneHandler.LOGIN_SCENE,loginController);
        GenericController registroUsuarioController = registroUsuarioLoader.getController();
        controllerHashMap.put(SceneHandler.REGISTRO_USUARIO_SCENE, registroUsuarioController);
        GenericController menuController = menuLoader.getController();
        loginController.setPrevMenu(menuController);
        controllerHashMap.put(SceneHandler.MENU_SCENE, menuController);
        controllerHashMap.values().forEach(genericController -> genericController.setSceneHandler(sceneHandler));


        stage.setTitle("Hello!");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}