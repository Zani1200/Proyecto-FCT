package com.campusdual.prubaslistview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ListView<String> listViewPrueba = new ListView<>();
    private ObservableList<String> observableList = FXCollections.observableArrayList( "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewPrueba.setItems(observableList);
        listViewPrueba.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ponerBoton();
            }
        });
    }

    static class ponerBoton extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty){
            super.updateItem(item, empty);
            HBox hBox = new HBox();
            ImageView foto_perfil = new ImageView();
            Image image = new Image("C:\\Users\\cie\\OneDrive\\Imágenes\\Escritorio\\Fotos\\agua.jpeg");
            foto_perfil.setImage(image);
            foto_perfil.setFitWidth(50);
            foto_perfil.setFitHeight(50);
            Label label = new Label();

            hBox.getChildren().addAll(foto_perfil,label);
            if (item != null) {
                label.setText(item+" 1"+"\n" +
                        "\n" +
                        "FUncionaaaaaaa");
                setGraphic(hBox);
            }
        }
    }
}