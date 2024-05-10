package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.MenuConsultas;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MenuController extends GenericController{
    @FXML
    private Button boton_crear_grupo;
    @FXML
    private ListView<String> view_chat = new ListView<>();
    @FXML
    public TextField texto_enviado;
    @FXML
    private Button boton_enviar;
    public ObservableList<String> almacenRuta = FXCollections.observableArrayList();
    private Socket cliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Usuario usuario;
    private List<Mensaje> mensajeList;
    private Timer timer = new Timer();
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            MenuConsultas menuConsultas = new MenuConsultas();
            mensajeList = menuConsultas.getMensajesGrupo();
            for (int i = 0; i < mensajeList.size(); i++) {
                almacenRuta.add(mensajeList.get(i).getId_usu().getUsu_nombre()+" "+mensajeList.get(i).getMensaje());
            }
            view_chat.setItems(almacenRuta);
        }
    };

    public void onEnviarButtonClick(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(usuario.getUsu_id()+","+usuario.getUsu_nombre()+","+usuario.getUsu_contrasenha()+","+usuario.getUsu_foto()+","+usuario.getUsu_activo());
        dataOutputStream.writeUTF(texto_enviado.getText());


    }
    public void setSocket(Usuario usuario) throws IOException {
        this.usuario = usuario;
        cliente = new Socket("localhost", 6000);
        dataInputStream = new DataInputStream(cliente.getInputStream());
        dataOutputStream = new DataOutputStream(cliente.getOutputStream());
        timer.schedule(timerTask,0,10000);
    }


}
