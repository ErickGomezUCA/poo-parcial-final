package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;
import org.example.parcialfinal.reports.ReporteA;
import org.example.parcialfinal.reports.ReporteC;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ReporteAController implements Initializable {

    @FXML
    private ComboBox<Cliente> selectCliente;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private DatePicker fechaFin;

    DBConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getInstance();

        ObservableList<Cliente> clienteValues = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        selectCliente.setItems(clienteValues);
    }

    @FXML
    void generarReporteA(ActionEvent event) {
        ReporteA reporteA = new ReporteA();
        reporteA.generarReporte(selectCliente, fechaInicio, fechaFin);
    }
}
