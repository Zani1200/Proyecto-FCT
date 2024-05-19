module com.campusdual.prubaslistview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.campusdual.prubaslistview to javafx.fxml;
    exports com.campusdual.prubaslistview;
}