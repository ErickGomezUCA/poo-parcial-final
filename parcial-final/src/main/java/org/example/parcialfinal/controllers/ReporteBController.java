package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;
import org.example.parcialfinal.reports.ReporteA;
import org.example.parcialfinal.reports.ReporteB;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReporteBController implements Initializable {

    @FXML
    private ComboBox<Cliente> selectCliente;

    @FXML
    private TextField txtMesABuscar;

    @FXML
    private TextField txtAnioABuscar;

    DBConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getInstance();

        ObservableList<Cliente> clienteValues = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        selectCliente.setItems(clienteValues);
    }

    @FXML
    void generarReporteB(ActionEvent event) {
//        ReporteB reporteB = new ReporteB();
//        reporteB.generarReporte(selectCliente, txtAnioABuscar, txtMesABuscar);
    }
}
