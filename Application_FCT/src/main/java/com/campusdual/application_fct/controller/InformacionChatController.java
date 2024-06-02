package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.entities.Chat;
import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InformacionChatController extends GenericController{
    @FXML
    private ImageView foto_grupo;
    @FXML
    private Label nombre_grupo;
    @FXML
    private ListView<String> participantes_grupo = new ListView<>();
    private Integer chatId;
    private ObservableList<String> participantesObservableList = FXCollections.observableArrayList();
    @FXML
    private void onSalirButtonClick() {
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
        ((MenuController)prevMenu).eliminarChat(chatId);
    }
    @FXML
    private void onVolverButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
    }
    public void setInformacionMenu(String nombre, String foto, List<String> usuarios,Integer chatId){
        this.chatId = chatId;
        nombre_grupo.setText(nombre);
        foto_grupo.setImage(new Image(foto));
        participantesObservableList.clear();
        participantesObservableList.addAll(usuarios);
        participantes_grupo.setItems(participantesObservableList);
        participantes_grupo.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ModificarCell();
            }

            static class ModificarCell extends ListCell<String> {
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    HBox hBox = new HBox();
                    ImageView foto_perfil = new ImageView();
                    Label label = new Label();

                    hBox.getChildren().addAll(foto_perfil,label);
                    if (item != null || !empty) {
                        String[] informacionParticipantes = item.split(",");
                        Image image = new Image(informacionParticipantes[1]);
                        foto_perfil.setImage(image);
                        foto_perfil.setFitWidth(30);
                        foto_perfil.setFitHeight(30);
                        label.setText(informacionParticipantes[0]+"\n" +
                                informacionParticipantes[2]);
                        setGraphic(hBox);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
    }
}
