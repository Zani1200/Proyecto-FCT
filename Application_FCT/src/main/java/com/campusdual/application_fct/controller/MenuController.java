package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.MenuConsultas;
import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Usuario;

import com.campusdual.application_fct.util.SceneHandler;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MenuController extends GenericController implements Initializable {
    @FXML
    private ListView<String> list_chats = new ListView<>();
    @FXML
    private Button boton_crear_grupo;
    @FXML
    private ListView<String> view_chat = new ListView<>();
    @FXML
    public TextField texto_enviado;
    @FXML
    private Button boton_enviar;
    public ObservableList<String> mensajeObservableList = FXCollections.observableArrayList();
    public ObservableList<String> chatObservableList = FXCollections.observableArrayList();
    private Socket cliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Usuario usuario;
    private List<Mensaje> mensajesList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MenuConsultas menuConsultas = new MenuConsultas();
        mensajesList = menuConsultas.getMensajesGrupo();
        for (int i = 0; i < mensajesList.size(); i++) {
            //mensajeObservableList.add(mensajesList.get(i).getId_usu().getUsu_nombre() + "," +
            //       mensajesList.get(i).getMensaje()+"," +
            //       mensajesList.get(i).getId_usu().getUsu_foto());
           // view_chat.setItems(mensajeObservableList);
        }
        view_chat.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new modificarCell();
            }
        });
    }
    static class modificarCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty){
            super.updateItem(item, empty);
            HBox hBox = new HBox();
            ImageView foto_perfil = new ImageView();
            Label label = new Label();

            hBox.getChildren().addAll(foto_perfil,label);
            if (item != null) {
                String[] informacionMensaje = item.split(",");
                Image image = new Image(informacionMensaje[2]);
                foto_perfil.setImage(image);
                foto_perfil.setFitWidth(30);
                foto_perfil.setFitHeight(30);
                label.setText(informacionMensaje[0]+"\n" +
                        informacionMensaje[1]);
                setGraphic(hBox);
            }
        }
    }

    public void OnCrearChatButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.REGISTRO_CHAT_SCENE);
    }

    public void onEnviarButtonClick(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(usuario.getUsu_id()+","+usuario.getUsu_nombre()+","+usuario.getUsu_contrasenha()+","+usuario.getUsu_foto()+","+usuario.getUsu_activo());
        dataOutputStream.writeUTF(texto_enviado.getText());
    }

    public void setSocket(Usuario usuario) throws IOException {
        this.usuario = usuario;
        cliente = new Socket("localhost", 6000);
        dataInputStream = new DataInputStream(cliente.getInputStream());
        dataOutputStream = new DataOutputStream(cliente.getOutputStream());
        Thread actualizadorChat = new Thread() {
            @Override
            public void run() {
                try {
                    do {
                        String mensaje = dataInputStream.readUTF();
                        Platform.runLater(() -> {
                            mensajeObservableList.add(mensaje);
                            view_chat.setItems(mensajeObservableList);
                        });
                    }while (true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        actualizadorChat.start();
    }

    public void addList_chats(Chat nuevoChat) throws IOException {
        chatObservableList.add(nuevoChat.getChat_nombre()+","+nuevoChat.getChat_foto());
        list_chats.setItems(chatObservableList);
        list_chats.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new modificacionTablaChat();
            }

            static class modificacionTablaChat extends ListCell<String> {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    HBox hBox = new HBox();
                    ImageView foto_perfil = new ImageView();
                    Label label = new Label();

                    hBox.getChildren().addAll(foto_perfil, label);
                    if (item != null) {
                        String[] informacionChat = item.split(",");
                        Image image = new Image(informacionChat[1]);
                        foto_perfil.setImage(image);
                        foto_perfil.setFitWidth(30);
                        foto_perfil.setFitHeight(30);
                        label.setText(informacionChat[0]);
                        setGraphic(hBox);
                    }
                }
            }
        });
    }
}
