package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.alertas.Alerta;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import javafx.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

public class ClienteController {
    DBConnection connection = DBConnection.getInstance();
    Alerta alerta = new Alerta();

    @FXML
    private TextField txtNombreCompletoAgregarCliente;
    @FXML
    private TextField txtDireccionAgregarCliente;
    @FXML
    private TextField txtNumTelefonoAgregarCliente;

    @FXML
    private ComboBox<Cliente> selectIdActualizarCliente;
    @FXML
    private TextField txtNombreCompletoActualizarCliente;
    @FXML
    private TextField txtDireccionActualizarCliente;
    @FXML
    private TextField txtNumTelefonoActualizarCliente;


    @FXML
    private ComboBox<Cliente> selectIdBuscarCliente;

    @FXML
    private ComboBox<Cliente> selectIdEliminarCliente;

    @FXML
    private TableView<Cliente> tableCliente;
    @FXML
    private TableColumn<Tarjeta, Integer> colId;
    @FXML
    private TableColumn<Tarjeta, String> colNombreCompleto;
    @FXML
    private TableColumn<Tarjeta, String> colDireccion;
    @FXML
    private TableColumn<Tarjeta, String> colNumTelefono;
    private ArrayList<Cliente> clientes;

    @FXML
    public void initialize() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());

        prepararBuscar(clientes);
        prepararActualizar(clientes);
        prepararEliminar(clientes);

        mostrarClientesTodos();
    }

    private void mostrarClientesTodos() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());
        mostrarCliente(clientes);
    }

    private void mostrarCliente(ObservableList<Cliente> clientes) {
        tableCliente.setItems(clientes);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombreCompleto.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colNumTelefono.setCellValueFactory(new PropertyValueFactory<>("numeroTelefono"));
    }

    private void prepararBuscar(ObservableList<Cliente> clientes) {
        selectIdBuscarCliente.setItems(clientes);
    }

    private void prepararActualizar(ObservableList<Cliente> clientes) {
        selectIdActualizarCliente.setItems(clientes);
    }

    private void prepararEliminar(ObservableList<Cliente> clientes) {
        selectIdEliminarCliente.setItems(clientes);
    }


    @FXML
    public void clickCrearCliente(ActionEvent event) {
        try {
            PreparedStatement st = connection.getConnection().prepareStatement("INSERT INTO Cliente(nombre_completo, direccion, num_telefono) VALUES (?, ?, ?)");
            st.setString(1, txtNombreCompletoAgregarCliente.getText());
            st.setString(2, txtDireccionAgregarCliente.getText());
            st.setString(3, txtNumTelefonoAgregarCliente.getText());
            st.executeUpdate();

            alerta.mostrarMensaje("Clientes", "Cliente creado en el sistema");
            mostrarClientesTodos();
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clickBuscarCliente() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        try {
            Statement st = connection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Cliente WHERE id = " + selectIdBuscarCliente.getValue().getId() + ";");

            while (rs.next()) {
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre_completo"), rs.getString("direccion"), rs.getString("num_telefono")));
            }
            alerta.mostrarMensaje("Clientes", "Cliente encontrado en el sistema");
            mostrarCliente(clientes);
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clickActualizarCliente() {
        try {
            PreparedStatement st = connection.getConnection().prepareStatement("UPDATE Cliente SET nombre_completo = ?, direccion = ?, num_telefono = ? WHERE id = ?");
            st.setString(1, txtNombreCompletoActualizarCliente.getText());
            st.setString(2, txtDireccionActualizarCliente.getText());
            st.setString(3, txtNumTelefonoActualizarCliente.getText());
            st.setInt(4, selectIdActualizarCliente.getValue().getId());

            alerta.mostrarMensaje("Clientes", "Cliente actualizado en el sistema");
            mostrarClientesTodos();
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void clickEliminarCliente() {
        try {
            PreparedStatement st = connection.getConnection().prepareStatement("DELETE FROM Cliente WHERE id = ?");
            st.setInt(1, selectIdEliminarCliente.getValue().getId());
            st.executeUpdate();

            alerta.mostrarMensaje("Clientes", "Cliente eliminado en el sistema");
            mostrarClientesTodos();
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
