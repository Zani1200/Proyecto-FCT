package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.LoginConsultas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController extends GenericController{
    @FXML
    private Button boton_registrarse;
    @FXML
    private Button boton_aceptar;
    @FXML
    private TextField texto_nombre;
    @FXML
    private TextField texto_contrasenha;


    public void onButtonClick(ActionEvent actionEvent) {
        LoginConsultas loginConsultas = new LoginConsultas();
        System.out.println(texto_nombre.getText());
        loginConsultas.validarUsuario(texto_nombre.getText(),texto_contrasenha.getText());
    }
}