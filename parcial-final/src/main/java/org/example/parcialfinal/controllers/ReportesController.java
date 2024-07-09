package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.database.DatabaseUtils;
import org.example.parcialfinal.reports.ReporteC;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {
    @FXML
    private ComboBox<Cliente> selectCliente_RC;
    @FXML
    private TextArea txtAreaRes_RC;

    @FXML
    private ComboBox<Facilitador> selectFacilitador_RD;
    @FXML
    private TableView<String> tbRes_RD;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Cliente> clienteObservableList = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        selectCliente_RC.setItems(clienteObservableList);
    }

    @FXML
    void clickGenerarReporteC(ActionEvent event) {
        ReporteC reporteC = new ReporteC(txtAreaRes_RC);
        reporteC.generarReporte(selectCliente_RC);
    }

    @FXML
    void clickGenerarReporteD(ActionEvent event) {

    }
}
