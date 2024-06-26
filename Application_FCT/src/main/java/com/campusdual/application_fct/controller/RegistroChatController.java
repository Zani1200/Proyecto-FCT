package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.RegistroChatControllerConsultas;
import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.util.HibernateUtil;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroChatController extends GenericController implements Initializable {
    @FXML
    private TextField txtField_nombre;
    @FXML
    private ImageView foto_perfil;
    private List<Image> imageList = new ArrayList<>();
    private Integer numeroImagen = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoEstandar.png"));
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilHombre.png"));
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilMujer.png"));
        foto_perfil.setImage(imageList.get(numeroImagen));
    }
    @FXML
    private void buttonDerechaOnClick() {
        numeroImagen++;
        if (numeroImagen > 2){
            numeroImagen = 0;
        }
        foto_perfil.setImage(imageList.get(numeroImagen));
    }
    @FXML
    private void buttonIzquierdaOnClick() {
        numeroImagen--;
        if (numeroImagen < 0){
            numeroImagen = 2;
        }
        foto_perfil.setImage(imageList.get(numeroImagen));
    }
    @FXML
    private void onAceptarButtonClick() {
        RegistroChatControllerConsultas registroChatControllerConsultas = new RegistroChatControllerConsultas();
        Integer puerto = (Integer) registroChatControllerConsultas.setPuerto();
        System.out.println(puerto);
        Chat nuevoChat = HibernateUtil.agregarChat(new Chat(txtField_nombre.getText(),foto_perfil.getImage().getUrl(),puerto));
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
        ((MenuController) prevMenu).addList_chats(nuevoChat);
    }
    @FXML
    private void onSalirButtonClick() {
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
    }
}
