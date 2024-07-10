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
    private DatePicker dtFechaAgregarCompra;

    @FXML
    private Spinner<Double> spinnerMontoAgregarCompra;

    @FXML
        private TextArea txtDescripcionAgregarCompra;

    @FXML
    //cambiar a combobox todo
    private TextField txtIdTarjetaAgregarCompra;


    //camniar a combobox todo
    @FXML
    private TextField txtIdBuscarCompra;

    //cambiar a combobox todo
    @FXML
    private TextField txtIdEliminarCompra;

    //cambiar a combobox todo
    @FXML
    private TextField txtIdActualizarCompra;

    @FXML
    private DatePicker dtFechaActualizarCompra;

    @FXML
    private Spinner<Double> spinnerMontoActualizarCompra;

    @FXML
    private TextField txtDescripcionActualizarCompra;

    @FXML
    private TextField txtIdTarjetaActualizarCompra;


    @FXML
    private TextArea txtMensajeAgregarCompra;

    @FXML
    private TextArea txtMensajeBuscarCompra;

    @FXML
    private TextArea txtMensajeMostrarTodosCompra;

    @FXML
    private TextArea txtMensajeEliminarCompra;

    @FXML
    private TextArea txtMensajeActualizarCompra;

    private ArrayList<Compra> compras = new ArrayList<>();


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
