package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.entities.Usuario;
import com.campusdual.application_fct.excepciones.ExisteUsuario;
import com.campusdual.application_fct.excepciones.NoExisteUsuario;
import com.campusdual.application_fct.excepciones.UsuarioNoValido;
import com.campusdual.application_fct.util.HibernateUtil;
import com.campusdual.application_fct.util.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroUsuarioController extends GenericController implements Initializable {
    @FXML
    private Label informacion_registro;
    @FXML
    private TextField txtField_nombre;
    @FXML
    private PasswordField password_registro;
    @FXML
    private ImageView foto_perfil;
    private List<Image> imageList = new ArrayList<>();
    private Integer numeroImagen = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoEstandar.png"));
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilHombre.png"));
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilMujer.png"));
        foto_perfil.setImage(imageList.get(numeroImagen));
    }

    public void ButtonDerechaOnClick(ActionEvent actionEvent) {
        numeroImagen++;
        if (numeroImagen > 2){
            numeroImagen = 0;
        }
        foto_perfil.setImage(imageList.get(numeroImagen));
    }


    public void ButtonIzquierdaOnClick(ActionEvent actionEvent) {
        numeroImagen--;
        if (numeroImagen < 0){
            numeroImagen = 2;
        }
        foto_perfil.setImage(imageList.get(numeroImagen));
    }

    public void OnAceptarButtonClick(ActionEvent actionEvent){
        try {
            HibernateUtil.agregarUsuarios(new Usuario(txtField_nombre.getText(), password_registro.getText(), "", 0));
            sceneHandler.changeToScene(SceneHandler.LOGIN_SCENE);
        } catch (ExisteUsuario | UsuarioNoValido e){
            informacion_registro.setText(e.getMessage());
        }
    }

    public void OnSalirButtonClick(ActionEvent actionEvent) {
        sceneHandler.changeToScene(SceneHandler.LOGIN_SCENE);
    }
}
