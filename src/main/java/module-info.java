module com.example.elektrocalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires atlantafx.base;
    requires matheclipse.core;
    requires matheclipse.parser;
    requires org.json;
    requires com.fasterxml.jackson.databind;

    opens com.example.elektrocalc to javafx.fxml;
    exports com.example.elektrocalc;
}