package org.example.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.parcialfinal.backend.Compra;
import org.example.parcialfinal.backend.database.DBConnection;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CompraController {
    DBConnection connection = DBConnection.getInstance();

    @FXML
    private DatePicker dtFechaAgregarCompra;//00167523

    @FXML
    private Spinner<Double> spinnerMontoAgregarCompra;//00167523

    @FXML
        private TextArea txtDescripcionAgregarCompra;//00167523

    @FXML
    //cambiar a combobox todo
    private TextField txtIdTarjetaAgregarCompra;//00167523


    //camniar a combobox todo
    @FXML
    private TextField txtIdBuscarCompra;//00167523

    //cambiar a combobox todo
    @FXML
    private TextField txtIdEliminarCompra;//00167523

    //cambiar a combobox todo
    @FXML
    private TextField txtIdActualizarCompra;//00167523

    @FXML
    private DatePicker dtFechaActualizarCompra;//00167523

    @FXML
    private Spinner<Double> spinnerMontoActualizarCompra;//00167523

    @FXML
    private TextField txtDescripcionActualizarCompra;//00167523

    @FXML
    private TextField txtIdTarjetaActualizarCompra;//00167523


    @FXML
    private TextArea txtMensajeAgregarCompra;//00167523

    @FXML
    private TextArea txtMensajeBuscarCompra;//00167523

    @FXML
    private TextArea txtMensajeMostrarTodosCompra;//00167523

    @FXML
    private TextArea txtMensajeEliminarCompra;//00167523

    @FXML
    private TextArea txtMensajeActualizarCompra;//00167523

    private ArrayList<Compra> compras = new ArrayList<>();//00167523


    @FXML
    public void initialize() {
        cargarCompras();

        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 10000.0, 0.0, 1);
        spinnerMontoAgregarCompra.setValueFactory(valueFactory);
        spinnerMontoActualizarCompra.setValueFactory(valueFactory);
    }

    @FXML
    public void agregarCompra() {
        txtMensajeAgregarCompra.clear();
        String fecha = dtFechaAgregarCompra.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double monto = spinnerMontoAgregarCompra.getValue();
        String descripcion = txtDescripcionAgregarCompra.getText();
        int idTarjeta = Integer.parseInt(txtIdTarjetaAgregarCompra.getText());

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
                txtIdTarjetaAgregarCompra.clear();

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
        int id = Integer.parseInt(txtIdBuscarCompra.getText());

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
        int id = Integer.parseInt(txtIdEliminarCompra.getText());

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

        int id = Integer.parseInt(txtIdActualizarCompra.getText());
        String fecha = dtFechaActualizarCompra.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Double monto = spinnerMontoAgregarCompra.getValue();
        String descripcion = txtDescripcionActualizarCompra.getText();
        int idTarjeta = Integer.parseInt(txtIdTarjetaActualizarCompra.getText());

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

                txtIdActualizarCompra.clear();
                dtFechaActualizarCompra.setValue(null);
                spinnerMontoAgregarCompra.getValueFactory().setValue(0.0);
                txtDescripcionActualizarCompra.clear();
                txtIdTarjetaActualizarCompra.clear();

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
}
