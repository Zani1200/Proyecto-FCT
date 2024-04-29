package com.campusdual.application_fct;

import com.campusdual.application_fct.controller.GenericController;
import com.campusdual.application_fct.util.HibernateUtil;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.HashMap;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Session session = HibernateUtil.getSessionfactory().openSession();
        FXMLLoader loginLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        Parent loginPane = loginLoader.load();

        Scene loginScene = new Scene(loginPane, 600,400);

        SceneHandler sceneHandler = new SceneHandler(stage);
        sceneHandler.addScene(SceneHandler.LOGIN_SCENE,loginScene);

        HashMap<String, GenericController> controllerHashMap = new HashMap<>();
        GenericController loginController = loginLoader.getController();
        controllerHashMap.put(SceneHandler.LOGIN_SCENE,loginController);
        controllerHashMap.values().forEach(genericController -> genericController.setSceneHandler(sceneHandler));

        stage.setTitle("Hello!");
        stage.setScene(loginScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}