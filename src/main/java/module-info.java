module org.example.java24groupe06 {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;


    exports org.example.java24groupe06.views;
    opens org.example.java24groupe06.views to javafx.fxml;
    exports org.example.java24groupe06.controllers;
}