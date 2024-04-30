package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.LoginConsultas;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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


    public void onAceptarButtonClick(ActionEvent actionEvent) {
        LoginConsultas loginConsultas = new LoginConsultas();
        System.out.println(texto_nombre.getText());
        if(loginConsultas.validarUsuario(texto_nombre.getText(), texto_contrasenha.getText()) == null) {
            label_informacion_menu.setText("El usuario/contraseña no son correctos");
        }
    }

    public void onRegistrarButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.REGISTRO_USUARIO_SCENE);
    }
}