package org.example.parcialfinal.controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class ReporteCController implements Initializable {

    @FXML
    private ComboBox<Cliente> selectCliente;

    DBConnection connection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connection = DBConnection.getInstance();

        ObservableList<Cliente> clienteValues = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        selectCliente.setItems(clienteValues);
    }

    @FXML
    void generarReporteC(ActionEvent event) {

    }

}

