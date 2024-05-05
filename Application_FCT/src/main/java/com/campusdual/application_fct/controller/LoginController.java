package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.LoginConsultas;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.servicio.Servidor;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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



    public void onAceptarButtonClick(ActionEvent actionEvent) throws IOException {
        LoginConsultas loginConsultas = new LoginConsultas();
        Usuario usuario = loginConsultas.validarUsuario(texto_nombre.getText(), texto_contrasenha.getText());
        if(loginConsultas.validarUsuario(texto_nombre.getText(), texto_contrasenha.getText()) == null) {
            label_informacion_menu.setText("El usuario/contraseña no son correctos");
        }
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
        ((MenuController) prevMenu).setSocket();
    }

    public void onRegistrarButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.REGISTRO_USUARIO_SCENE);
    }
}