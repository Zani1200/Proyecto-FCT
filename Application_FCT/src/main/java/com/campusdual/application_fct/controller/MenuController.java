package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.entities.Usuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MenuController extends GenericController{
    @FXML
    private Button boton_crear_grupo;
    @FXML
    private ListView view_chat;
    @FXML
    public TextField texto_enviado;
    @FXML
    private Button boton_enviar;
    private Socket cliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Usuario usuario;

    public void onEnviarButtonClick(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(usuario.getUsu_id()+","+usuario.getUsu_nombre()+","+usuario.getUsu_foto()+","+usuario.getUsu_activo());
        dataOutputStream.writeUTF("jkingvdresuihwedfiuyhbodsv");
    }
    public void setSocket(Usuario usuario) throws IOException {
        this.usuario = usuario;
        cliente = new Socket("localhost", 6000);
        dataInputStream = new DataInputStream(cliente.getInputStream());
        dataOutputStream = new DataOutputStream(cliente.getOutputStream());
    }
}
