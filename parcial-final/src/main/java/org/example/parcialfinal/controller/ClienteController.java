package org.example.parcialfinal.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.parcialfinal.backend.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClienteController {
    @FXML
    private Button btnAgregarCliente;

    @FXML
    private Button btnBuscarCliente;

    @FXML
    private Button btnMostrarTodosCliente;

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
    private TextArea txtMensajeAgregarCliente;

    @FXML
    private TextArea txtMensajeBuscarCliente;

    @FXML
    private TextArea txtMensajeEliminarCliente;

    @FXML
    private TextArea txtMensajeMostrarTodosCliente;

    @FXML
    private TextArea txtMensajeActualizarCliente;


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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("INSERT INTO Cliente VALUES (?, ?, ?)");
            st.setString(1, nombreCompleto);
            st.setString(2, direccion);
            st.setString(3, numTelefono);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeAgregarCliente.setText("Cliente agregado con exito");

                txtNombreCompletoAgregarCliente.clear();
                txtDireccionAgregarCliente.clear();
                txtNumTelefonoAgregarCliente.clear();

                cargarClientes();
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    public void buscarCliente() {
        txtMensajeBuscarCliente.clear();
        int id = Integer.parseInt(txtIdBuscarCliente.getText());

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Cliente WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int idString = rs.getInt("id");
                String nombreCompleto = rs.getString("nombre_completo");
                String direccion = rs.getString("direccion");
                String numTelefono = rs.getString("num_telefono");

                txtMensajeBuscarCliente.appendText("CLIENTE ENCONTRADO:\n" + idString + ", " + nombreCompleto + ", " + direccion + ", " + numTelefono + ".");
            } else {
                txtMensajeBuscarCliente.setText("Cliente no encontrado en base de datos");
            }
            conn.close();
        } catch (Exception e) {
            txtMensajeBuscarCliente.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void mostrarTodosCliente() {
        txtMensajeMostrarTodosCliente.clear();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Cliente");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreCompleto = rs.getString("nombre_completo");
                String direccion = rs.getString("direccion");
                String numTelefono = rs.getString("num_telefono");

                txtMensajeMostrarTodosCliente.appendText("CLIENTE:\n" + id + ", " + nombreCompleto + ", " + direccion + ", " + numTelefono + ".");
            }
            conn.close();
        } catch (Exception e) {
            txtMensajeMostrarTodosCliente.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void eliminarCliente() {
        txtMensajeEliminarCliente.clear();
        int id = Integer.parseInt(txtIdEliminarCliente.getText());

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("DELETE FROM Cliente WHERE id = ?");
            st.setInt(1, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeEliminarCliente.setText("Cliente eliminado con exito");
                cargarClientes();
            } else {
                txtMensajeEliminarCliente.setText("Cliente no encontrado en base de datos");
            }

            conn.close();
        } catch (Exception e) {
            txtMensajeBuscarCliente.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void actualizarCliente() {
        txtMensajeActualizarCliente.clear();
        int id = Integer.parseInt(txtIdActualizarCliente.getText());
        String nombreCompleto = txtNombreCompletoActualizarCliente.getText();
        String direccion = txtDireccionActualizarCliente.getText();
        String numTelefono = txtNumTelefonoActualizarCliente.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            PreparedStatement st = conn.prepareStatement("UPDATE Cliente SET ?, ?, ? WHERE id = ?");
            st.setString(1, nombreCompleto);
            st.setString(2, direccion);
            st.setString(3, numTelefono);
            st.setInt(4, id);

            int filas = st.executeUpdate();
            if (filas > 0) {
                txtMensajeActualizarCliente.setText("Cliente eliminado con exito");
                cargarClientes();
            } else {
                txtMensajeActualizarCliente.setText("Cliente no encontrado en base de datos");
            }
            conn.close();
        } catch (Exception e) {
            txtMensajeActualizarCliente.setText("Error: " + e.getMessage());
        }
    }

    @FXML

    private void cargarClientes() {
        clientes = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "pooUser", "pooUser");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Cliente");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreCompleto = rs.getString("nombre_completo");
                String direccion = rs.getString("direccion");
                String numTelefono = rs.getString("num_telefono");

                Cliente cliente = new Cliente(id, nombreCompleto, direccion, numTelefono);
                clientes.add(cliente);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
