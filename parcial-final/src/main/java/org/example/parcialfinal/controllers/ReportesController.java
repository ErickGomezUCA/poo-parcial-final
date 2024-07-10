package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcialfinal.LobbyApplication;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Facilitador;
import org.example.parcialfinal.backend.Mes;
import org.example.parcialfinal.backend.database.DatabaseUtils;
import org.example.parcialfinal.reports.ReporteA;
import org.example.parcialfinal.reports.ReporteB;
import org.example.parcialfinal.reports.ReporteC;
import org.example.parcialfinal.reports.ReporteD;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable { // 00300723 Define la clase controladora para los reportes
    @FXML
    private VBox main; // 00300723 Declara el contenedor principal de la vista

    @FXML
    private ComboBox<Cliente> selectCliente_RA; // 00300723 ComboBox para seleccionar cliente en Reporte A
    @FXML
    private DatePicker dateFechaInicio_RA; // 00300723 Selector de fecha inicial para Reporte A
    @FXML
    private DatePicker dateFechaFinal_RA; // 00300723 Selector de fecha final para Reporte A
    @FXML
    private TextArea txtAreaRes_RA; // 00300723 Área de texto para mostrar resultados del Reporte A

    @FXML
    private ComboBox<Cliente> selectCliente_RB; // 00300723 ComboBox para seleccionar cliente en Reporte B
    @FXML
    private ComboBox<Mes> selectMes_RB; // 00300723 ComboBox para seleccionar mes en Reporte B
    @FXML
    private Spinner<Integer> spinnerYear_RB; // 00300723 Spinner para seleccionar año en Reporte B
    @FXML
    private TextArea txtAreaRes_RB; // 00300723 Área de texto para mostrar resultados del Reporte B

    @FXML
    private ComboBox<Cliente> selectCliente_RC; // 00300723 ComboBox para seleccionar cliente en Reporte C
    @FXML
    private TextArea txtAreaRes_RC; // 00300723 Área de texto para mostrar resultados del Reporte C

    @FXML
    private ComboBox<Facilitador> selectFacilitador_RD; // 00300723 ComboBox para seleccionar facilitador en Reporte D
    @FXML
    private TextArea txtAreaRes_RD; // 00300723 Área de texto para mostrar resultados del Reporte D

    ObservableList<Cliente> clientes; // 00300723 Lista observable para almacenar clientes
    ObservableList<Facilitador> facilitadores; // 00300723 Lista observable para almacenar facilitadores

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // 00300723 Método de inicialización del controlador
        clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes()); // 00300723 Obtiene y almacena los clientes de la base de datos
        facilitadores = FXCollections.observableArrayList(DatabaseUtils.obtenerFacilitadores()); // 00300723 Obtiene y almacena los facilitadores de la base de datos

        prepararInputsReporteA(clientes); // 00300723 Prepara los inputs para el Reporte A
        prepararInputsReporteB(clientes); // 00300723 Prepara los inputs para el Reporte B
        prepararInputsReporteC(clientes); // 00300723 Prepara los inputs para el Reporte C
        prepararInputsReporteD(facilitadores); // 00300723 Prepara los inputs para el Reporte D
    }

    private void prepararInputsReporteA(ObservableList<Cliente> clientes) { // 00300723 Método para preparar inputs del Reporte A
        selectCliente_RA.setItems(clientes); // 00300723 Establece los clientes en el ComboBox del Reporte A
    }

    private void prepararInputsReporteB(ObservableList<Cliente> clientes) { // 00300723 Método para preparar inputs del Reporte B
        selectCliente_RB.setItems(clientes); // 00300723 Establece los clientes en el ComboBox del Reporte B
        selectMes_RB.setItems(FXCollections.observableArrayList(Mes.values())); // 00300723 Establece los meses en el ComboBox del Reporte B

        int minValueSpinner = 1967; // 00300723 Define el valor mínimo para el spinner de año
        int maxValueSpinner = 5000; // 00300723 Define el valor máximo para el spinner de año
        SpinnerValueFactory<Integer> yearSpinnerFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minValueSpinner, maxValueSpinner, minValueSpinner, 1); // 00300723 Crea la fábrica de valores para el spinner de año
        spinnerYear_RB.setValueFactory(yearSpinnerFactory); // 00300723 Establece la fábrica de valores en el spinner de año
        spinnerYear_RB.setEditable(true); // 00300723 Permite editar manualmente el valor del spinner de año
    }

    private void prepararInputsReporteC(ObservableList<Cliente> clientes) { // 00300723 Método para preparar inputs del Reporte C
        selectCliente_RC.setItems(clientes); // 00300723 Establece los clientes en el ComboBox del Reporte C
    }

    private void prepararInputsReporteD(ObservableList<Facilitador> facilitadores) { // 00300723 Método para preparar inputs del Reporte D
        selectFacilitador_RD.setItems(facilitadores); // 00300723 Establece los facilitadores en el ComboBox del Reporte D
    }

    @FXML
    void clickGenerarReporteA(ActionEvent event) { // 00300723 Método para generar el Reporte A
        ReporteA reporteA = new ReporteA(txtAreaRes_RA); // 00300723 Crea una instancia del Reporte A
        reporteA.generarReporte(selectCliente_RA, dateFechaInicio_RA, dateFechaFinal_RA); // 00300723 Genera el Reporte A con los parámetros seleccionados
    }

    @FXML
    void clickGenerarReporteB(ActionEvent event) { // 00300723 Método para generar el Reporte B
        ReporteB reporteB = new ReporteB(txtAreaRes_RB); // 00300723 Crea una instancia del Reporte B
        reporteB.generarReporte(selectCliente_RB, selectMes_RB, spinnerYear_RB); // 00300723 Genera el Reporte B con los parámetros seleccionados
    }

    @FXML
    void clickGenerarReporteC(ActionEvent event) { // 00300723 Método para generar el Reporte C
        ReporteC reporteC = new ReporteC(txtAreaRes_RC); // 00300723 Crea una instancia del Reporte C
        reporteC.generarReporte(selectCliente_RC); // 00300723 Genera el Reporte C con el cliente seleccionado
    }

    @FXML
    void clickGenerarReporteD(ActionEvent event) { // 00300723 Método para generar el Reporte D
        ReporteD reporteD = new ReporteD(txtAreaRes_RD); // 00300723 Crea una instancia del Reporte D
        reporteD.generarReporte(selectFacilitador_RD); // 00300723 Genera el Reporte D con el facilitador seleccionado
    }

    @FXML
    private void clickRegresar(ActionEvent event) { // 00300723 Método para regresar a la pantalla principal
        Stage stage = new Stage(); // 00300723 Crea una nueva ventana
        LobbyApplication lobbyApp = new LobbyApplication(); // 00300723 Crea una instancia de la aplicación de lobby
        try {
            lobbyApp.start(stage); // 00300723 Inicia la aplicación de lobby en la nueva ventana
        } catch (IOException e) {
            throw new RuntimeException(e); // 00300723 Maneja cualquier error de IO lanzando una excepción
        }
        stage.show(); // 00300723 Muestra la nueva ventana
        cerrar(); // 00300723 Cierra la ventana actual
    }

    @FXML
    private void cerrar() { // 00300723 Método para cerrar la ventana actual
        ((Stage)main.getScene().getWindow()).close(); // 00300723 Obtiene la ventana actual y la cierra
    }
}