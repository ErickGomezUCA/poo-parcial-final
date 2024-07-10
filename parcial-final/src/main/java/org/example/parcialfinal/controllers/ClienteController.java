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
    private VBox main; // Ventana principal para cerrarla

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

    public void initialize() { // Cuando la ventana inicie
        actualizarInputs(); // 00300723 Actualizar los inputs
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

    private void actualizarInputs() { // 00300723 Método para actualizar los inputs
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(DatabaseUtils.obtenerClientes()); // 00300723 Obtener lista de clientes
        prepararBuscar(clientes); // 00300723 Preparar ComboBox de búsqueda con los clientes
        prepararActualizar(clientes); // 00300723 Preparar ComboBox de actualización con los clientes
        prepararEliminar(clientes); // 00300723 Preparar ComboBox de eliminación con los clientes
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

    public void clickCrearCliente(ActionEvent event) { // 00300723 Inicio del método
        try { // 00300723 Bloque try para manejar posibles excepciones SQL
            PreparedStatement st = connection.getConnection().prepareStatement("INSERT INTO Cliente(nombre_completo, direccion, num_telefono) VALUES (?, ?, ?)"); // 00300723 Preparar la declaración SQL
            st.setString(1, txtNombreCompletoAgregarCliente.getText()); // 00300723 Establecer el nombre del cliente
            st.setString(2, txtDireccionAgregarCliente.getText()); // 00300723 Establecer la dirección del cliente
            st.setString(3, txtNumTelefonoAgregarCliente.getText()); // 00300723 Establecer el número de teléfono del cliente
            st.executeUpdate(); // 00300723 Ejecutar la actualización

            mostrarClientesTodos(); // 00300723 Mostrar todos los clientes
            actualizarInputs(); // 00300723 Actualizar los inputs
            alerta.mostrarMensaje("Clientes", "Cliente creado en el sistema"); // 00300723 Mostrar mensaje de éxito
            connection.closeConnection(); // 00300723 Cerrar la conexión
        } catch (SQLException e) { // 00300723 Capturar excepciones SQL
            throw new RuntimeException(e); // 00300723 Lanzar una nueva RuntimeException
        }
    }

    public void clickBuscarCliente() { // 00300723 Inicio del método
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(); // 00300723 Crear una lista observable de clientes
        try { // 00300723 Bloque try para manejar posibles excepciones SQL
            Statement st = connection.getConnection().createStatement(); // 00300723 Crear una declaración SQL
            ResultSet rs = st.executeQuery("SELECT * FROM Cliente WHERE id = " + selectIdBuscarCliente.getValue().getId() + ";"); // 00300723 Ejecutar la consulta SQL

            while (rs.next()) { // 00300723 Iterar sobre los resultados
                clientes.add(new Cliente(rs.getInt("id"), rs.getString("nombre_completo"), rs.getString("direccion"), rs.getString("num_telefono"))); // 00300723 Agregar cliente a la lista
            }
            mostrarCliente(clientes); // 00300723 Mostrar los clientes
            alerta.mostrarMensaje("Clientes", "Cliente encontrado en el sistema"); // 00300723 Mostrar mensaje de éxito
            connection.closeConnection(); // 00300723 Cerrar la conexión
        } catch (SQLException e) { // 00300723 Capturar excepciones SQL
            throw new RuntimeException(e); // 00300723 Lanzar una nueva RuntimeException
        }
    }

    public void clickActualizarCliente() { // 00300723 Inicio del método
        try { // 00300723 Bloque try para manejar posibles excepciones SQL
            PreparedStatement st = connection.getConnection().prepareStatement("UPDATE Cliente SET nombre_completo = ?, direccion = ?, num_telefono = ? WHERE id = ?"); // 00300723 Preparar la declaración SQL
            st.setString(1, txtNombreCompletoActualizarCliente.getText()); // 00300723 Establecer el nombre del cliente
            st.setString(2, txtDireccionActualizarCliente.getText()); // 00300723 Establecer la dirección del cliente
            st.setString(3, txtNumTelefonoActualizarCliente.getText()); // 00300723 Establecer el número de teléfono del cliente
            st.setInt(4, selectIdActualizarCliente.getValue().getId()); // 00300723 Establecer el ID del cliente
            st.executeUpdate(); // 00300723 Ejecutar la actualización

            mostrarClientesTodos(); // 00300723 Mostrar todos los clientes
            actualizarInputs(); // 00300723 Actualizar los inputs
            alerta.mostrarMensaje("Clientes", "Cliente actualizado en el sistema"); // 00300723 Mostrar mensaje de éxito
            connection.closeConnection(); // 00300723 Cerrar la conexión
        } catch (SQLException e) { // 00300723 Capturar excepciones SQL
            throw new RuntimeException(e); // 00300723 Lanzar una nueva RuntimeException
        }
    }

    public void clickEliminarCliente() { // 00300723 Inicio del método
        try { // 00300723 Bloque try para manejar posibles excepciones SQL
            PreparedStatement st = connection.getConnection().prepareStatement("DELETE FROM Cliente WHERE id = ?"); // 00300723 Preparar la declaración SQL
            st.setInt(1, selectIdEliminarCliente.getValue().getId()); // 00300723 Establecer el ID del cliente
            st.executeUpdate(); // 00300723 Ejecutar la eliminación

            mostrarClientesTodos(); // 00300723 Mostrar todos los clientes
            actualizarInputs(); // 00300723 Actualizar los inputs
            alerta.mostrarMensaje("Clientes", "Cliente eliminado en el sistema"); // 00300723 Mostrar mensaje de éxito
            connection.closeConnection(); // 00300723 Cerrar la conexión
        } catch (SQLException e) { // 00300723 Capturar excepciones SQL
            throw new RuntimeException(); // 00300723 Lanzar una nueva RuntimeException
        }
    }

    private void clickRegresar(ActionEvent event) { // 00300723 Inicio del método
        Stage stage = new Stage(); // 00300723 Crear un nuevo Stage
        LobbyApplication lobbyApp = new LobbyApplication(); // 00300723 Crear una nueva instancia de LobbyApplication
        try { // 00300723 Bloque try para manejar posibles excepciones IO
            lobbyApp.start(stage); // 00300723 Iniciar la aplicación de Lobby
        } catch (IOException e) { // 00300723 Capturar excepciones IO
            throw new RuntimeException(e); // 00300723 Lanzar una nueva RuntimeException
        }
        stage.show(); // 00300723 Mostrar el Stage
        cerrar(); // 00300723 Cerrar la ventana actual
    }

    private void cerrar() { // 00300723 Inicio del método
        ((Stage)main.getScene().getWindow()).close(); // 00300723 Cerrar la ventana actual
    }
}
