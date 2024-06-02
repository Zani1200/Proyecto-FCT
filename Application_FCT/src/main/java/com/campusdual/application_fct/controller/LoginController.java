package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.LoginConsultas;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.NoExisteUsuario;
import com.campusdual.application_fct.excepciones.UsuarioActivo;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.util.Base64;


public class LoginController extends GenericController{
    @FXML
    private Label label_informacion_menu;
    @FXML
    private TextField texto_nombre;
    @FXML
    private TextField texto_contrasenha;

    @FXML
    private void onAceptarButtonClick(){
        try {
            LoginConsultas loginConsultas = new LoginConsultas();
            byte[] encodeContrasenha = Base64.getEncoder().encode(texto_contrasenha.getText().getBytes());
            String contrasenhaEncriptada = new String(encodeContrasenha);
            System.out.println(contrasenhaEncriptada);
            Object usuario = loginConsultas.validarUsuario(texto_nombre.getText(), contrasenhaEncriptada);
            sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
            ((MenuController) prevMenu).setMenuController((Usuario) usuario);
        } catch (UsuarioActivo | NoExisteUsuario e){
            label_informacion_menu.setText(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onRegistrarButtonClick() {
        sceneHandler.changeToScene(SceneHandler.REGISTRO_USUARIO_SCENE);
    }
}