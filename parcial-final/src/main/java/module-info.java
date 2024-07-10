module org.example.parcialfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;

    opens org.example.parcialfinal to javafx.fxml;
    opens org.example.parcialfinal.backend to javafx.base;

    exports org.example.parcialfinal;
    exports org.example.parcialfinal.applications;
    exports org.example.parcialfinal.controllers;
    exports org.example.parcialfinal.backend;

    opens org.example.parcialfinal.applications to javafx.fxml;
    opens org.example.parcialfinal.controllers to javafx.fxml;
}