package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ReporteDController implements Initializable {

    @FXML
    private ComboBox<Facilitador> selectFacilitador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Facilitador> facilitadorValues = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores());
        selectFacilitador.setItems(facilitadorValues);
    }

    @FXML
    void generarReporteD(ActionEvent event) {

    }
}
