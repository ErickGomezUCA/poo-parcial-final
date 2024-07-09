package org.example.parcialfinal.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class ClienteController {
    DBConnection connection = DBConnection.getInstance();

    @FXML
    private Button btnAgregarCliente;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnEliminarCliente;

    @FXML
    private Button btnActualizarCliente;


    @FXML
    private TextField txtNombreCompletoAgregarCliente;

    @FXML
    private TextField txtDireccionAgregarCliente;

    @FXML
    private TextField txtNumTelefonoAgregarCliente;

    @FXML
    private TextField txtIdActualizarCliente;

    @FXML
    private TextField txtNombreCompletoActualizarCliente;

    @FXML
    private TextField txtDireccionActualizarCliente;

    @FXML
    private TextField txtNumTelefonoActualizarCliente;


    @FXML
    private TextArea txtMensajeError;


    @FXML
    private TextField txtIdBuscarCliente;

    @FXML
    private TextField txtIdEliminarCliente;

    private ArrayList<Cliente> clientes;

    @FXML
    public void initialize() {
        cargarClientes();
    }

    @FXML
    public void agregarCliente() {
        txtMensajeError.clear();

        String nombreCompleto = txtNombreCompletoAgregarCliente.getText();
        String direccion = txtDireccionAgregarCliente.getText();
        String numTelefono = txtNumTelefonoAgregarCliente.getText();

        if (nombreCompleto.equals("")) {
            txtNombreCompletoAgregarCliente.setText("Este campo es obligatorio");
        }
        if (direccion.equals("")) {
            txtDireccionAgregarCliente.setText("Este campo es obligatorio");
        }
        if (numTelefono.equals("")) {
            txtNumTelefonoAgregarCliente.setText("Este campo es obligatorio");
        }

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("INSERT INTO Cliente VALUES (?, ?, ?)");
            st.setString(1, nombreCompleto);
            st.setString(2, direccion);
            st.setString(3, numTelefono);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeError.setText("Cliente agregado con exito");

                txtNombreCompletoAgregarCliente.clear();
                txtDireccionAgregarCliente.clear();
                txtNumTelefonoAgregarCliente.clear();

                cargarClientes();
            }
            connection.closeConnection();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void buscarCliente() {
        txtMensajeError.clear();
        int id = Integer.parseInt(txtIdBuscarCliente.getText());

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("SELECT * FROM Cliente WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int idString = rs.getInt("id");
                String nombreCompleto = rs.getString("nombre_completo");
                String direccion = rs.getString("direccion");
                String numTelefono = rs.getString("num_telefono");

                txtMensajeError.appendText("CLIENTE ENCONTRADO:\n" + idString + ", " + nombreCompleto + ", " + direccion + ", " + numTelefono + ".");
            } else {
                txtMensajeError.setText("Cliente no encontrado en base de datos");
            }
            connection.closeConnection();
        } catch (Exception e) {
            txtMensajeError.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void eliminarCliente() {
        txtMensajeError.clear();
        int id = Integer.parseInt(txtIdEliminarCliente.getText());

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("DELETE FROM Cliente WHERE id = ?");
            st.setInt(1, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeError.setText("Cliente eliminado con exito");
                cargarClientes();
            } else {
                txtMensajeError.setText("Cliente no encontrado en base de datos");
            }

            connection.closeConnection();
        } catch (Exception e) {
            txtMensajeError.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void actualizarCliente() {
        txtMensajeError.clear();
        int id = Integer.parseInt(txtIdActualizarCliente.getText());
        String nombreCompleto = txtNombreCompletoActualizarCliente.getText();
        String direccion = txtDireccionActualizarCliente.getText();
        String numTelefono = txtNumTelefonoActualizarCliente.getText();

        try {
            PreparedStatement st = connection.getConnection().prepareStatement("UPDATE Cliente SET ?, ?, ? WHERE id = ?");
            st.setString(1, nombreCompleto);
            st.setString(2, direccion);
            st.setString(3, numTelefono);
            st.setInt(4, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeError.setText("Cliente actualizado con exito");

                txtIdActualizarCliente.clear();
                txtNombreCompletoActualizarCliente.clear();
                txtDireccionActualizarCliente.clear();
                txtNumTelefonoActualizarCliente.clear();

                cargarClientes();
            } else {
                txtMensajeError.setText("Cliente no encontrado en base de datos");
            }
            connection.closeConnection();
        } catch (Exception e) {
            txtMensajeError.setText("Error: " + e.getMessage());
        }
    }

    @FXML

    private void cargarClientes() {
        clientes = new ArrayList<>();
        try {
            Statement st = connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Cliente");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreCompleto = rs.getString("nombre_completo");
                String direccion = rs.getString("direccion");
                String numTelefono = rs.getString("num_telefono");

                Cliente cliente = new Cliente(id, nombreCompleto, direccion, numTelefono);
                clientes.add(cliente);
            }
            connection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
