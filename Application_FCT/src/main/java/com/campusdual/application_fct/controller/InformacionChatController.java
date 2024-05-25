package com.campusdual.application_fct.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InformacionChatController extends GenericController{
    @FXML
    private ImageView foto_grupo;
    @FXML
    private Label nombre_grupo;
    @FXML
    private ListView participantes_grupo;
    @FXML
    private void OnSalirButtonClick(ActionEvent actionEvent) {
    }
    @FXML
    private void OnVolverButtonClick(ActionEvent actionEvent) {
    }
    public void setInformacionMenu(String nombre, String foto){
        nombre_grupo.setText(nombre);
        foto_grupo.setImage(new Image(foto));
    }
}
