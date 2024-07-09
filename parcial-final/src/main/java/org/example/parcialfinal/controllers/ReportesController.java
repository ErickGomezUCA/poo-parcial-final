package org.example.parcialfinal.controllers;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {
    @FXML
    private ComboBox<?> selectCliente_RC;

    @FXML
    private ComboBox<?> selectFacilitador_RD;

    @FXML
    private TableView<?> tbRes_RD;

    @FXML
    private TextArea txtAreaRes_RC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void clickGenerarReporteC(ActionEvent event) {

    }

    @FXML
    void clickGenerarReporteD(ActionEvent event) {

    }
}
