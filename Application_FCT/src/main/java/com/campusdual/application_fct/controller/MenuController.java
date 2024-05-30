package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.MenuConsultas;
import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Mensaje;
import com.campusdual.application_fct.entities.Participantes;
import com.campusdual.application_fct.entities.Usuario;

import com.campusdual.application_fct.util.HibernateUtil;
import com.campusdual.application_fct.util.SceneHandler;
import com.campusdual.application_fct.util.UsuarioHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;

public class MenuController extends GenericController implements Initializable {
    @FXML
    protected ImageView foto_perfil_entrada;
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

    protected Usuario usuario;
    private final List<Chat> chatList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void addList_chats(Chat nuevoChat) {
        chatList.add(nuevoChat);
        chatObservableList.add(nuevoChat.getChat_nombre()+","+nuevoChat.getChat_foto()+","+nuevoChat.getChat_id()+","+nuevoChat.getChat_puerto());
        list_chats.setItems(chatObservableList);

        Participantes nuevoParticipante = new Participantes(new Usuario(usuario.getUsu_id(),usuario.getUsu_nombre(),usuario.getUsu_foto()),nuevoChat);
        HibernateUtil.agregarParticipantes(nuevoParticipante);
        conectarServidor(nuevoChat.getChat_puerto());
        enviarMensaje(new Usuario(usuario.getUsu_id(),usuario.getUsu_nombre(),usuario.getUsu_foto()),nuevoChat,"El usuario "+usuario.getUsu_nombre()+" se ha unido");
    }

    public void setMenuController(Usuario usuario) throws IOException {
        this.usuario = usuario;
        UsuarioHandler.usuario = usuario;
        foto_perfil_entrada.setImage(new Image(usuario.getUsu_foto()));

        MenuConsultas menuConsultas = new MenuConsultas();
        List<Chat> totalGrupos = menuConsultas.getTodosChat(usuario.getUsu_id());
        if(totalGrupos != null) {
            for(int i=0;i<totalGrupos.size();i++) {
                chatList.add(totalGrupos.get(i));
                chatObservableList.add(totalGrupos.get(i).getChat_nombre() + "," + totalGrupos.get(i).getChat_foto() + "," + totalGrupos.get(i).getChat_id() + "," + totalGrupos.get(i).getChat_puerto());
                list_chats.setItems(chatObservableList);
            }
            System.out.println(totalGrupos);
        }
        System.out.println("gg");

    }

    private void getMensajesView(Integer idChat) {
        MenuConsultas menuConsultas = new MenuConsultas();
        List<Mensaje> mensajesList = menuConsultas.getMensajesGrupo(idChat);
        mensajeObservableList.clear();
        for (int i = 0; i < mensajesList.size(); i++) {
            mensajeObservableList.add(mensajesList.get(i).getId_part().getId_usu().getUsu_nombre() + "," +
                   mensajesList.get(i).getMensaje()+"," +
                   mensajesList.get(i).getId_part().getId_usu().getUsu_foto());
        }
        view_chat.setItems(mensajeObservableList);

    }

    private void recibirMensaje(){
        Thread actualizadorChat = new Thread(() -> {
            try {
                do {
                    String mensaje = dataInputStream.readUTF();
                    Platform.runLater(() -> {
                        mensajeObservableList.add(mensaje);
                        view_chat.setItems(mensajeObservableList);
                    });
                } while (true);
            } catch (IOException e) {
                System.err.println("Error de conexion: "+e.getMessage());
            }
        });
        actualizadorChat.start();
    }

    private void conectarServidor(int port) {
        try{
            if (cliente != null && !cliente.isClosed()) {
                cliente.close();
                System.out.println("El cliente se desconecto del puerto: "+port);
            }
            cliente = new Socket("localhost",port);
            dataOutputStream = new DataOutputStream(cliente.getOutputStream());
            dataInputStream = new DataInputStream(cliente.getInputStream());

            System.out.println("CLIENTE CONECTADO AL PUERTO: "+port);

            String datosChat = list_chats.getSelectionModel().getSelectedItem();
            if(datosChat != null){
                String idChat = datosChat.split(",")[2];
                Platform.runLater(() -> getMensajesView(Integer.valueOf(idChat)));
            }
            recibirMensaje();
        } catch (IOException e) {
            System.err.println("Error al conectarse al servidor: "+e.getMessage());
        }
    }

    private void enviarMensaje(Usuario usuario, Chat chat, String mensaje) {
        try {
            dataOutputStream.writeUTF(usuario.getUsu_id()+","+usuario.getUsu_nombre()+","+usuario.getUsu_contrasenha()+","+usuario.getUsu_foto()+","+usuario.getUsu_activo()
                    +","+(chat.getChat_id())+","+chat.getChat_nombre()+","+chat.getChat_foto()+","+chat.getChat_puerto());
            System.out.println((chat.getChat_id())+","+chat.getChat_nombre()+","+chat.getChat_foto()+","+chat.getChat_puerto());
            dataOutputStream.writeUTF(mensaje);
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: "+e.getMessage());
        }
    }

    @FXML
    private void OnCrearChatButtonClick() {
        sceneHandler.changeToScene(SceneHandler.REGISTRO_CHAT_SCENE);
    }

    @FXML
    private void onEnviarButtonClick() throws IOException {
        String chatActual = list_chats.getSelectionModel().getSelectedItem();
        enviarMensaje(new Usuario(usuario.getUsu_id(),usuario.getUsu_nombre(),usuario.getUsu_contrasenha(),usuario.getUsu_foto(),usuario.getUsu_activo()),
                new Chat(Integer.parseInt(chatActual.split(",")[2]),chatActual.split(",")[0],chatActual.split(",")[1],Integer.parseInt(chatActual.split(",")[3])),texto_enviado.getText());
    }

    @FXML
    private void unirseChatOnClick() {
        sceneHandler.changeToScene(SceneHandler.UNIRSE_CHAT_SCENE);
    }

    @FXML
    private void OnClickInfoChat() {
        String chatActual = list_chats.getSelectionModel().getSelectedItem();
        Integer chatActualId = list_chats.getSelectionModel().getSelectedIndex();
        MenuConsultas menuConsultas = new MenuConsultas();
        List<String> usuarios = menuConsultas.getTodosParticipantes(Integer.valueOf(chatActual.split(",")[2]));
        sceneHandler.changeToScene(SceneHandler.INFORMACION_CHAT_SCENE);
        ((InformacionChatController) prevInfoChat ).setInformacionMenu(chatActual.split(",")[0],chatActual.split(",")[1],usuarios,chatActualId);
    }

    @FXML
    private void OnClickChatList() {
        String chatActual = list_chats.getSelectionModel().getSelectedItem();
        int chatActualId = list_chats.getSelectionModel().getSelectedIndex();
        conectarServidor(chatList.get(chatActualId).getChat_puerto());
        System.out.println(chatList);
        if(!(chatActual==null)) {
            foto_chat.setImage(new Image(chatActual.split(",")[1]));
            nombre_chat_label.setText(chatActual.split(",")[0]);
        }
    }

    @FXML
    private void OnClickFotoPerfil() {
        sceneHandler.changeToScene(SceneHandler.INFORMACION_PERFIL_SCENE);
        ((InformacionPerfilController) prevInfoPerfil).setInformacionPerfil(usuario);
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
