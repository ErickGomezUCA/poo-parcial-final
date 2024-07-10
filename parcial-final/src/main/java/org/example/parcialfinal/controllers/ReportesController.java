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
import org.example.parcialfinal.reports.ReporteA;
import org.example.parcialfinal.reports.ReporteB;
import org.example.parcialfinal.reports.ReporteC;
import org.example.parcialfinal.reports.ReporteD;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {
    @FXML
    private ComboBox<Cliente> selectCliente_RA;
    @FXML
    private DatePicker dateFechaInicio_RA;
    @FXML
    private DatePicker dateFechaFinal_RA;
    @FXML
    private TextArea txtAreaRes_RA;

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
    private TextArea txtAreaRes_RD;

    ObservableList<Cliente> clientes;
    ObservableList<Facilitador> facilitadores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        facilitadores = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores());

        prepararInputsReporteA(clientes);
        prepararInputsReporteB(clientes);
        prepararInputsReporteC(clientes);
        prepararInputsReporteD(facilitadores);
    }

    private void prepararInputsReporteA(ObservableList<Cliente> clientes) {
        selectCliente_RA.setItems(clientes);
    }

    private void prepararInputsReporteB(ObservableList<Cliente> clientes) {
        selectCliente_RB.setItems(clientes);

        selectMes_RB.setItems(FXCollections.observableArrayList(Mes.values()));

        int minValueSpinner = 1967;
        int maxValueSpinner = 5000;
        SpinnerValueFactory<Integer> yearSpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValueSpinner, maxValueSpinner, minValueSpinner, 1);
        spinnerYear_RB.setValueFactory(yearSpinnerFactory);
        spinnerYear_RB.setEditable(true);
    }

    private void prepararInputsReporteC(ObservableList<Cliente> clientes) {
        selectCliente_RC.setItems(clientes);
    }

    private void prepararInputsReporteD(ObservableList<Facilitador> facilitadores) {
        selectFacilitador_RD.setItems(facilitadores);
    }

    @FXML
    void clickGenerarReporteA(ActionEvent event) {
        ReporteA reporteA = new ReporteA(txtAreaRes_RA);
        reporteA.generarReporte(selectCliente_RA, dateFechaInicio_RA, dateFechaFinal_RA);

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
        ReporteD reporteD = new ReporteD(txtAreaRes_RD);
        reporteD.generarReporte(selectFacilitador_RD);
    }
}
