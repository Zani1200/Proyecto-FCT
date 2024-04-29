module com.campusdual.application_fct {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql;


    opens com.campusdual.application_fct to javafx.fxml;
    opens com.campusdual.application_fct.entities;
    exports com.campusdual.application_fct;
}