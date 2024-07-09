package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;
import org.example.parcialfinal.backend.reportes.ReporteUtils;
import org.example.parcialfinal.reports.ReporteD;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReporteDController implements Initializable {

    @FXML
    private ComboBox<Facilitador> selectFacilitador;

    DBConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getInstance();

        ObservableList<Facilitador> facilitadorValues = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores());
        selectFacilitador.setItems(facilitadorValues);
    }

    @FXML
    void generarReporteD(ActionEvent event) {
        ReporteD reporte = new ReporteD();
        reporte.generarReporte(selectFacilitador);
    }
}
