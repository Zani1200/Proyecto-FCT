package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.MenuConsultas;
import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Participantes;
import com.campusdual.application_fct.entities.Usuario;

import com.campusdual.application_fct.util.HibernateUtil;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import javax.persistence.PersistenceException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class MenuController extends GenericController {
    @FXML
    private ImageView foto_chat;
    @FXML
    private Label nombre_chat_label;
    @FXML
    protected ListView<String> list_chats = new ListView<>();
    @FXML
    private ListView<String> view_chat = new ListView<>();
    @FXML
    public TextField texto_enviado;
    public ObservableList<String> mensajeObservableList = FXCollections.observableArrayList();
    public ObservableList<String> chatObservableList = FXCollections.observableArrayList();
    private Socket cliente;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Usuario usuario;
    private static List<Mensaje> mensajesList;
    protected List<Chat> chatList = new ArrayList<>();
    private String datosChat;
    private Timer timer = new Timer();
    public TimerTask actualizarChat = new TimerTask() {
        String chatAnterior;
        @Override
        public void run() {
            datosChat = list_chats.getSelectionModel().getSelectedItem();
            if(datosChat != null){
                String idChat = datosChat.split(",")[2];
                Platform.runLater(() -> {
                    getMensajesView(Integer.valueOf(idChat));
                });
            }
        }
    };
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
        timer.schedule(actualizarChat,0,3000);
    }

    public void getMensajesView(Integer idChat) {
        MenuConsultas menuConsultas = new MenuConsultas();
        mensajesList = menuConsultas.getMensajesGrupo(idChat);
        mensajeObservableList.clear();
        for (int i = 0; i < mensajesList.size(); i++) {
            mensajeObservableList.add(mensajesList.get(i).getId_part().getId_usu().getUsu_nombre() + "," +
                   mensajesList.get(i).getMensaje()+"," +
                   mensajesList.get(i).getId_part().getId_usu().getUsu_foto());
        }
        view_chat.setItems(mensajeObservableList);
        view_chat.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new modificarCell();
            }

            static class modificarCell extends ListCell<String> {

                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if (item != null || !empty) {
                        String[] informacionMensaje = item.split(",");
                        System.out.println(item);
                        HBox hBox = new HBox();
                        ImageView foto_perfil = new ImageView();
                        Label label = new Label();
                        hBox.getChildren().addAll(foto_perfil,label);
                        Image image = new Image(informacionMensaje[2]);
                        foto_perfil.setImage(image);
                        foto_perfil.setFitWidth(30);
                        foto_perfil.setFitHeight(30);
                        label.setText(informacionMensaje[0]+"\n" +
                                informacionMensaje[1]);
                        setGraphic(hBox);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
    }

    public void addList_chats(Chat nuevoChat) throws IOException {
        chatList.add(new Chat(nuevoChat.getChat_id(),nuevoChat.getChat_nombre(),nuevoChat.getChat_foto()));
        chatObservableList.add(nuevoChat.getChat_nombre()+","+nuevoChat.getChat_foto()+","+nuevoChat.getChat_id());
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
                    if (item != null || !empty) {
                        HBox hBox = new HBox();
                        ImageView foto_perfil = new ImageView();
                        Label label = new Label();
                        String[] informacionChat = item.split(",");
                        hBox.getChildren().addAll(foto_perfil, label);
                        Image image = new Image(informacionChat[1]);
                        foto_perfil.setImage(image);
                        foto_perfil.setFitWidth(30);
                        foto_perfil.setFitHeight(30);
                        label.setText(informacionChat[0]);
                        setGraphic(hBox);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });

        Participantes nuevoParticipante = new Participantes(new Usuario(usuario.getUsu_id(),usuario.getUsu_nombre(),usuario.getUsu_foto()),nuevoChat);
        HibernateUtil.agregarParticipantes(nuevoParticipante);
        HibernateUtil.agregarMensaje(new Mensaje(nuevoParticipante,"El usuario "+usuario.getUsu_nombre()+" se ha unido"));
    }

    public void OnCrearChatButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.REGISTRO_CHAT_SCENE);
    }

    public void onEnviarButtonClick(ActionEvent actionEvent) throws IOException {
        String chatActual = list_chats.getSelectionModel().getSelectedItem();
        dataOutputStream.writeUTF(usuario.getUsu_id()+","+usuario.getUsu_nombre()+","+usuario.getUsu_contrasenha()+","+usuario.getUsu_foto()+","+usuario.getUsu_activo()
        +","+(chatActual.split(",")[2])+","+chatActual.split(",")[0]+","+chatActual.split(",")[1]);
        dataOutputStream.writeUTF(texto_enviado.getText());
    }
    @FXML
    private void unirseChatOnClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.UNIRSE_CHAT_SCENE);
    }
    @FXML
    private void OnClickInfoChat(MouseEvent mouseEvent) {
        String chatActual = list_chats.getSelectionModel().getSelectedItem();
        Integer chatActualId = list_chats.getSelectionModel().getSelectedIndex();
        MenuConsultas menuConsultas = new MenuConsultas();
        List<String> usuarios = menuConsultas.getTodosParticipantes(Integer.valueOf(chatActual.split(",")[2]));
        sceneHandler.changeToScene(SceneHandler.INFORMACION_CHAT_SCENE);
        ((InformacionChatController) prevMenu ).setInformacionMenu(chatActual.split(",")[0],chatActual.split(",")[1],usuarios,chatActualId);
    }
    @FXML
    private void OnClickChatList(MouseEvent mouseEvent) {
        String chatActual = list_chats.getSelectionModel().getSelectedItem();
        if(!(chatActual==null)) {
            foto_chat.setImage(new Image(chatActual.split(",")[1]));
            nombre_chat_label.setText(chatActual.split(",")[0]);
        }
    }

    protected void eliminarChat(int chatId){
        chatList.remove(chatId);
        chatObservableList.clear();
        for(int i = 0;i<chatList.size();i++){
            chatObservableList.add(chatList.get(i).getChat_nombre()+"," +
                    chatList.get(i).getChat_foto()+","+
                    chatList.get(i).getChat_id());
            list_chats.setItems(chatObservableList);
        }
        System.out.println(chatId);
    }
}
