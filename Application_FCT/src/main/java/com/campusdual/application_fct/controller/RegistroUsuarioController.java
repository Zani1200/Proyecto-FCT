package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroUsuarioController extends GenericController implements Initializable {
    @FXML
    private Button boton_izq;
    @FXML
    private Button boton_der;
    @FXML
    private ImageView foto_perfil;
    private List<Image> imageList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\SmallLogo.scale-400.png"));
        foto_perfil.setImage(imageList.get(0));
    }

    public void onButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.LOGIN_SCENE);
    }

    public void cambioFoto(){

    }
}
