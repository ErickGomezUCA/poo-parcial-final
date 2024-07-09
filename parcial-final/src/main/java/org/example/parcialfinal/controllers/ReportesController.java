package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Mes;
import org.example.parcialfinal.backend.database.DatabaseUtils;
import org.example.parcialfinal.reports.ReporteB;
import org.example.parcialfinal.reports.ReporteC;
import org.example.parcialfinal.reports.ReporteD;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {
    @FXML
    private ComboBox<Cliente> selectCliente_RB;
    @FXML
    private ComboBox<Mes> selectMes_RB;
    @FXML
    private Spinner<Integer> spinnerYear_RB;

    @FXML
    private TextArea txtAreaRes_RB;

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
        selectMes_RB.setItems(FXCollections.observableArrayList(Mes.values()));

        int minValueSpinner = 1967;
        int maxValueSpinner = 5000;
        SpinnerValueFactory<Integer> yearSpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValueSpinner, maxValueSpinner, minValueSpinner, 1);
        spinnerYear_RB.setValueFactory(yearSpinnerFactory);

        ObservableList<Cliente> clienteObservableList = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        selectCliente_RC.setItems(clienteObservableList);

        ObservableList<Facilitador> facilitadorObservableList = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores());
        selectFacilitador_RD.setItems(facilitadorObservableList);
    }

    @FXML
    void clickGenerarReporteB(ActionEvent event) {
        ReporteB reporteB = new ReporteB(txtAreaRes_RB);
        reporteB.generarReporte(selectCliente_RB, selectMes_RB, spinnerYear_RB);
    }

    @FXML
    void clickGenerarReporteC(ActionEvent event) {
        ReporteC reporteC = new ReporteC(txtAreaRes_RC);
        reporteC.generarReporte(selectCliente_RC);
    }

    @FXML
    void clickGenerarReporteD(ActionEvent event) {
        ReporteD reporteD = new ReporteD(tbRes_RD);
        reporteD.generarReporte(selectFacilitador_RD);
    }
}
