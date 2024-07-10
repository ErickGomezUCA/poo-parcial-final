package org.example.parcialfinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcialfinal.applications.ClienteApplication;
import org.example.parcialfinal.applications.CompraApplication;
import org.example.parcialfinal.applications.ReportesApplication;
import org.example.parcialfinal.applications.TarjetaApplication;
import org.example.parcialfinal.backend.Tarjeta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {
    @FXML
    private VBox main;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void cerrar() {
        ((Stage)main.getScene().getWindow()).close();
    }

    @FXML
    void clickAbrirClientes(ActionEvent event) {
        Stage stage = new Stage();
        ClienteApplication clienteApp = new ClienteApplication();
        try {
            clienteApp.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        cerrar();
    }

    @FXML
    void clickAbrirCompras(ActionEvent event) {
        Stage stage = new Stage();
        CompraApplication compraApp = new CompraApplication();
        try {
            compraApp.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        cerrar();
    }

    @FXML
    void clickAbrirReportes(ActionEvent event) {
        Stage stage = new Stage();
        ReportesApplication reportesApp = new ReportesApplication();
        try {
            reportesApp.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        cerrar();
    }

    @FXML
    void clickAbrirTarjetas(ActionEvent event) {
        Stage stage = new Stage();
        TarjetaApplication tarjetaApp = new TarjetaApplication();
        try {
            tarjetaApp.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        cerrar();
    }

    @FXML
    void clickSalir(ActionEvent event) {
        cerrar();
    }
}
