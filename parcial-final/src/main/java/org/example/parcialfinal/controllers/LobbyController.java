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

public class LobbyController { // 00300723 Clase que controla las funciones del menu principal
    @FXML
    private VBox main;  // 00300723 Elemento principal de la interfaz

    private void cerrar() { // 00300723 Funcion para cerrar la ventana actual
        ((Stage)main.getScene().getWindow()).close();  // 00300723 Cierra la ventana actual
    }

    @FXML
    void clickAbrirClientes(ActionEvent event) { // 00300723 funcion para abrir el crud de clientes
        Stage stage = new Stage();  // 00300723 Crea una nueva ventana
        ClienteApplication clienteApp = new ClienteApplication();  // 00300723 Instancia la aplicación de clientes
        try { // 00300723 Para abrir la ventana clientes
            clienteApp.start(stage);  // 00300723 Inicia la aplicación de clientes
        } catch (IOException e) { // 00300723 En caso que no se abra
            throw new RuntimeException(e);  // 00300723 Maneja errores de IO lanzando una excepción
        }
        stage.show();  // 00300723 Muestra la nueva ventana
        cerrar();  // 00300723 Cierra la ventana actual
    }

    @FXML
    void clickAbrirCompras(ActionEvent event) { // 00300723 Funcion para abrir el crud de compras
        Stage stage = new Stage();  // 00300723 Crea una nueva ventana
        CompraApplication compraApp = new CompraApplication();  // 00300723 Instancia la aplicación de compras
        try { // 00300723 Para abrir Compras
            compraApp.start(stage);  // 00300723 Inicia la aplicación de compras
        } catch (IOException e) { // 00300723 En caso que no
            throw new RuntimeException(e);  // 00300723 Maneja errores de IO lanzando una excepción
        }
        stage.show();  // 00300723 Muestra la nueva ventana
        cerrar();  // 00300723 Cierra la ventana actual
    }

    @FXML
    void clickAbrirReportes(ActionEvent event) { // 00300723 Funcion para abrir los reportes
        Stage stage = new Stage();  // 00300723 Crea una nueva ventana
        ReportesApplication reportesApp = new ReportesApplication();  // 00300723 Instancia la aplicación de reportes
        try { // 00300723 Para abrir reportes
            reportesApp.start(stage);  // 00300723 Inicia la aplicación de reportes
        } catch (IOException e) { // 00300723 En caso que no
            throw new RuntimeException(e);  // 00300723 Maneja errores de IO lanzando una excepción
        }
        stage.show();  // 00300723 Muestra la nueva ventana
        cerrar();  // 00300723 Cierra la ventana actual
    }

    @FXML
    void clickAbrirTarjetas(ActionEvent event) { // Funcion para abrir el crud de tarjeta
        Stage stage = new Stage();  // 00300723 Crea una nueva ventana
        TarjetaApplication tarjetaApp = new TarjetaApplication();  // 00300723 Instancia la aplicación de tarjetas
        try { // 00300723 Para abrir tarjetas
            tarjetaApp.start(stage);  // 00300723 Inicia la aplicación de tarjetas
        } catch (IOException e) { // 00300723 En caso que no
            throw new RuntimeException(e);  // 00300723 Maneja errores de IO lanzando una excepción
        }
        stage.show();  // 00300723 Muestra la nueva ventana
        cerrar();  // 00300723 Cierra la ventana actual
    }

    @FXML
    void clickSalir(ActionEvent event) { // 00300723 Funcion para cerrar el programa
        cerrar();  // 00300723 Cierra la aplicación al hacer clic en salir
    }
}