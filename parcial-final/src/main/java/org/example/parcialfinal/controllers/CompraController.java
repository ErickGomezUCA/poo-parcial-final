package org.example.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.Compra;

import java.sql.*;
import java.util.ArrayList;

public class CompraController {
    @FXML
    private Button btnAgregarCompra;

    @FXML
    private Button btnBuscarCompra;

    @FXML
    private Button btnMostrarTodosCompra;

    @FXML
    private Button btnEliminarCompra;

    @FXML
    private Button btnActualizarCompra;


    @FXML
    private TextField txtFechaAgregarCompra;

    @FXML
    private TextField txtMontoAgregarCompra;

    @FXML
    private TextField txtDescripcionAgregarCompra;

    @FXML
    private TextField txtIdTarjetaAgregarCompra;

    @FXML
    private TextField txtIdBuscarCompra;

    @FXML
    private TextField txtIdEliminarCompra;

    @FXML
    private TextField txtIdActualizarCompra;

    @FXML
    private TextField txtFechaActualizarCompra;

    @FXML
    private TextField txtMontoActualizarCompra;

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
    }

    @FXML
    public void agregarCompra() {
        txtMensajeAgregarCompra.clear();
        //comprobar la fecha con DATE de java TODO
        String fecha = txtFechaAgregarCompra.getText();
        Double monto = Double.parseDouble(txtMontoAgregarCompra.getText());
        String descripcion = txtDescripcionAgregarCompra.getText();
        int idTarjeta = Integer.parseInt(txtIdTarjetaAgregarCompra.getText());

        if (fecha.equals("")) {
            txtFechaAgregarCompra.setText("Este campo es obligatorio");
        }
        //dudas con este TODO
        if (txtMontoAgregarCompra.getText().trim().isEmpty()) {
            txtFechaAgregarCompra.setText("Este campo es obligatorio");
        }

        if (descripcion.equals("")) {
            txtDescripcionAgregarCompra.setText("Este campo es obligatorio");
        }

        if (txtIdTarjetaAgregarCompra.getText().trim().isEmpty()) {
            txtIdTarjetaAgregarCompra.setText("Este campo es obligatorio");
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("INSERT INTO Compra VALUES (?, ?, ?, ?)");
            st.setString(1, fecha);
            st.setDouble(2, monto);
            st.setString(3, descripcion);
            st.setInt(4, idTarjeta);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeAgregarCompra.setText("Compra agregada con exito");

                txtFechaAgregarCompra.clear();
                txtMontoAgregarCompra.clear();
                txtDescripcionAgregarCompra.clear();
                txtIdTarjetaAgregarCompra.clear();

                cargarCompras();
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void buscarCompra() {
        txtMensajeBuscarCompra.clear();
        int id = Integer.parseInt(txtIdBuscarCompra.getText());

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Compra WHERE id = ?");
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
            conn.close();
        } catch (Exception e) {
            txtMensajeBuscarCompra.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void mostrarTodosCompra() {
        txtMensajeMostrarTodosCompra.clear();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Compra");

            while (rs.next()) {
                int idCompra = rs.getInt("id");
                //cambiar a DATE TODO
                String fecha = rs.getString("fecha_compra");
                Double monto = rs.getDouble("monto");
                String descripcion = rs.getString("descripcion");
                int idTarjeta = rs.getInt("id_tarjeta_C");

                txtMensajeMostrarTodosCompra.appendText("COMPRA:\n" + idCompra + ", " + fecha + ", " + monto + ", " + descripcion + ", " + idTarjeta + "\n");
            }
            conn.close();
        } catch (Exception e) {
            txtMensajeMostrarTodosCompra.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void eliminarCompra() {
        txtMensajeEliminarCompra.clear();
        int id = Integer.parseInt(txtIdEliminarCompra.getText());

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("DELETE FROM Compra WHERE id = ?");
            st.setInt(1, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeEliminarCompra.setText("Compra eliminada con exito");
                cargarCompras();
            } else {
                txtMensajeEliminarCompra.setText("Compra no encontrada en base de datos");
            }

            conn.close();
        } catch (Exception e) {
            txtMensajeEliminarCompra.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void actualizarCompra() {
        txtMensajeActualizarCompra.clear();

        int id = Integer.parseInt(txtIdActualizarCompra.getText());
        String fecha = txtFechaActualizarCompra.getText();
        Double monto = Double.parseDouble(txtMontoActualizarCompra.getText());
        String descripcion = txtDescripcionActualizarCompra.getText();
        int idTarjeta = Integer.parseInt(txtIdTarjetaActualizarCompra.getText());

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("UPDATE Compra SET ?, ?, ?, ? WHERE id = ?");
            st.setString(1, fecha);
            st.setDouble(2, monto);
            st.setString(3, descripcion);
            st.setInt(4, idTarjeta);
            st.setInt(5, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeActualizarCompra.setText("Compra actualizada con exito");

                txtIdActualizarCompra.clear();
                txtFechaActualizarCompra.clear();
                txtMontoActualizarCompra.clear();
                txtDescripcionActualizarCompra.clear();
                txtIdTarjetaActualizarCompra.clear();

                cargarCompras();
            } else {
                txtMensajeActualizarCompra.setText("Compra no encontrada en base de datos");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void cargarCompras() {
        compras = new ArrayList();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            Statement st = conn.createStatement();
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
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
