module org.example.parcialfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.parcialfinal to javafx.fxml;
    exports org.example.parcialfinal.applications;
    opens org.example.parcialfinal.applications to javafx.fxml;
    exports org.example.parcialfinal.controllers;
    opens org.example.parcialfinal.controllers to javafx.fxml;
}