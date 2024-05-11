package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.LoginConsultas;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.NoExisteUsuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;
import com.campusdual.application_fct.servicio.Servidor;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.persistence.NoResultException;
import java.io.IOException;


public class LoginController extends GenericController{
    @FXML
    private Label label_informacion_menu;
    @FXML
    private Button boton_registrarse;
    @FXML
    private Button boton_aceptar;
    @FXML
    private TextField texto_nombre;
    @FXML
    private TextField texto_contrasenha;



    public void onAceptarButtonClick(ActionEvent actionEvent){
        try {
            LoginConsultas loginConsultas = new LoginConsultas();
            Object usuario = loginConsultas.validarUsuario(texto_nombre.getText(), texto_contrasenha.getText());
        } catch (UsuarioActivo | NoExisteUsuario e){
            System.out.println(e);
            label_informacion_menu.setText(e.getMessage());
        }
        /*
        if(usuario == null) {
            label_informacion_menu.setText("El usuario/contraseña no son correctos");
        }
        else {
            sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
            ((MenuController) prevMenu).setSocket((Usuario) usuario);
        }

         */
    }

    public void onRegistrarButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.REGISTRO_USUARIO_SCENE);
    }
}