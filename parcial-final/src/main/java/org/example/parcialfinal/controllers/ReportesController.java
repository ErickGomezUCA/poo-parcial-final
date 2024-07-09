package org.example.parcialfinal.controllers;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
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

    }

    @FXML
    void clickGenerarReporteC(ActionEvent event) {
        ReporteC reporteC = new ReporteC();
    }

    @FXML
    void clickGenerarReporteD(ActionEvent event) {

    }
}
