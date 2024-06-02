package com.campusdual.application_fct.controller;

import com.campusdual.application_fct.consultas.InformacionPerfilConsultas;
import com.campusdual.application_fct.entities.Usuario;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class InformacionPerfilController extends GenericController implements Initializable {
    @FXML
    private Button btn_guardar_foto;
    @FXML
    private ImageView foto_perfil;
    @FXML
    private PasswordField txt_contrasenha_repetida;
    @FXML
    private TextField txt_cambio_nombre;
    @FXML
    private Label label_cambio_nombre;
    @FXML
    private PasswordField txt_cambio_contrasenha;
    @FXML
    private Label label_cambio_contrasenha;
    private List<Image> imageList = new ArrayList<>();
    private Usuario usuario;
    private final InformacionPerfilConsultas informacionPerfilConsultas = new InformacionPerfilConsultas();
    private Integer numeroImagen = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoEstandar.png"));
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilHombre.png"));
        imageList.add(new Image("C:\\Proyecto-FCT\\Application_FCT\\src\\main\\resources\\com\\campusdual\\application_fct\\assets\\fotoPerfilMujer.png"));
        txt_cambio_contrasenha.setText("");
        txt_contrasenha_repetida.setText("");
        txt_cambio_nombre.setText("");
        label_cambio_nombre.setText("");
        label_cambio_contrasenha.setText("");
    }

    public void setInformacionPerfil(Usuario usuario) {
        this.usuario = usuario;
        for(int i=0;i<imageList.size();i++){
            System.out.println(imageList.get(i).getUrl()+","+usuario.getUsu_foto());
            btn_guardar_foto.setDisable(false);
            if(Objects.equals(imageList.get(i).getUrl(), usuario.getUsu_foto())){
                btn_guardar_foto.setDisable(true);
                foto_perfil.setImage(imageList.get(i));
            }
        }
    }
    @FXML
    private void buttonDerechaOnClick() {
        numeroImagen++;
        if (numeroImagen > 2){
            numeroImagen = 0;
        }
        btn_guardar_foto.setDisable(Objects.equals(imageList.get(numeroImagen).getUrl(), usuario.getUsu_foto()));
        foto_perfil.setImage(imageList.get(numeroImagen));
    }
    @FXML
    private void buttonIzquierdaOnClick() {
        numeroImagen--;
        if (numeroImagen < 0){
            numeroImagen = 2;
        }
        btn_guardar_foto.setDisable(Objects.equals(imageList.get(numeroImagen).getUrl(), usuario.getUsu_foto()));
        foto_perfil.setImage(imageList.get(numeroImagen));
    }
    @FXML
    private void onSalirButtonClick() {
        sceneHandler.changeToScene(SceneHandler.MENU_SCENE);
    }
    @FXML
    private void onButtonCambioNombreClick() {
        if(!(txt_cambio_nombre.getText().isEmpty())){
            if(informacionPerfilConsultas.validarCambioNombre(usuario, txt_cambio_nombre.getText())) {
                label_cambio_nombre.setText("El nombre se cambio con exito");
            } else {
                label_cambio_nombre.setText("El nombre ya existe");
            }
        }
    }
    @FXML
    private void onButtonContasenhaClick() {
        if(informacionPerfilConsultas.validarCambioContraseña(txt_cambio_contrasenha.getText(),txt_contrasenha_repetida.getText(),usuario)){
            if (!(Objects.equals(usuario.getUsu_contrasenha(), txt_cambio_contrasenha.getText()))) {
                label_cambio_contrasenha.setText("Cambio de contraseña con exito");
            }
            label_cambio_contrasenha.setText("Esta contraseña ya ha sido puesta");
        }else {
            label_cambio_contrasenha.setText("Las contraseñas no son iguales");
        }
    }
    @FXML
    private void onButtonGuardarFotoClick() {
        informacionPerfilConsultas.setFoto(imageList.get(numeroImagen).getUrl(),usuario);
        ((MenuController)prevMenu).usuario.setUsu_foto(imageList.get(numeroImagen).getUrl());
        ((MenuController)prevMenu).foto_perfil_entrada.setImage(imageList.get(numeroImagen));
    }
}
