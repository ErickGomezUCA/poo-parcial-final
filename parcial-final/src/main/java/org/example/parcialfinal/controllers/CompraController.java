package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcialfinal.LobbyApplication;
import org.example.parcialfinal.backend.Compra;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.alertas.Alerta;
import org.example.parcialfinal.backend.database.DBConnection;

import javafx.event.ActionEvent;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompraController {
    DBConnection connection = DBConnection.getInstance();
    @FXML
    private VBox main;

    @FXML
    private DatePicker dtFechaAgregarCompra;
    @FXML
    private Spinner<Double> spinnerMontoAgregarCompra;
    @FXML
    private ComboBox<Tarjeta> selectAgregarTarjeta;
    @FXML
    private TextArea txtDescripcionAgregarCompra;

    @FXML
    private ComboBox<Compra> selectBuscarCompra;

    @FXML
    private ComboBox<Compra> selectEliminarCompra;

    //cambiar a combobox todo
    @FXML
    private ComboBox<Compra> selectActualizarCompra;
    @FXML
    private DatePicker dtFechaActualizarCompra;
    @FXML
    private Spinner<Double> spinnerMontoActualizarCompra;
    @FXML
    private ComboBox<Tarjeta> selectActualizarTarjeta;
    @FXML
    private TextArea txtDescripcionActualizarCompra;


    @FXML
    private TableView<Compra> tableCompra;
    @FXML
    private TableColumn<Compra, Integer> colId;
    @FXML
    private TableColumn<Compra, String> colFecha;
    @FXML
    private TableColumn<Compra, Double> colMonto;
    @FXML
    private TableColumn<Compra, Integer> colTarjeta;
    @FXML
    private TableColumn<Compra, String> colDescripcion;



    private ArrayList<Compra> compras = new ArrayList<>();
    Alerta alerta = new Alerta();


    @FXML
    public void initialize() {
        cargarCompras();

        actualizarInputs();
        mostrarTodasCompras();
    }

    private void actualizarInputs() {
        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(DatabaseUtils.obtenerTarjetas());
        ObservableList<Compra> compras = FXCollections.observableArrayList(DatabaseUtils.obtenerCompras());

        SpinnerValueFactory<Double> valueFactoryCrear = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 0.0, 1);
        SpinnerValueFactory<Double> valueFactoryActualizar = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 0.0, 1);
        spinnerMontoActualizarCompra.setValueFactory(valueFactoryActualizar);

        prepararCrear(valueFactoryCrear, tarjetas);
        prepararBuscar(compras);
        prepararActualizar(compras, valueFactoryActualizar, tarjetas);
        prepararEliminar(compras);
    }

    private void prepararCrear(SpinnerValueFactory<Double> valueFactoryCrear, ObservableList<Tarjeta> tarjetas) {
        spinnerMontoAgregarCompra.setValueFactory(valueFactoryCrear);
        selectAgregarTarjeta.setItems(tarjetas);
    }

    private void prepararBuscar(ObservableList<Compra> compras) {
        selectBuscarCompra.setItems(compras);
    }

    private void prepararActualizar(ObservableList<Compra> compras, SpinnerValueFactory<Double> valueFactoryActualizar, ObservableList<Tarjeta> tarjetas) {
        selectActualizarTarjeta.setItems(tarjetas);
        spinnerMontoActualizarCompra.setValueFactory(valueFactoryActualizar);
        selectActualizarTarjeta.setItems(tarjetas);
    }

    private void prepararEliminar(ObservableList<Compra> compras) {
        selectEliminarCompra.setItems(compras);
    }

    private void mostrarTodasCompras() {
        ObservableList<Compra> compras = FXCollections.observableArrayList(DatabaseUtils.obtenerCompras());
        mostrarCompra(compras);
    }

    private void mostrarCompra(ObservableList<Compra> compras) {
        tableCompra.setItems(compras);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colTarjeta.setCellValueFactory(new PropertyValueFactory<>("idTajerta"));
    }

    @FXML
    public void agregarCompra() {
        String fecha = dtFechaAgregarCompra.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double monto = spinnerMontoAgregarCompra.getValue();
        String descripcion = txtDescripcionAgregarCompra.getText();
        int idTarjeta = selectAgregarTarjeta.getValue().getId();

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("INSERT INTO Compra(fecha_compra, monto, descripcion, id_tarjeta_C) VALUES (?, ?, ?, ?)");
            st.setString(1, fecha);
            st.setDouble(2, monto);
            st.setString(3, descripcion);
            st.setInt(4, idTarjeta);

            int filas = st.executeUpdate();
            if (filas > 0) {
                alerta.mostrarMensaje("Compras", "Compra agregada con exito");
                mostrarTodasCompras();
                actualizarInputs();


                dtFechaAgregarCompra.setValue(null);
                spinnerMontoAgregarCompra.getValueFactory().setValue(0.0);
                txtDescripcionAgregarCompra.clear();

                cargarCompras();
            }
            connection.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void buscarCompra() {
        int id =(selectBuscarCompra.getValue().getId());
        ObservableList<Compra> compras = FXCollections.observableArrayList();

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT * FROM Compra WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                compras.add(new Compra(rs.getInt("id"), rs.getString("fecha_compra"), rs.getDouble("monto"), rs.getString("descripcion"), rs.getInt("id_tarjeta_C")));
                alerta.mostrarMensaje("Compras", "Compra encontrada");

                } else {
                alerta.mostrarError("Compras error","Compra no encontrado en base de datos", "");
            }

            mostrarCompra(compras);

            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void eliminarCompra() {
        int id = selectEliminarCompra.getValue().getId();

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("DELETE FROM Compra WHERE id = ?");
            st.setInt(1, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                alerta.mostrarMensaje("Compras", "Compra eliminada con exito");
                cargarCompras();
            } else {
                alerta.mostrarError("Compra error", "Compra no encontrada en base de datos", "");
            }

            mostrarTodasCompras();
            actualizarInputs();
            connection.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actualizarCompra() {
        int id = selectActualizarCompra.getValue().getId();
        String fecha = dtFechaActualizarCompra.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double monto = spinnerMontoAgregarCompra.getValue();
        String descripcion = txtDescripcionActualizarCompra.getText();
        int idTarjeta = selectActualizarTarjeta.getValue().getId();

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("UPDATE Compra SET ?, ?, ?, ? WHERE id = ?");
            st.setString(1, fecha);
            st.setDouble(2, monto);
            st.setString(3, descripcion);
            st.setInt(4, idTarjeta);
            st.setInt(5, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                alerta.mostrarMensaje("Compra", "Compra actualizada con exito");

                dtFechaActualizarCompra.setValue(null);
                spinnerMontoAgregarCompra.getValueFactory().setValue(0.0);
                txtDescripcionActualizarCompra.clear();

                cargarCompras();
            } else {
                alerta.mostrarError("Compra error", "Compra no encontrada en base de datos", "");
            }
            mostrarTodasCompras();
            actualizarInputs();
            connection.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cargarCompras() {
        compras = new ArrayList();
        try {
            Statement st = connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Compra");

            while (rs.next()) {
                int idCompra = rs.getInt("id");
                //cambiar a DATE TODO
                String fecha = rs.getString("fecha_compra");
                Double monto = rs.getDouble("monto");
                String descripcion = rs.getString("descripcion");
                int idTarjeta = rs.getInt("id_tarjeta_C");

                Compra compra = new Compra(idCompra, fecha, monto, descripcion, idTarjeta);
                compras.add(compra);
            }
            connection.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private void clickRegresar(ActionEvent event) {
        Stage stage = new Stage();
        LobbyApplication lobbyApp = new LobbyApplication();
        try {
            lobbyApp.start(stage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
        cerrar();
    }

    @FXML
    private void cerrar() {
        ((Stage)main.getScene().getWindow()).close();
    }
}
