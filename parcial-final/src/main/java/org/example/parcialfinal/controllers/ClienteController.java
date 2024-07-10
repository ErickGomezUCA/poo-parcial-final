package org.example.parcialfinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.parcialfinal.LobbyApplication;
import org.example.parcialfinal.backend.Cliente;
import org.example.parcialfinal.backend.Tarjeta;
import org.example.parcialfinal.backend.alertas.Alerta;
import org.example.parcialfinal.backend.database.DBConnection;
import org.example.parcialfinal.backend.database.DatabaseUtils;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ClienteController { //00167523 comienzo de clase clientecontroller
    DBConnection connection = DBConnection.getInstance(); //00167523 inicializacion de dbconnection con su singleton
    Alerta alerta = new Alerta(); //00167523 inicializacion de alerta con clase alerta

    @FXML
    private VBox main;

    @FXML
    private TextField txtNombreCompletoAgregarCliente; //00167523 textfield para guardar el nombrecompleto de cliente en agregarcliente
    @FXML
    private TextField txtDireccionAgregarCliente; //00167523 textfield para guardar direccion de cliente en agregarcliente
    @FXML
    private TextField txtNumTelefonoAgregarCliente; //00167523 textfield para guardar el numtelefono de cliente en agregarcliente

    @FXML
    private ComboBox<Cliente> selectIdActualizarCliente; //00167523 combobox de tipo cliente que selecciona el id en actualizarcliente
    @FXML
    private TextField txtNombreCompletoActualizarCliente; //00167523 textfield para guardar el nombrecompleto de cliente en actualizarcliente
    @FXML
    private TextField txtDireccionActualizarCliente; //00167523 textfield para guardar direccion de cliente en actualizarcliente
    @FXML
    private TextField txtNumTelefonoActualizarCliente; //00167523 textfield para guardar numtelefono de cliente en actualizarcliente


    @FXML
    private ComboBox<Cliente> selectIdBuscarCliente; //00167523 combobox de tipo cliente que selecciona id en buscarcliente

    @FXML
    private ComboBox<Cliente> selectIdEliminarCliente; //00167523 combobox de tipo cliente que selecciona id en eliminarcliente

    @FXML
    private TableView<Cliente> tableCliente; //00167523 tableview de tipo cliente
    @FXML
    private TableColumn<Cliente, Integer> colId; //00167523 columna int para idcliente
    @FXML
    private TableColumn<Cliente, String> colNombreCompleto; //00167523 columna string para nombrecompleto
    @FXML
    private TableColumn<Cliente, String> colDireccion; //00167523 columna string para direccion cliente
    @FXML
    private TableColumn<Cliente, String> colNumTelefono; //00167523 columna string para numtelefono cliente
    private ArrayList<Cliente> clientes; //00167523 arraylist de clientes

    @FXML

    public void initialize() {
        actualizarInputs();
        mostrarClientesTodos(); //00167523 llamando metodo mostrarclientetodos
    }

    private void mostrarClientesTodos() { //00167523 metodo para mostrar todos los clientes
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes()); //00167523 lista observable clientes que proviene de databaseutils obtenerclientes
        mostrarCliente(clientes); //00167523 llamando a metodo mostrar cliente con parametro clientes para mostrar uno por uno
    }

    private void mostrarCliente(ObservableList<Cliente> clientes) { //00167523 metodo para mostrar un cliente con parametro lista observable cliente
        tableCliente.setItems(clientes); //00167523 se pone en la tablacliente los items de clientes
        colId.setCellValueFactory(new PropertyValueFactory<>("id")); //00167523 en la colID se ponen los valores de la columna de la clase cliente id
        colNombreCompleto.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto")); //00167523 en la colnombrecompleto se ponen los valores de la columa de la clase cliente nombrecomppleto
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion")); //00167523 en la coldireccion se ponen los valores de la columa de la clase cliente direccion
        colNumTelefono.setCellValueFactory(new PropertyValueFactory<>("numeroTelefono")); //00167523 en la colnumtelefono se ponen los valores de la columa de la clase cliente numerotelefono
    }

    private void actualizarInputs() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes());

        prepararBuscar(clientes);
        prepararActualizar(clientes);
        prepararEliminar(clientes);
    }

    private void prepararBuscar(ObservableList<Cliente> clientes) { //00167523 metodo para perarar el combobox a utilizar en buscarclientes
        selectIdBuscarCliente.setItems(clientes); //00167523 combobox se inicializa con la lista clientes
    }

    private void prepararActualizar(ObservableList<Cliente> clientes) { //00167523 metodo para preparar el combobox a utilizar en actualizarclientes
        selectIdActualizarCliente.setItems(clientes); //00167523 combobox se inicializa con la lista clientes
    }

    private void prepararEliminar(ObservableList<Cliente> clientes) { //00167523 metodo para preparar le combobox a utilizar en eliminarclientes
        selectIdEliminarCliente.setItems(clientes); //00167523 combobox se inicializa con la lista clientes
    }


    @FXML
    public void clickCrearCliente(ActionEvent event) {
        try {
            PreparedStatement st = connection.getConnection().prepareStatement("INSERT INTO Cliente(nombre_completo, direccion, num_telefono) VALUES (?, ?, ?)");
            st.setString(1, txtNombreCompletoAgregarCliente.getText());
            st.setString(2, txtDireccionAgregarCliente.getText());
            st.setString(3, txtNumTelefonoAgregarCliente.getText());
            st.executeUpdate();

            mostrarClientesTodos();
            actualizarInputs();
            alerta.mostrarMensaje("Clientes", "Cliente creado en el sistema");
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
            mostrarCliente(clientes);
            alerta.mostrarMensaje("Clientes", "Cliente encontrado en el sistema");
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
            st.executeUpdate();

            mostrarClientesTodos();
            actualizarInputs();
            alerta.mostrarMensaje("Clientes", "Cliente actualizado en el sistema");
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

            mostrarClientesTodos();
            actualizarInputs();
            alerta.mostrarMensaje("Clientes", "Cliente eliminado en el sistema");
            connection.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException();
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
