package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcialfinal.LobbyApplication;
import org.example.parcialfinal.backend.Compra;
import org.example.parcialfinal.backend.Tarjeta;
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
    private TextField txtDescripcionActualizarCompra;


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


    @FXML
    public void initialize() {
        cargarCompras();

        ObservableList<Tarjeta> tarjetas = FXCollections.observableArrayList(DatabaseUtils.obtenerTarjetas());
        ObservableList<Compra> compras = FXCollections.observableArrayList(DatabaseUtils.obtenerCompras());

        SpinnerValueFactory<Double> valueFactoryCrear = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 0.0, 1);
        SpinnerValueFactory<Double> valueFactoryActualizar = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 0.0, 1);
        spinnerMontoActualizarCompra.setValueFactory(valueFactoryActualizar);

        prepararCrear(valueFactoryCrear, tarjetas);
        prepararBuscar(compras);
    }

    private void prepararCrear(SpinnerValueFactory<Double> valueFactoryCrear, ObservableList<Tarjeta> tarjetas) {
        spinnerMontoAgregarCompra.setValueFactory(valueFactoryCrear);
        selectAgregarTarjeta.setItems(tarjetas);
    }

    private void prepararBuscar(ObservableList<Compra> compras) {
        selectActualizarCompra.setItems(compras);
    }

    private void prepararActualizar() {}

    private void prepararEliminar() {}

    @FXML
    public void agregarCompra() {
        txtMensajeAgregarCompra.clear();
        String fecha = dtFechaAgregarCompra.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double monto = spinnerMontoAgregarCompra.getValue();
        String descripcion = txtDescripcionAgregarCompra.getText();
        int idTarjeta = selectAgregarTarjeta.getValue().getId();

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("INSERT INTO Compra VALUES (?, ?, ?, ?)");
            st.setString(1, fecha);
            st.setDouble(2, monto);
            st.setString(3, descripcion);
            st.setInt(4, idTarjeta);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeAgregarCompra.setText("Compra agregada con exito");

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
        txtMensajeBuscarCompra.clear();
        int id =(selectBuscarCompra.getValue().getId());

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT * FROM Compra WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int idCompra = rs.getInt("id");
                //cambiar a DATE TODO
                String fecha = rs.getString("fecha_compra");
                Double monto = rs.getDouble("monto");
                String descripcion = rs.getString("descripcion");
                int idTarjeta = rs.getInt("id_tarjeta_C");

                txtMensajeBuscarCompra.appendText("COMPRA ENCONTRADA:\n" + idCompra + ", " + fecha + ", " + monto + ", " + descripcion + ", " + idTarjeta + "\n");
            } else {
                txtMensajeBuscarCompra.setText("Compra no encontrado en base de datos");
            }
            connection.closeConnection();
        } catch (Exception e) {
            txtMensajeBuscarCompra.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void eliminarCompra() {
        txtMensajeEliminarCompra.clear();
        int id = selectEliminarCompra.getValue().getId();

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("DELETE FROM Compra WHERE id = ?");
            st.setInt(1, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeEliminarCompra.setText("Compra eliminada con exito");
                cargarCompras();
            } else {
                txtMensajeEliminarCompra.setText("Compra no encontrada en base de datos");
            }

            connection.closeConnection();
        } catch (Exception e) {
            txtMensajeEliminarCompra.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void actualizarCompra() {
        txtMensajeActualizarCompra.clear();

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
                txtMensajeActualizarCompra.setText("Compra actualizada con exito");

                dtFechaActualizarCompra.setValue(null);
                spinnerMontoAgregarCompra.getValueFactory().setValue(0.0);
                txtDescripcionActualizarCompra.clear();

                cargarCompras();
            } else {
                txtMensajeActualizarCompra.setText("Compra no encontrada en base de datos");
            }
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
